package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import umpaz.nethersdelight.common.block.util.PropelplantBlock;
import umpaz.nethersdelight.common.registry.NDBlocks;

public class PropelplantBerryCaneBlock extends PropelplantBlock {
	public PropelplantBerryCaneBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(PEARL, false));
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);
		Block belowBlock = belowState.getBlock();
		if (!state.getValue(PEARL) && (belowBlock instanceof PropelplantStemBlock)) {
			if (random.nextInt(16) == 0) {
				if (random.nextInt(2) > 0) {
					if (level.isEmptyBlock(pos.above())) {
						level.setBlock(pos, NDBlocks.PROPELPLANT_CANE.get().defaultBlockState(), 2);
						level.setBlock(pos.above(), NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState(), 2);
					}
				}
				else {
					level.setBlock(pos, state.setValue(PEARL, true), 2);
				}
			}
		}
		if (!state.getValue(PEARL) && (belowBlock instanceof PropelplantCaneBlock)) {
			if (random.nextInt(8) == 0) {
				level.setBlock(pos, state.setValue(PEARL, true), 2);
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult context) {
		if (state.getValue(PEARL)) {
			return harvestPearls(state, level, pos, player, hand, context);
		}
		return super.use(state, level, pos, player, hand, context);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(NDBlocks.PROPELPLANT_CANE.get()) || state.is(NDBlocks.PROPELPLANT_STEM.get());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
		state.add(PEARL);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos pos, BlockState state, boolean isClientSide) {
	      return !state.getValue(PEARL);
	   }

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
	      return true;
	   }

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
	      level.setBlock(pos, state.setValue(PEARL, true), 2);
	   }
	}
