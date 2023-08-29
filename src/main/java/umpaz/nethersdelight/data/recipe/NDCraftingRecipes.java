package umpaz.nethersdelight.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.registry.ModItems;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class NDCraftingRecipes extends RecipeProvider {

	public NDCraftingRecipes(PackOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NDItems.SOUL_COMPOST.get())
				.requires(Items.SOUL_SOIL)
				.requires(Items.TWISTING_VINES)
				.requires(Items.TWISTING_VINES)
				.requires(Items.BONE_MEAL)
				.requires(Items.BONE_MEAL)
				.requires(Items.WARPED_ROOTS)
				.requires(Items.WARPED_ROOTS)
				.requires(Items.WARPED_ROOTS)
				.requires(Items.WARPED_ROOTS)
				.unlockedBy("has_soil", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SOUL_SOIL))
				.save(consumer, new ResourceLocation(NethersDelight.MODID, "soul_compost_from_warped_roots"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NDItems.SOUL_COMPOST.get())
				.requires(Items.SOUL_SOIL)
				.requires(NDItems.HOGLIN_HIDE.get())
				.requires(NDItems.HOGLIN_HIDE.get())
				.requires(Items.TWISTING_VINES)
				.requires(Items.TWISTING_VINES)
				.requires(Items.BONE_MEAL)
				.requires(Items.BONE_MEAL)
				.requires(Items.BONE_MEAL)
				.requires(Items.BONE_MEAL)
				.unlockedBy("has_soil", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SOUL_SOIL))
				.save(consumer, new ResourceLocation(NethersDelight.MODID, "soul_compost_from_hoglin_hide"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NDItems.WARPED_MOLDY_MEAT.get())
				.requires(Items.WARPED_ROOTS)
				.requires(Items.WARPED_ROOTS)
				.requires(NDItems.HOGLIN_SIRLOIN.get())
				.requires(Items.BOWL)
				.unlockedBy("has_sirloin", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_SIRLOIN.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NDItems.BLACKSTONE_FURNACE.get())
				.pattern("###")
				.pattern("# #")
				.pattern("###")
				.define('#', Items.BLACKSTONE)
				.unlockedBy("has_blackstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACKSTONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NDItems.BLACKSTONE_BLAST_FURNACE.get())
				.pattern("###")
				.pattern("#b#")
				.pattern("sss")
				.define('#', Tags.Items.INGOTS_IRON)
				.define('b', NDItems.BLACKSTONE_FURNACE.get())
				.define('s', Items.POLISHED_BLACKSTONE)
				.unlockedBy("has_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NDItems.NETHER_BRICK_SMOKER.get())
				.pattern(" n ")
				.pattern("nbn")
				.pattern(" n ")
				.define('n', Items.NETHER_BRICKS)
				.define('b', NDItems.BLACKSTONE_FURNACE.get())
				.unlockedBy("has_nether_bricks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NDItems.IRON_MACHETE.get())
				.pattern("  m")
				.pattern(" m ")
				.pattern("s  ")
				.define('m', Tags.Items.INGOTS_IRON)
				.define('s', Items.STICK)
				.unlockedBy("has_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NDItems.DIAMOND_MACHETE.get())
				.pattern("  m")
				.pattern(" m ")
				.pattern("s  ")
				.define('m', Items.DIAMOND)
				.define('s', Items.STICK)
				.unlockedBy("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
				.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, NDItems.GOLDEN_MACHETE.get())
				.pattern("  m")
				.pattern(" m ")
				.pattern("s  ")
				.define('m', Items.GOLD_INGOT)
				.define('s', Items.STICK)
				.unlockedBy("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,  NDItems.BLACKSTONE_STOVE.get())
			.pattern("nnn")
			.pattern("b b")
			.pattern("bcb")
			.define('n', Items.NETHER_BRICK)
			.define('b', Items.POLISHED_BLACKSTONE_BRICKS)
			.define('c', Items.CAMPFIRE)
			.unlockedBy("has_nether_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICK))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NDItems.NETHER_SKEWER.get())
			.pattern("ps")
			.pattern("b ")
			.define('p', NDItems.PROPELPEARL.get())
			.define('s', NDTags.RAW_STRIDER)
			.define('b', Items.BLAZE_ROD)
			.unlockedBy("has_propelpearl", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.PROPELPEARL.get()))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NDItems.RAW_STUFFED_HOGLIN.get())
			.pattern("wfc")
			.pattern("l#l")
			.pattern("hsh")
			.define('w', Items.WARPED_ROOTS)
			.define('f', Items.CRIMSON_FUNGUS)
			.define('c', Items.CRIMSON_ROOTS)
			.define('l', NDItems.HOGLIN_LOIN.get())
			.define('#', NDItems.HOGLIN_HIDE.get())
			.define('h', ModItems.HAM.get())
			.define('s', ModItems.NETHER_SALAD.get())
			.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_HIDE.get()))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, NDItems.HOGLIN_TROPHY.get())
			.pattern("php")
			.pattern("pwp")
			.pattern("ggg")
			.define('p', ItemTags.PLANKS)
			.define('h', NDItems.HOGLIN_HIDE.get())
			.define('w', ItemTags.WOOL)
			.define('g', Items.GOLD_NUGGET)
			.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_HIDE.get()))
			.save(consumer);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NDItems.PROPELPLANT_TORCH.get())
			.pattern("p")
			.pattern("c")
			.define('p', NDItems.PROPELPEARL.get())
			.define('c', NDItems.PROPELPLANT_CANE.get())
			.unlockedBy("has_propelplant", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.PROPELPEARL.get()))
			.save(consumer);
	}
}