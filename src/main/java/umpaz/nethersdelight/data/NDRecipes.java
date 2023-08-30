package umpaz.nethersdelight.data;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import umpaz.nethersdelight.data.builder.NDCookingPotRecipeBuilder;
import umpaz.nethersdelight.data.builder.NDCuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class NDRecipes extends RecipeProvider {
    private final List<IRecipeContainer> recipeContainers;

    public NDRecipes(PackOutput output) {
        super(output);
        recipeContainers = List.of(
                new Crafting(),
                new Smelting(),
                new Smithing(),
                new CookingPot(),
                new CuttingBoard()
        );
    }

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        this.recipeContainers.forEach(container -> container.buildRecipes(consumer));
    }

    private interface IRecipeContainer {
        void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer);
    }

    private static class Crafting implements IRecipeContainer {
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

            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NDItems.BLACKSTONE_STOVE.get())
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

    private static class Smelting implements IRecipeContainer {
        @Override
        public void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
            foodSmeltingRecipe("hoglin_sirloin", NDItems.HOGLIN_LOIN.get(), NDItems.HOGLIN_SIRLOIN.get(), 0.35F, consumer);

            SimpleCookingRecipeBuilder.smelting(Ingredient.of(NDItems.IRON_MACHETE.get()), RecipeCategory.MISC,
                            Items.IRON_NUGGET, 0.1F, 200)
                    .unlockedBy("has_iron_machete", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.IRON_MACHETE.get()))
                    .save(consumer, new ResourceLocation(NethersDelight.MODID, "iron_nugget_from_smelting_machete"));
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(NDItems.GOLDEN_MACHETE.get()), RecipeCategory.MISC,
                            Items.GOLD_NUGGET, 0.1F, 200)
                    .unlockedBy("has_golden_machete", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.GOLDEN_MACHETE.get()))
                    .save(consumer, new ResourceLocation(NethersDelight.MODID, "gold_nugget_from_smelting_machete"));
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(NDItems.IRON_MACHETE.get()), RecipeCategory.MISC,
                            Items.IRON_NUGGET, 0.1F, 100)
                    .unlockedBy("has_iron_machete", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.IRON_MACHETE.get()))
                    .save(consumer, new ResourceLocation(NethersDelight.MODID, "iron_nugget_from_blasting_machete"));
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(NDItems.GOLDEN_MACHETE.get()), RecipeCategory.MISC,
                            Items.GOLD_NUGGET, 0.1F, 100)
                    .unlockedBy("has_golden_machete", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.GOLDEN_MACHETE.get()))
                    .save(consumer, new ResourceLocation(NethersDelight.MODID, "gold_nugget_from_blasting_machete"));
        }

        private void foodSmeltingRecipe(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
            String namePrefix = new ResourceLocation(NethersDelight.MODID, name).toString();
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD,
                            result, experience, 200)
                    .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                    .save(consumer);
            SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD,
                            result, experience, 600)
                    .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                    .save(consumer, namePrefix + "_from_campfire_cooking");
            SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD,
                            result, experience, 100)
                    .unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
                    .save(consumer, namePrefix + "_from_smoking");
        }
    }

    private static class Smithing implements IRecipeContainer {
        @Override
        public void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
            netheriteSmithing(consumer, NDItems.DIAMOND_MACHETE.get(), RecipeCategory.TOOLS, NDItems.NETHERITE_MACHETE.get());
        }
    }

    private static class CookingPot implements IRecipeContainer {
        public static final int FAST_COOKING = 100;      // 5 seconds
        public static final int NORMAL_COOKING = 200;    // 10 seconds
        public static final int SLOW_COOKING = 400;      // 20 seconds

        public static final float SMALL_EXP = 0.35F;
        public static final float MEDIUM_EXP = 1.0F;
        public static final float LARGE_EXP = 2.0F;

        @Override
        public void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
            CookingPotRecipeBuilder.cookingPotRecipe(NDItems.STUFFED_HOGLIN.get(), 1, SLOW_COOKING, LARGE_EXP)
                    .addIngredient(ModItems.NETHER_SALAD.get())
                    .addIngredient(NDItems.RAW_STUFFED_HOGLIN.get())
                    .addIngredient(ModItems.NETHER_SALAD.get())
                    .unlockedByItems("has_raw_stuffed_hoglin", NDItems.RAW_STUFFED_HOGLIN.get())
                    .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                    .build(consumer);
            NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.GRILLED_STRIDER.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                    .addIngredient(NDTags.RAW_STRIDER)
                    .addIngredient(Items.WARPED_FUNGUS)
                    .addIngredient(Items.CRIMSON_FUNGUS)
                    .addIngredient(Items.WARPED_ROOTS)
                    .addIngredient(Items.CRIMSON_ROOTS)
                    .unlockedByItems("has_strider_slice", NDItems.STRIDER_SLICE.get())
                    .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                    .build(consumer);
            NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.MAGMA_GELATIN.get(), 1, NORMAL_COOKING, MEDIUM_EXP, Items.LAVA_BUCKET)
                    .addIngredient(Items.MAGMA_CREAM)
                    .addIngredient(Items.MAGMA_CREAM)
                    .addIngredient(Items.MAGMA_CREAM)
                    .addIngredient(NDItems.PROPELPEARL.get())
                    .unlockedByItems("has_propelpearl", NDItems.PROPELPEARL.get())
                    .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                    .build(consumer);
            NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.STRIDER_MOSS_STEW.get(), 1, NORMAL_COOKING, MEDIUM_EXP)
                    .addIngredient(Items.WARPED_FUNGUS)
                    .addIngredient(Items.CRIMSON_FUNGUS)
                    .addIngredient(Items.CRIMSON_ROOTS)
                    .addIngredient(Items.WARPED_FUNGUS)
                    .addIngredient(NDTags.RAW_STRIDER)
                    .unlockedByItems("has_strider_slice", NDItems.STRIDER_SLICE.get())
                    .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                    .build(consumer);
        }
    }

    private static class CuttingBoard implements IRecipeContainer {
        @Override
        public void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
            NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.STRIDER_SLICE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), NDItems.GROUND_STRIDER.get(), 2)
                    .build(consumer);
            NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.CRIMSON_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.CRIMSON_FUNGUS, 5)
                    .build(consumer);
            NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.WARPED_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.WARPED_FUNGUS, 5)
                    .build(consumer);
            NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.HOGLIN_HIDE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.LEATHER, 4)
                    .build(consumer);
            NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.PROPELPLANT_CANE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.GUNPOWDER)
                    .build(consumer);
        }
    }
}
