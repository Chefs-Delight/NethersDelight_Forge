package umpaz.nethersdelight.integration.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.integration.jei.category.CompositionRecipeCategory;
import umpaz.nethersdelight.integration.jei.resource.CompositionDummy;
import vectorwing.farmersdelight.integration.jei.FDRecipes;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class NDJEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = new ResourceLocation(NethersDelight.MODID, "jei_plugin");

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new CompositionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        FDRecipes modRecipes = new FDRecipes();
        registration.addRecipes(NDRecipeTypes.COMPOSITION, ImmutableList.of(new CompositionDummy()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(NDItems.BLACKSTONE_STOVE.get()), RecipeTypes.CAMPFIRE_COOKING);
        registration.addRecipeCatalyst(new ItemStack(NDItems.BLACKSTONE_FURNACE.get()), RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(NDItems.NETHER_BRICK_SMOKER.get()), RecipeTypes.SMOKING);
        registration.addRecipeCatalyst(new ItemStack(NDItems.BLACKSTONE_BLAST_FURNACE.get()), RecipeTypes.BLASTING);
        registration.addRecipeCatalyst(new ItemStack(NDBlocks.SOUL_COMPOST.get()), NDRecipeTypes.COMPOSITION);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}