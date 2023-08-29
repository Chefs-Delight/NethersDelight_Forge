package umpaz.nethersdelight.data.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import umpaz.nethersdelight.common.registry.NDItems;

import java.util.function.Consumer;

public class NDSmithingRecipes extends RecipeProvider {
    public NDSmithingRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        netheriteSmithing(consumer, NDItems.DIAMOND_MACHETE.get(), RecipeCategory.TOOLS, NDItems.NETHERITE_MACHETE.get());
    }
}
