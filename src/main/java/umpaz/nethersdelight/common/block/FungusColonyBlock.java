package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

import java.util.function.Supplier;

public class FungusColonyBlock extends MushroomColonyBlock {
    public static final IntegerProperty COLONY_AGE = BlockStateProperties.AGE_3;

    public FungusColonyBlock(Properties properties, Supplier<Item> fungusType) {
        super(properties, fungusType);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.NYLIUM)
                || state.is(Blocks.MYCELIUM)
                || state.is(Blocks.SOUL_SOIL)
                || state.is(NDBlocks.RICH_SOUL_SOIL.get())
                || state.is(BlockTags.DIRT)
                || state.is(Blocks.FARMLAND)
                || super.mayPlaceOn(state, level, pos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this)
            return level.getBlockState(blockpos).canSustainPlant(level, blockpos, Direction.UP, this);
        return this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int age = state.getValue(COLONY_AGE);
        ItemStack heldStack = player.getItemInHand(handIn);

        if (age > 0 && heldStack.is(Tags.Items.SHEARS)) {
            popResource(worldIn, pos, this.getCloneItemStack(worldIn, pos, state));
            worldIn.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
            worldIn.setBlock(pos, state.setValue(COLONY_AGE, age - 1), 2);
            if (!worldIn.isClientSide) {
                heldStack.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(handIn));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(COLONY_AGE);
        BlockState groundState = level.getBlockState(pos.below());
        if (age < this.getMaxAge() && groundState.is(NDTags.FUNGUS_COLONY_GROWABLE_ON) && ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(4) == 0)) {
            level.setBlock(pos, state.setValue(COLONY_AGE, age + 1), 2);
            ForgeHooks.onCropsGrowPost(level, pos, state);
        }
    }
}