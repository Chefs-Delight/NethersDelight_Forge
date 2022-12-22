package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.tag.NDTags;

import java.util.Random;

public class MimicarnationBlock extends FlowerBlock {

	public MimicarnationBlock(MobEffect suspiciousStewEffect, int length, Properties properties) {
		super(suspiciousStewEffect, length, properties);
	}

	@Override
	public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return super.mayPlaceOn(state, level, pos) || state.is(Blocks.NETHERRACK) || state.is(Blocks.SOUL_SAND) || state.is(Blocks.SOUL_SOIL) || state.is(NDBlocks.RICH_SOUL_SOIL.get()) || state.is(NDBlocks.SOUL_COMPOST.get());
	}
	
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
		Vec3 vec3 = voxelshape.bounds().getCenter();
		double d0 = (double)pos.getX() + vec3.x;
		double d1 = (double)pos.getZ() + vec3.z;
		for(int i = 0; i < 1; ++i) {
			BlockPos belowPos = pos.below();
			BlockState belowState = level.getBlockState(belowPos);
			Block belowBlock = belowState.getBlock();
			if (random.nextBoolean() && random.nextInt(30) == 0 && belowBlock == NDBlocks.RICH_SOUL_SOIL.get()) {
				level.addParticle(ParticleTypes.SOUL, d0 + random.nextDouble() / 5.0D, (double)pos.getY() + (0.5D - random.nextDouble()), d1 + random.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
			}
		}      
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack heldStack = player.getItemInHand(handIn);
		Item heldItem = heldStack.getItem();
		VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
		Vec3 vec3 = voxelshape.bounds().getCenter();
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);
		Block belowBlock = belowState.getBlock();
		ItemEntity itemEntity = new ItemEntity(level, pos.getX() + vec3.x, pos.getY(), pos.getZ() + vec3.z, new ItemStack(heldItem, 1));
		itemEntity.setDeltaMovement(Vec3.ZERO);
		if (heldStack.isEmpty()) {
			return InteractionResult.PASS;
		}
		if (heldStack.is(NDTags.MEAL_ITEM) && belowBlock == NDBlocks.RICH_SOUL_SOIL.get()) {
			level.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 5.0F, 5.0F);
			level.addFreshEntity(itemEntity);
			level.removeBlock(pos, false);
			Random random = new Random();
			for(int i = 0; i < 100; ++i) {
			double d0 = random.nextGaussian() * 0.02D;
			double d1 = random.nextGaussian() * 0.02D;
			double d2 = random.nextGaussian() * 0.02D;
			double x = (double)pos.getX() + vec3.x - 0.18D;
			double z = (double)pos.getZ() + vec3.z - 0.18D;
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + random.nextDouble() / 4.0D, (double)pos.getY() + (0.7D - random.nextDouble() / 2.0D), z + random.nextDouble() / 4.0D, d0, d1, d2);
			}
			return InteractionResult.SUCCESS;
		} else
		return InteractionResult.PASS;
	}
}
