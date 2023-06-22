package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import umpaz.nethersdelight.common.block.util.PropelplantBlock;
import umpaz.nethersdelight.common.registry.NDBlocks;

import javax.annotation.Nullable;

public class PropelplantCaneBlock extends PropelplantBlock {

	public PropelplantCaneBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.isEmptyBlock(pos.above()) && random.nextInt(16) == 0) {
			level.setBlock(pos.above(), NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState(), 2);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockState = context.getLevel().getBlockState(context.getClickedPos().below());
		if (blockState == NDBlocks.PROPELPLANT_STEM.get().defaultBlockState() || blockState == NDBlocks.PROPELPLANT_CANE.get().defaultBlockState()) {
			return NDBlocks.PROPELPLANT_CANE.get().defaultBlockState();
		}
		else {
			return NDBlocks.PROPELPLANT_BERRY_STEM.get().defaultBlockState();
		}
	}
	
	@Override
	 protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return state.is(NDBlocks.PROPELPLANT_CANE.get()) || state.is(NDBlocks.PROPELPLANT_STEM.get());
	   }
}
