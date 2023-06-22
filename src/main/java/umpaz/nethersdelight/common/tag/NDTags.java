package umpaz.nethersdelight.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import umpaz.nethersdelight.NethersDelight;

public class NDTags {

    public static final TagKey<Block> SOUL_COMPOST_ACTIVATORS = modBlockTag("soul_compost_activators");

    public static final TagKey<Block> SOUL_COMPOST_FLAMES = modBlockTag("soul_compost_flames");

    public static final TagKey<Block> FUNGUS_COLONY_GROWABLE_ON = modBlockTag("fungus_colony_growable_on");

    // Strider Meat
    public static final TagKey<Item> RAW_STRIDER = modItemTag("raw_strider");

    // Mimicarnation Meals
    public static final TagKey<Item> MEAL_ITEM = modItemTag("meal_item");

    public static final TagKey<Item> MACHETES = modItemTag("tools/machetes");

    public static final TagKey<Item> HUNTING_TOOLS = modItemTag("tools/hunting_tools");

    private static TagKey<Item> modItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(NethersDelight.MODID + ":" + path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(NethersDelight.MODID + ":" + path));
    }
}
