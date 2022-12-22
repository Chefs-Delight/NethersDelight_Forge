package umpaz.nethersdelight.common.utility;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class NDFoods
{
	
	public static final FoodProperties HOGLIN_LOIN = (new FoodProperties.Builder())
			.nutrition(3).saturationMod(0.25f)
			.meat().build();
	
	public static final FoodProperties HOGLIN_SIRLOIN = (new FoodProperties.Builder())
			.nutrition(7).saturationMod(0.75f).meat().build();
	
	public static final FoodProperties STRIDER_SLICE = (new FoodProperties.Builder())
			.nutrition(4).saturationMod(0.7f)
			.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0), 1.0F)
			.meat().build();
	
	public static final FoodProperties GRILLED_STRIDER = (new FoodProperties.Builder())
			.nutrition(10).saturationMod(0.9f)
			.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
			.effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 2400, 0), 1.0F)
			.build();
	
	public static final FoodProperties WARPED_MOLDY_MEAT = (new FoodProperties.Builder())
			.nutrition(9).saturationMod(0.8f)
			.effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200, 0), 1.0F)
			.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0F)
			.build();
	
	public static final FoodProperties GROUND_STRIDER = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(0.75f)
			.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1.0F)
			.effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0), 0.35F)
			.meat().build();
	
	public static final FoodProperties STRIDER_MOSS_STEW = (new FoodProperties.Builder())
			.nutrition(8).saturationMod(0.6f)
			.effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 2400, 0), 1.0F)
			.build();
	
	public static final FoodProperties HOGLIN_EAR = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(0.5f)
			.meat().fast().build();
	
	public static final FoodProperties PLATE_OF_STUFFED_HOGLIN_SNOUT = (new FoodProperties.Builder())
			.nutrition(14).saturationMod(0.9f)
			.effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 4800, 0), 1.0F)
			.build();
	
	public static final FoodProperties PLATE_OF_STUFFED_HOGLIN_HAM = (new FoodProperties.Builder())
			.nutrition(10).saturationMod(0.75f)
			.effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 2400, 0), 1.0F)
			.build();
	
	public static final FoodProperties PLATE_OF_STUFFED_HOGLIN_ROAST = (new FoodProperties.Builder())
			.nutrition(8).saturationMod(0.6f)
			.effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT.get(), 2400, 0), 1.0F)
			.build();
	
	public static final FoodProperties PROPELPEARL = (new FoodProperties.Builder())
			.nutrition(2).saturationMod(0.5f)
			.build();
	
	public static final FoodProperties NETHER_SKEWER = (new FoodProperties.Builder())
			.nutrition(7).saturationMod(0.5f)
			.build();
	
	public static final FoodProperties MAGMA_GELATIN = (new FoodProperties.Builder())
			.nutrition(1).saturationMod(6.0f)
			.effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)
			.build();
}
