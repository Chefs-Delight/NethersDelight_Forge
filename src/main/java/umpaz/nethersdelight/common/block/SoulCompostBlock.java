package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class SoulCompostBlock extends Block
{
    public static IntegerProperty COMPOSTING = IntegerProperty.create("composting", 0, 3);

    public SoulCompostBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(super.defaultBlockState().setValue(COMPOSTING, 0));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COMPOSTING);
        super.createBlockStateDefinition(builder);
    }

    public int getMaxCompostingStage() {
        return 3;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if (worldIn.isClientSide) return;

        float chance = 0F;
        boolean hasLava = false;
        boolean isSoulValley = false;

        for (BlockPos neighborPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState neighborState = worldIn.getBlockState(neighborPos);
            if (neighborState.is(NDTags.SOUL_COMPOST_ACTIVATORS) || neighborState.is(NDTags.SOUL_COMPOST_FLAMES)) {
                chance += 0.02F;
            }
            if (neighborState.getFluidState().is(FluidTags.LAVA)) {
                hasLava = true;
            }
            if (worldIn.getBiome(pos) == Biomes.SOUL_SAND_VALLEY) {
                isSoulValley = true;
            }
        }

        chance += hasLava ? 0.08F : 0.00F;
        chance += isSoulValley ? 0.3F : 0.0F;

        if (worldIn.getRandom().nextFloat() <= chance && worldIn.dimensionType().ultraWarm()) {
            if (state.getValue(COMPOSTING) == this.getMaxCompostingStage())
                worldIn.setBlock(pos, NDBlocks.RICH_SOUL_SOIL.get().defaultBlockState(), 3); // finished
            else
                worldIn.setBlock(pos, state.setValue(COMPOSTING, state.getValue(COMPOSTING) + 1), 3); // next stage
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
        return (getMaxCompostingStage() + 1 - blockState.getValue(COMPOSTING));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0 && level.dimensionType().ultraWarm()) {
            level.addParticle(ParticleTypes.SOUL, (double) pos.getX() + (double) random.nextFloat(), (double) pos.getY() + 1.1D, (double) pos.getZ() + (double) random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }
}
