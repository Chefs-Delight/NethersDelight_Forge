package umpaz.nethersdelight.common.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.loot.modifier.ReplaceItemModifier;

public class NDLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, NethersDelight.MODID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> REPLACE_ITEM = LOOT_MODIFIERS.register("replace_item", ReplaceItemModifier.CODEC);
}
