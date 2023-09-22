package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import umpaz.nethersdelight.common.registry.NDBlocks;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class RichSoulSoilBlock extends Block
{

    public RichSoulSoilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (!worldIn.isClientSide) {
            BlockPos abovePos = pos.above();
            BlockPos belowPos = pos.below();
            BlockState aboveState = worldIn.getBlockState(abovePos);
            BlockState belowState = worldIn.getBlockState(belowPos);
            Block aboveBlock = aboveState.getBlock();
            Block belowBlock = belowState.getBlock();

            // Convert mushrooms to colonies if it's dark enough
            if (aboveBlock == Blocks.CRIMSON_FUNGUS) {
                worldIn.setBlockAndUpdate(pos.above(), NDBlocks.CRIMSON_FUNGUS_COLONY.get().defaultBlockState());
                return;
            }
            if (aboveBlock == Blocks.WARPED_FUNGUS) {
                worldIn.setBlockAndUpdate(pos.above(), NDBlocks.WARPED_FUNGUS_COLONY.get().defaultBlockState());
                return;
            }
            if (worldIn.isEmptyBlock(abovePos) && worldIn.getMaxLocalRawBrightness(abovePos) < 1 && MathUtils.RAND.nextInt(50) == 0) {
                worldIn.setBlockAndUpdate(pos.above(), NDBlocks.MIMICARNATION.get().defaultBlockState());
                return;
            }

            if (Configuration.RICH_SOIL_BOOST_CHANCE.get() == 0.0) {
                return;
            }

            if (aboveBlock instanceof BonemealableBlock growable && (aboveBlock instanceof TwistingVinesBlock || aboveBlock instanceof TwistingVinesPlantBlock) && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get() - 0.95F) {
                if (growable.isValidBonemealTarget(worldIn, pos.above(), aboveState, false) && ForgeHooks.onCropsGrowPre(worldIn, pos.above(), aboveState, true)) {
                    growable.performBonemeal(worldIn, worldIn.random, pos.above(), aboveState);
                    worldIn.levelEvent(2005, pos.above(), 0);
                    ForgeHooks.onCropsGrowPost(worldIn, pos.above(), aboveState);
                }
                return;
            }
            if (belowBlock instanceof BonemealableBlock growable && (belowBlock instanceof WeepingVinesBlock || belowBlock instanceof WeepingVinesPlantBlock) && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get() - 0.75) {
                if (growable.isValidBonemealTarget(worldIn, pos.below(), belowState, false) && ForgeHooks.onCropsGrowPre(worldIn, pos.below(), belowState, true)) {
                    growable.performBonemeal(worldIn, worldIn.random, pos.below(), belowState);
                    worldIn.levelEvent(2005, pos.below(), 0);
                    ForgeHooks.onCropsGrowPost(worldIn, pos.below(), belowState);
                }
                return;
            }
            if (aboveBlock == Blocks.NETHER_WART && MathUtils.RAND.nextFloat() <= Configuration.RICH_SOIL_BOOST_CHANCE.get() - 0.5F) {
                if (ForgeHooks.onCropsGrowPre(worldIn, pos.below(), belowState, true)) {
                    int age = aboveState.getValue(NetherWartBlock.AGE);
                    if (age < NetherWartBlock.MAX_AGE) {
                        worldIn.setBlockAndUpdate(pos.above(), aboveState.setValue(NetherWartBlock.AGE, age + 1));
                        worldIn.levelEvent(2005, pos.above(), 0);
                        ForgeHooks.onCropsGrowPost(worldIn, pos.below(), belowState);
                    }
                }
            }
        }
    }

    /*@Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.PlantType plantType = plantable.getPlantType(world, pos.relative(facing));
        return plantType == PlantType.NETHER;
    }*/
}