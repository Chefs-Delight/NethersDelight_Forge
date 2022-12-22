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
			level.setBlock(pos.above(), ((Block) NDBlocks.PROPELPLANT_BERRY_CANE.get()).defaultBlockState(), 2);
		}
	}
	
	/*@Override
	 public void onRemove(BlockState state, Level level, BlockPos pos, BlockState facingState, boolean bool) {
		if (!level.isClientSide) {
	   level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
		}
	} */
	
	/*@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);
		Block belowBlock = belowState.getBlock();
		if (!(belowBlock == NDBlocks.RICH_SOUL_SOIL.get() || belowBlock == Blocks.CRIMSON_NYLIUM || belowBlock == Blocks.WARPED_NYLIUM || belowBlock == Blocks.NETHERRACK)) {
			level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
			level.removeBlock(pos, false);	
		}
	}*/

	  @Override
	  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return state.is(BlockTags.NYLIUM) || state.is(Blocks.NETHERRACK) || state.is(NDBlocks.RICH_SOUL_SOIL.get());
	   }
}
