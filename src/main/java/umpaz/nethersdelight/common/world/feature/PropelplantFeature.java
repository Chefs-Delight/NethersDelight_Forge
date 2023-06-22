package umpaz.nethersdelight.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import umpaz.nethersdelight.common.block.PropelplantBerryCaneBlock;
import umpaz.nethersdelight.common.registry.NDBlocks;

import java.util.HashMap;
import java.util.Map;

public class PropelplantFeature extends Feature<NoneFeatureConfiguration> {
	public PropelplantFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		RandomSource rand = level.getRandom();
		BlockState propelplantBush = NDBlocks.PROPELPLANT_BERRY_STEM.get().defaultBlockState();
		BlockState propelplantStem = NDBlocks.PROPELPLANT_STEM.get().defaultBlockState();
		BlockState propelplantCane = NDBlocks.PROPELPLANT_CANE.get().defaultBlockState();
		BlockState propelplantBerryCane = NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState();

		HashMap<BlockPos, BlockState> blocks = new HashMap<>();
		int i = 0;
			for (int x = -3; x <= 3; ++x) {
				for (int z = -3; z <= 3; ++z) {
					if (Math.abs(x) < 2 || Math.abs(z) < 2) {
						for (int y = -1; y <= 1; ++y) {
							BlockPos blockpos = pos.offset(x, y, z);
							BlockPos below = blockpos.below();
							BlockState belowState = level.getBlockState(below);
							if (canGrowPropelplant(belowState) && rand.nextInt(3) == 0) {
								BlockPos above = blockpos.above();
								BlockPos evenMoreAbove = blockpos.above(2);
								if (level.isEmptyBlock(blockpos) && !level.isOutsideBuildHeight(above)) {
									if (rand.nextBoolean()) {
										blocks.put(blockpos, propelplantBush.setValue(PropelplantBerryCaneBlock.PEARL, rand.nextBoolean()));
									}
									else if (rand.nextBoolean() && level.isEmptyBlock(above)) {
										blocks.put(blockpos, propelplantStem);
										blocks.put(above, propelplantBerryCane.setValue(PropelplantBerryCaneBlock.PEARL, rand.nextBoolean()));
									} else if (level.isEmptyBlock(above) && level.isEmptyBlock(evenMoreAbove)) {
										blocks.put(blockpos, propelplantStem);
										blocks.put(above, propelplantCane);
										blocks.put(evenMoreAbove, propelplantBerryCane.setValue(PropelplantBerryCaneBlock.PEARL, rand.nextBoolean()));
									}
								}
							}
							++i;
						}
					}
				}
			}

		for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
			BlockPos entryPos = entry.getKey();
			BlockState entryState = entry.getValue();
			level.setBlock(entryPos, entryState, 19);
		}
		return i > 0;
	}
	public static boolean canGrowPropelplant(BlockState state)
	{
		return state.is(BlockTags.NYLIUM);
	}
}