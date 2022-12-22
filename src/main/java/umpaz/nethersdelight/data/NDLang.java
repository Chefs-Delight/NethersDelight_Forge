package umpaz.nethersdelight.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;

import java.util.HashSet;
import java.util.Set;

import static umpaz.nethersdelight.common.registry.NDBlocks.BLOCKS;
import static umpaz.nethersdelight.common.registry.NDItems.ITEMS;

public class NDLang extends LanguageProvider {

    public NDLang(DataGenerator generatorIn) {
        super(generatorIn, NethersDelight.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BLOCKS.getEntries());
        Set<RegistryObject<Item>> items = new HashSet<>(ITEMS.getEntries());

        blocks.remove(NDBlocks.PROPELPLANT_WALL_TORCH);
        blocks.forEach(b ->
        {
            String name = b.get().getDescriptionId().replaceFirst("block.nethersdelight.", "");
            name = toTitleCase(correctBlockItemName(name), "_").replaceAll("Of", "of");
            add(b.get().getDescriptionId(), name);
        });
        items.removeIf(i -> i.get() instanceof BlockItem);
        items.forEach(i ->
        {
            String name = i.get().getDescriptionId().replaceFirst("item.nethersdelight.", "");
            name = toTitleCase(correctBlockItemName(name), "_").replaceAll("Of", "of");
            add(i.get().getDescriptionId(), name);
        });

        add("itemGroup." + NethersDelight.MODID, "Nether's Delight");
        add("nethersdelight.container.blackstone_furnace", "Blackstone Furnace");
        add("nethersdelight.container.nether_brick_smoker", "Nether Brick Smoker");
        add("nethersdelight.container.blackstone_blast_furnace", "Blackstone Blast Furnace");

        add("nethersdelight.jei.composition", "Composition");
        add("nethersdelight.jei.composition.light", "Sped up by adjacent flames (see below)");
        add("nethersdelight.jei.composition.fluid", "Sped up by adjacent lava");
        add("nethersdelight.jei.composition.accelerators", "Sped up by adjacent activators (see below)");
        add("nethersdelight.jei.composition.nether", "Will only compose in the nether");

        add("nethersdelight.block.feast.use_knife", "You need a Knife to cut this.");

    }

    @Override
    public String getName() {
        return "Lang Entries";
    }

    public static String toTitleCase(String givenString, String regex) {
        String[] stringArray = givenString.split(regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringArray) {
            stringBuilder.append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).append(regex);
        }
        return stringBuilder.toString().trim().replaceAll(regex, " ").substring(0, stringBuilder.length() - 1);
    }

    public String correctBlockItemName(String name) {
        if ((!name.endsWith("_bricks"))) {
            if (name.contains("bricks")) {
                name = name.replaceFirst("bricks", "brick");
            }
        }
        if (name.contains("_fence") || name.contains("_button")) {
            if (name.contains("planks")) {
                name = name.replaceFirst("_planks", "");
            }
        }
        return name;
    }
}