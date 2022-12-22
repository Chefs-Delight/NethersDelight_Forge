package umpaz.nethersdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.utility.NDTextUtils;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.TextUtils;

import javax.annotation.Nullable;

public class StuffedHoglinBlock extends HorizontalDirectionalBlock {

	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 11);

	protected static final VoxelShape[] SHAPES_NORTH_HEAD = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 9.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 7.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_NORTH_FOOT = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 2.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 2.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 2.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 2.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_SOUTH_HEAD = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 9.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 9.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 9.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 9.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 9.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 9.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 9.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 9.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_SOUTH_FOOT = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 14.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 14.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 14.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 15.0D, 9.0D, 14.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 15.0D)),
	};

	protected static final VoxelShape[] SHAPES_WEST_HEAD = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 7.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 7.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 7.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 7.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 7.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 7.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 7.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 7.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_WEST_FOOT = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(2.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(2.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(2.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(2.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 1.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(1.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_EAST_HEAD = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 1.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 0.0D, 16.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 0.0D, 16.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(9.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 16.0D, 10.0D, 16.0D)),
	};

	protected static final VoxelShape[] SHAPES_EAST_FOOT = new VoxelShape[]{
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 14.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 14.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 14.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 1.0D, 14.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 9.0D, 15.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 9.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 10.0D, 16.0D)),
			Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 2.0D, 0.0D, 15.0D, 10.0D, 16.0D)),
	};


	public StuffedHoglinBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(SERVINGS, 11).setValue(PART, BedPart.HEAD));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(PART) == BedPart.HEAD) {
		switch ((Direction) state.getValue(FACING)) {
			case NORTH:
				return SHAPES_NORTH_HEAD[state.getValue(SERVINGS)];
			case SOUTH:
				return SHAPES_SOUTH_HEAD[state.getValue(SERVINGS)];
			case WEST:
				return SHAPES_WEST_HEAD[state.getValue(SERVINGS)];
			case EAST:
				return SHAPES_EAST_HEAD[state.getValue(SERVINGS)];
			}
		}
		if (state.getValue(PART) == BedPart.FOOT) {
			switch ((Direction) state.getValue(FACING)) {
				case NORTH:
					return SHAPES_NORTH_FOOT[state.getValue(SERVINGS)];
				case SOUTH:
					return SHAPES_SOUTH_FOOT[state.getValue(SERVINGS)];
				case WEST:
					return SHAPES_WEST_FOOT[state.getValue(SERVINGS)];
				case EAST:
					return SHAPES_EAST_FOOT[state.getValue(SERVINGS)];
			}
		}
		return SHAPES_NORTH_HEAD[state.getValue(SERVINGS)];
	}
	
	private static Direction getDirectionToOther(BedPart part, Direction direction) {
		return part == BedPart.HEAD ? direction : direction.getOpposite();
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, SERVINGS, PART);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == getDirectionToOther(stateIn.getValue(PART), stateIn.getValue(FACING))) {
			return stateIn.canSurvive(worldIn, currentPos) && facingState.is(this) && facingState.getValue(PART) != stateIn.getValue(PART) ? stateIn : Blocks.AIR.defaultBlockState();
		} else {
			return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public void playerWillDestroy(Level p_49505_, BlockPos p_49506_, BlockState p_49507_, Player p_49508_) {
		if (!p_49505_.isClientSide && p_49508_.isCreative()) {
			BedPart bedpart = p_49507_.getValue(PART);
			if (bedpart == BedPart.FOOT) {
				BlockPos blockpos = p_49506_.relative(getDirectionToOther(bedpart, p_49507_.getValue(FACING)));
				BlockState blockstate = p_49505_.getBlockState(blockpos);
				if (blockstate.is(this) && blockstate.getValue(PART) == BedPart.HEAD) {
					p_49505_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
					p_49505_.levelEvent(p_49508_, 2001, blockpos, Block.getId(blockstate));
				}
			}
		}

		super.playerWillDestroy(p_49505_, p_49506_, p_49507_, p_49508_);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
		Direction direction = p_49479_.getHorizontalDirection();
		BlockPos blockpos = p_49479_.getClickedPos();
		BlockPos blockpos1 = blockpos.relative(direction);
		Level level = p_49479_.getLevel();
		return level.getBlockState(blockpos1).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos1) ? this.defaultBlockState().setValue(FACING, direction) : null;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(worldIn, pos, state, placer, stack);
		if (!worldIn.isClientSide) {
			BlockPos facingPos = pos.relative(state.getValue(FACING));
			worldIn.setBlock(facingPos, state.setValue(PART, BedPart.FOOT), 3);
			worldIn.blockUpdated(pos, Blocks.AIR);
			state.updateNeighbourShapes(worldIn, pos, 3);
		}
	}
	
	public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
	      BedPart bedpart = state.getValue(PART);
	      return bedpart == BedPart.FOOT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
	   }
	
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int servings = state.getValue(SERVINGS);
		ItemStack heldStack = player.getItemInHand(handIn);
		
		if (servings > 9) {
			if (heldStack.is(ModTags.KNIVES)) {
				return cutEar(level, pos, state);
			}
			else {
				player.displayClientMessage(NDTextUtils.getTranslation("block.feast.use_knife"), true);
				}
			}
		if (servings < 10) {
			if (heldStack.is(Items.BOWL)) {
				if (servings == 9) {
					return takeServing(level, pos, state, player, handIn, NDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT.get());
				}
				if (servings > 4 && servings < 9) {
					return takeServing(level, pos, state, player, handIn, NDItems.PLATE_OF_STUFFED_HOGLIN_HAM.get());
				}
				if (servings > 0 && servings < 5) {
					return takeServing(level, pos, state, player, handIn, NDItems.PLATE_OF_STUFFED_HOGLIN_ROAST.get());
				}
			}
			if (servings == 0) {
				level.playSound(null, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
				level.destroyBlock(pos, true);
			}
			else {
				player.displayClientMessage(TextUtils.getTranslation("block.feast.use_container", new ItemStack(Items.BOWL).getHoverName()), true);
				}
			}
		 return InteractionResult.SUCCESS;
	}
	
	protected InteractionResult cutEar(Level level, BlockPos pos, BlockState state) {
		int servings = state.getValue(SERVINGS);
		BedPart part = state.getValue(PART);
        BlockPos pairPos = pos.relative(getDirectionToOther(part, state.getValue(FACING)));
		BlockState pairState = level.getBlockState(pairPos);

		level.setBlock(pairPos, pairState.setValue(SERVINGS, servings - 1), 3);
		level.setBlock(pos, state.setValue(SERVINGS, servings - 1), 3);

		Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(NDItems.HOGLIN_EAR.get()));
		level.playSound(null, pos, ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
		return InteractionResult.SUCCESS;
	}
	
	protected InteractionResult takeServing(Level level, BlockPos pos, BlockState state, Player player, InteractionHand handIn, Item serving) {
		int servings = state.getValue(SERVINGS);
		BedPart part = state.getValue(PART);
        BlockPos pairPos = pos.relative(getDirectionToOther(part, state.getValue(FACING)));
		BlockState pairState = level.getBlockState(pairPos);
		ItemStack heldItem = player.getItemInHand(handIn);

		level.setBlock(pairPos, pairState.setValue(SERVINGS, servings - 1), 3);
		level.setBlock(pos, state.setValue(SERVINGS, servings - 1), 3);

		if (!player.isCreative()) {
			heldItem.shrink(1);
		}
		if (!player.getInventory().add(new ItemStack(serving))) {
			player.drop(new ItemStack(serving), false);
		}
		level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
		return InteractionResult.SUCCESS;
	}
}