package umpaz.nethersdelight.common.registry;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.block.*;

import java.util.function.ToIntFunction;

public class NDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NethersDelight.MODID);

    private static FlowerPotBlock vanillaFlowerPot() {
        return (FlowerPotBlock) net.minecraftforge.registries.ForgeRegistries.BLOCKS.getDelegateOrThrow(Blocks.FLOWER_POT).get();
    }

    // Workstations
    public static final RegistryObject<Block> BLACKSTONE_STOVE = BLOCKS.register("blackstone_stove", () -> new BlackstoneStoveBlock(
            BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS).lightLevel(stoveBlockEmission(13, 9))));

    public static final RegistryObject<Block> BLACKSTONE_FURNACE = BLOCKS.register("blackstone_furnace", () -> new BlackstoneFurnaceBlock(
            BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(furnaceBlockEmission(13))));

    public static final RegistryObject<Block> NETHER_BRICK_SMOKER = BLOCKS.register("nether_brick_smoker", () -> new NetherBrickSmokerBlock(
            BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS).requiresCorrectToolForDrops().strength(3.5F).lightLevel(furnaceBlockEmission(13))));

    public static final RegistryObject<Block> BLACKSTONE_BLAST_FURNACE = BLOCKS.register("blackstone_blast_furnace", () -> new BlackstoneBlastFurnaceBlock(
            BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(furnaceBlockEmission(13))));

    //Decoration
    public static final RegistryObject<Block> HOGLIN_TROPHY = BLOCKS.register("hoglin_trophy",
            () -> new HoglinMountBlock(Block.Properties.copy(Blocks.PINK_WOOL)));
    public static final RegistryObject<Block> STUFFED_HOGLIN = BLOCKS.register("stuffed_hoglin",
            () -> new StuffedHoglinBlock(Block.Properties.copy(Blocks.CAKE)));
    // Composting

    public static final RegistryObject<Block> CRIMSON_FUNGUS_COLONY = BLOCKS.register("crimson_fungus_colony",
            () -> new FungusColonyBlock(Block.Properties.copy(Blocks.CRIMSON_FUNGUS).randomTicks(), () -> Items.CRIMSON_FUNGUS));

    public static final RegistryObject<Block> WARPED_FUNGUS_COLONY = BLOCKS.register("warped_fungus_colony",
            () -> new FungusColonyBlock(Block.Properties.copy(Blocks.WARPED_FUNGUS).randomTicks(), () -> Items.WARPED_FUNGUS));

    public static final RegistryObject<Block> SOUL_COMPOST = BLOCKS.register("soul_compost",
            () -> new SoulCompostBlock(Block.Properties.copy(Blocks.SOUL_SOIL).strength(1.2F).sound(SoundType.CROP)));

    public static final RegistryObject<Block> RICH_SOUL_SOIL = BLOCKS.register("rich_soul_soil",
            () -> new RichSoulSoilBlock(Block.Properties.copy(Blocks.SOUL_SOIL).randomTicks()));

    private static final BlockBehaviour.Properties mimicarnationBlockBehaviour = BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .pushReaction(PushReaction.DESTROY)
            .noCollission()
            .instabreak()
            .sound(SoundType.GRASS)
            .lightLevel(state -> 8);

    public static final RegistryObject<Block> MIMICARNATION = BLOCKS.register("mimicarnation", () -> new MimicarnationBlock
            (MobEffects.INVISIBILITY, 8, mimicarnationBlockBehaviour));

    public static final RegistryObject<Block> POTTED_MIMICARNATION = BLOCKS.register("potted_mimicarnation",
            () -> new FlowerPotBlock(
                    NDBlocks::vanillaFlowerPot,
                    NDBlocks.MIMICARNATION,
                    BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)
                            .mapColor(MapColor.PLANT)
            )
    );

    //Propelplant
    private static final BlockBehaviour.Properties propelplantBlockBehaviour = BlockBehaviour.Properties.of()
            .mapColor(MapColor.NETHER)
            .pushReaction(PushReaction.DESTROY)
            .noCollission()
            .sound(SoundType.GRASS)
            .strength(0.1F);

    private static final BlockBehaviour.Properties propelplantBerryBlockBehaviour = BlockBehaviour.Properties.of()
            .mapColor(MapColor.NETHER)
            .pushReaction(PushReaction.DESTROY)
            .noCollission()
            .sound(SoundType.GRASS)
            .strength(0.1F)
            .lightLevel(propelplantBlockEmission(9));

    public static final RegistryObject<Block> PROPELPLANT_STEM = BLOCKS.register("propelplant_stem", () ->
            new PropelplantStemBlock(propelplantBlockBehaviour)
    );

    public static final RegistryObject<Block> PROPELPLANT_BERRY_STEM = BLOCKS.register("propelplant_berry_stem", () ->
            new PropelplantBerryStemBlock(propelplantBerryBlockBehaviour)
    );

    public static final RegistryObject<Block> PROPELPLANT_CANE = BLOCKS.register("propelplant_cane", () ->
            new PropelplantCaneBlock(propelplantBlockBehaviour)
    );

    public static final RegistryObject<Block> PROPELPLANT_BERRY_CANE = BLOCKS.register("propelplant_berry_cane", () ->
            new PropelplantBerryCaneBlock(propelplantBerryBlockBehaviour)
    );

    public static final RegistryObject<Block> PROPELPLANT_TORCH = BLOCKS.register("propelplant_torch", () -> new TorchBlock
            (BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).lightLevel((light) -> {
                return 12;
            }), ParticleTypes.FLAME));

    public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH = BLOCKS.register("propelplant_wall_torch", () -> new WallTorchBlock
            (BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).lootFrom( PROPELPLANT_TORCH ).lightLevel((light) -> {
                return 12;
            }), ParticleTypes.FLAME));

    public static ToIntFunction<BlockState> stoveBlockEmission(int lightValue, int soulLightValue) {
        return (state) -> {
            if (state.getValue(BlackstoneStoveBlock.SOUL)) {
                return soulLightValue;
            }
            if (!state.getValue(BlackstoneStoveBlock.SOUL) && state.getValue(BlockStateProperties.LIT)) {
                return lightValue;
            } else
                return 0;
        };
    }

    public static ToIntFunction<BlockState> furnaceBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static ToIntFunction<BlockState> propelplantBlockEmission(int pearl) {
        return (state) -> {
            if (state.getValue(PropelplantBerryStemBlock.PEARL)) {
                return pearl;
            }
            else
                return 0;
        };
    }
}
