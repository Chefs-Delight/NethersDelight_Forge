package umpaz.nethersdelight.integration.jei;

import mezz.jei.api.recipe.RecipeType;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.integration.jei.resource.CompositionDummy;

public class NDRecipeTypes {
	public static final RecipeType<CompositionDummy> COMPOSITION = RecipeType.create(NethersDelight.MODID, "composition", CompositionDummy.class);
}