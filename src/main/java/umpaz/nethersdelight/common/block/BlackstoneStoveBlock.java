package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import umpaz.nethersdelight.common.registry.NDBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.MathUtils;

import javax.annotation.Nullable;

public class BlackstoneStoveBlock extends AbstractStoveBlock {
    public static final BooleanProperty SOUL = BooleanProperty.create("soul");;

    public BlackstoneStoveBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SOUL, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getItemInHand(hand);
        Item heldItem = heldStack.getItem();

        if (state.getValue(LIT) && !state.getValue(SOUL) && (heldItem == Items.SOUL_SAND || heldItem == Items.SOUL_SOIL)) {
            if (!level.isClientSide()) {
                level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            soulLight(state, level, pos);
            if (!player.isCreative()) {
                heldStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    public void soulLight(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, state.setValue(SOUL, true), 2);
        double x = (double) pos.getX() + 0.5D;
        double y = pos.getY();
        double z = (double) pos.getZ() + 0.5D;
        Direction direction = state.getValue(BlackstoneStoveBlock.FACING);
        Direction.Axis direction$axis = direction.getAxis();

        level.playLocalSound(x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 0.2F, 2.6F, false);
        level.playLocalSound(x, y, z, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 1.5F, 2.6F, false);

        {
            double horizontalOffset = MathUtils.RAND.nextDouble() * 0.6D - 0.3D;
            double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
            double yOffset = MathUtils.RAND.nextDouble() * 6.0D / 16.0D;
            double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
            level.addParticle(ParticleTypes.EXPLOSION, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
        }

        for (int i = 0; i< 7; ++i) {
            double horizontalOffset = MathUtils.RAND.nextDouble() * 0.6D - 0.3D;
            double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
            double yOffset = MathUtils.RAND.nextDouble() * 6.0D / 16.0D;
            double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
            level.addParticle(ParticleTypes.SOUL, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D); }
    }

    @Override
    public void extinguish(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        BlockState updatedState = state.setValue(SOUL, false);
        super.extinguish(entity, updatedState, level, pos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(SOUL, false);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        boolean isLit = level.getBlockState(pos).getValue(LIT);
        boolean isSoul = level.getBlockState(pos).getValue(SOUL);
        if (isLit && isSoul && !entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.hurt(ModDamageTypes.getSimpleDamageSource(level, ModDamageTypes.STOVE_BURN), 2.0F);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SOUL);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
        if (stateIn.getValue(LIT) && stateIn.getValue(SOUL)) {
            double x = (double) pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5D;
            if (rand.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.getValue(HorizontalDirectionalBlock.FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double horizontalOffset = rand.nextDouble() * 0.6D - 0.3D;
            double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
            double yOffset = rand.nextDouble() * 6.0D / 16.0D;
            double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
            return;
        }
        super.animateTick(stateIn, level, pos, rand);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return NDBlockEntityTypes.BLACKSTONE_STOVE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createStoveTicker(level, state, blockEntityType, NDBlockEntityTypes.BLACKSTONE_STOVE.get());
    }
}
