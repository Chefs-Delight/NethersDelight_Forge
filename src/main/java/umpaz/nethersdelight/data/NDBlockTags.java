package umpaz.nethersdelight.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class NDBlockTags extends IntrinsicHolderTagsProvider<Block>
{
    public NDBlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper fileHelper) {
        super(
                packOutput,
                ForgeRegistries.Keys.BLOCKS,
                registries,
                (Block attribute) -> ForgeRegistries.BLOCKS.getResourceKey(attribute).get(),
                NethersDelight.MODID,
                fileHelper
        );
    }

    //LET REGULAR MUSHROOMS BE PLACED ON RICH SOUL SOIL :)

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider registries) {
        this.registerModTags();
        this.registerMinecraftTags();
        this.registerForgeTags();
        this.registerFarmersDelightTags();

        this.registerBlockMineables();
    }

    protected void registerBlockMineables() {
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                NDBlocks.HOGLIN_TROPHY.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                NDBlocks.BLACKSTONE_STOVE.get(),
                NDBlocks.BLACKSTONE_FURNACE.get(),
                NDBlocks.NETHER_BRICK_SMOKER.get(),
                NDBlocks.BLACKSTONE_BLAST_FURNACE.get()

        );
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                NDBlocks.SOUL_COMPOST.get(),
                NDBlocks.RICH_SOUL_SOIL.get()
        );
        tag(ModTags.MINEABLE_WITH_KNIFE).add(
                NDBlocks.PROPELPLANT_CANE.get()
        );
    }

    protected void registerMinecraftTags() {
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(
                NDBlocks.SOUL_COMPOST.get(),
                NDBlocks.RICH_SOUL_SOIL.get()
        );
        tag(BlockTags.SMALL_FLOWERS).add(
                NDBlocks.MIMICARNATION.get()
        );
        tag(net.minecraft.tags.BlockTags.TALL_FLOWERS).add(
                ModBlocks.WILD_RICE.get()
        );
    }

    protected void registerForgeTags() {
        tag(BlockTags.DIRT).add(
                NDBlocks.RICH_SOUL_SOIL.get()
        );
    }

    protected void registerModTags() {
        tag(NDTags.FUNGUS_COLONY_GROWABLE_ON).add(
                NDBlocks.RICH_SOUL_SOIL.get()
        );
        tag(NDTags.SOUL_COMPOST_ACTIVATORS).add(
                Blocks.CRIMSON_FUNGUS,
                Blocks.WARPED_FUNGUS,
                Blocks.BONE_BLOCK,
                Blocks.NETHER_WART,
                NDBlocks.SOUL_COMPOST.get(),
                NDBlocks.CRIMSON_FUNGUS_COLONY.get(),
                NDBlocks.WARPED_FUNGUS_COLONY.get(),
                NDBlocks.RICH_SOUL_SOIL.get()
        );
        tag(NDTags.SOUL_COMPOST_FLAMES).add(
                Blocks.LANTERN,
                Blocks.SOUL_LANTERN,
                Blocks.FIRE,
                Blocks.SOUL_FIRE,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE
        );
    }

    protected void registerFarmersDelightTags() {
        tag(ModTags.HEAT_SOURCES).add(
                NDBlocks.BLACKSTONE_STOVE.get(),
                NDBlocks.BLACKSTONE_FURNACE.get(),
                NDBlocks.NETHER_BRICK_SMOKER.get(),
                NDBlocks.BLACKSTONE_BLAST_FURNACE.get()
        );
        tag(ModTags.UNAFFECTED_BY_RICH_SOIL).add(
                NDBlocks.CRIMSON_FUNGUS_COLONY.get(),
                NDBlocks.WARPED_FUNGUS_COLONY.get()
        );
    }
}