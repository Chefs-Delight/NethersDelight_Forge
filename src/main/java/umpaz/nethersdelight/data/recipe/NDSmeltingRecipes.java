package umpaz.nethersdelight.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDItems;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class NDSmeltingRecipes extends RecipeProvider {
    public NDSmeltingRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
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
