package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import umpaz.nethersdelight.common.block.util.PropelplantBlock;
import umpaz.nethersdelight.common.registry.NDBlocks;

public class PropelplantStemBlock extends PropelplantBlock {

	public PropelplantStemBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.isEmptyBlock(pos.above()) && random.nextInt(16) == 0) {
			level.setBlock(pos.above(), NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState(), 2);
		}
	}

	  @Override
	  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return state.is(BlockTags.NYLIUM) || state.is(Blocks.NETHERRACK) || state.is(NDBlocks.RICH_SOUL_SOIL.get());
	   }
}
