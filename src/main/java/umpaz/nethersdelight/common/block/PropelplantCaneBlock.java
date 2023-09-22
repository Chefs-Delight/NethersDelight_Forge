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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;


@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PropelplantCaneBlock extends Block implements IPlantable, BonemealableBlock {
    public static final BooleanProperty PEARL = BooleanProperty.create("pearl");
    public static final BooleanProperty STEM = BooleanProperty.create("stem");
    public static final BooleanProperty BUD = BooleanProperty.create("bud");
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final float EXPLOSION_LEVEL = 1.0F;
    private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public PropelplantCaneBlock(Properties properties) {
        super(properties);
        registerDefaultState(
                defaultBlockState()
                        .setValue(PEARL, false)
                        .setValue(STEM, false)
                        .setValue(BUD, false)
                        .setValue(CUT, false)
        );
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(NDItems.PROPELPLANT_CANE.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult context) {
        if (state.getValue(PEARL)) {
            return harvestPearls(state, level, pos, player, hand, context);
        }
        return super.use(state, level, pos, player, hand, context);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(BUD)) return;

        if (random.nextInt(8) == 0 && !state.getValue(PEARL)) {
            state = state.setValue(PEARL, true);
            level.setBlock(pos, state, 2);
        }

        if (!level.isEmptyBlock(pos.above())) return;

        if (random.nextInt(12) == 0) {
            BlockState belowState = level.getBlockState(pos.below());
            BlockState bottomState = level.getBlockState(pos.below().below());
            if (belowState.is(this) && bottomState.is(this)) return;

            boolean pearl = state.getValue(PEARL);
            level.setBlock(pos, state.setValue(BUD, false).setValue(PEARL, false), 2);
            level.setBlock(pos.above(), defaultBlockState().setValue(BUD, true).setValue(PEARL, pearl), 2);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide && !willHarvest) {
            playerWillDestroy(level, pos, state, player);
            explode(state, level, pos, player);
            return true;
        }

        if (!level.isClientSide()) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            if (aboveState.is(this)) {
                level.setBlock(abovePos, aboveState.setValue(CUT, true), 2);
            }
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @javax.annotation.Nullable BlockEntity blockEntity, ItemStack stack) {
        if (stack.is(ModTags.KNIVES)) {
            BlockPos topPos = pos.above();
            while (level.getBlockState(topPos).getBlock() instanceof PropelplantCaneBlock) {
                dropResources(state, level, topPos, blockEntity, player, stack);
                level.destroyBlock(topPos, true);
                topPos = topPos.above();
            }
        } else {
            explode(state, level, pos);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, stack);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.makeStuckInBlock(state, new Vec3(0.8D, 0.75D, 0.8D));
            if (!entity.isCrouching()) {
                explode(state, level, pos);
            }
            return;
        }
        if (entity instanceof Projectile) {
            explode(state, level, pos);
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        explode(state, level, pos);
        super.onBlockExploded(state, level, pos, explosion);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (state.getValue(PEARL)) return 100;
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 100;
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable net.minecraft.core.Direction face, @javax.annotation.Nullable LivingEntity igniter) {
        explode(state, level, pos);
        super.onCaughtFire(state, level, pos, face, igniter);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!state.canSurvive(level, pos)) {
            explode(state, level, pos);
        }
        super.tick(state, level, pos, randomSource);
    }

    @SubscribeEvent
    public static void explodeOnPistonAction(final PistonEvent.Pre event) {
        if (!(event.getLevel() instanceof Level level)) return;
        if (level.isClientSide) return;
        if (event.getPistonMoveType() != PistonEvent.PistonMoveType.EXTEND) return;

        Direction pistonDirection = event.getDirection();
        BlockPos pistonPos = event.getPos();
        for (int i = 1; i <= PistonStructureResolver.MAX_PUSH_DEPTH + 1; i++) {
            BlockPos targetPos = pistonPos.relative(pistonDirection, i);
            BlockState targetPosState = level.getBlockState(targetPos);

            if (targetPosState.isAir()) {
                break;
            }

    protected void explode(Level level, BlockPos pos) {
        explode(level, pos, (LivingEntity)null);
    }

    protected void explode(Level level, BlockPos pos, @Nullable LivingEntity entity) {
        explode(level.getBlockState(pos), level, pos, entity);
    }

    protected void explode(BlockState state, Level level, BlockPos pos) {
        explode(state, level, pos, (LivingEntity) null);
    }

    protected void explode(BlockState state, Level level, BlockPos pos, @Nullable LivingEntity entity) {
        if (level.isClientSide) return;

        Explosion explosion = level.explode(entity, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, EXPLOSION_LEVEL, false, Level.ExplosionInteraction.NONE);
        super.onBlockExploded(state, level, pos, explosion);
    }

    protected InteractionResult harvestPearls(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult context) {
        int j = 1 + level.random.nextInt(2);
        popResource(level, pos, new ItemStack(NDItems.PROPELPEARL.get(), j));
        level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
        level.setBlock(pos, state.setValue(PEARL, Boolean.FALSE), 2);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState belowState = context.getLevel().getBlockState(context.getClickedPos().below());
        return defaultBlockState().setValue(STEM, !belowState.is(this)).setValue(BUD, true);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction neighborDirection, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (neighborDirection == Direction.DOWN) {
            if (!state.canSurvive(level, pos)) {
                level.scheduleTick(pos, this, 1);
                return state;
            }

            return super.updateShape(state, neighborDirection, neighborState, level, pos, neighborPos).setValue(STEM, !neighborState.is(this));
        }

        if (neighborDirection == Direction.UP) {
            return super.updateShape(state, neighborDirection, neighborState, level, pos, neighborPos).setValue(BUD, !neighborState.is(this)).setValue(PEARL, false);
        }
        return super.updateShape(state, neighborDirection, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);

        if (belowState.is(this) && !belowState.getValue(PEARL)) return true;
        return this.mayPlaceOn(belowState, level, belowPos);
    }

    public boolean mayPlaceOn(BlockState state, LevelReader level, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(Tags.Blocks.NETHERRACK) || state.is(NDBlocks.RICH_SOUL_SOIL.get());
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PEARL, STEM, BUD);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClientSide) {
        return state.getValue(BUD) && !state.getValue(PEARL);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(PEARL, true), 2);
    }

    @Override
    public @Nullable BlockPathTypes getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        return BlockPathTypes.DAMAGE_OTHER;
    }

    @Override
    public @Nullable BlockPathTypes getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, BlockPathTypes originalType) {
        return BlockPathTypes.DAMAGE_OTHER;
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.NETHER;
    }

    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        return defaultBlockState();
    }
}
