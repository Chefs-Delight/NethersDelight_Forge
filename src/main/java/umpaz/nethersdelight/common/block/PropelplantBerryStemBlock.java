package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import umpaz.nethersdelight.common.block.util.PropelplantBlock;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;

public class PropelplantBerryStemBlock extends PropelplantBlock {
	public static final BooleanProperty PEARL = BooleanProperty.create("pearl");

	public PropelplantBerryStemBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(PEARL, false));
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.getValue(PEARL)) {
			if (random.nextInt(16) == 0) {
				if (random.nextInt(2) > 0) {
					if (level.isEmptyBlock(pos.above())) {
						level.setBlock(pos, NDBlocks.PROPELPLANT_STEM.get().defaultBlockState(), 2);
						level.setBlock(pos.above(), NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState(), 2);
					}
				}
				else {
					level.setBlock(pos, state.setValue(PEARL, true), 2);
				}
			}
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult context) {
		if (state.getValue(PEARL)) {
				   int j = 1 + level.random.nextInt(2);
				   popResource(level, pos, new ItemStack(NDItems.PROPELPEARL.get(), j));
				   level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
				   level.setBlock(pos, state.setValue(PEARL, Boolean.FALSE), 2);
		            return InteractionResult.sidedSuccess(level.isClientSide);
			   }
	         return super.use(state, level, pos, player, hand, context);
	   }

		@Override
	   	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
	      state.add(PEARL);
	   }

	   @Override
	   protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return state.is(BlockTags.NYLIUM) || state.is(Blocks.NETHERRACK) || state.is(NDBlocks.RICH_SOUL_SOIL.get());
	   }

	   @Override
	   public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClientSide) {
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
