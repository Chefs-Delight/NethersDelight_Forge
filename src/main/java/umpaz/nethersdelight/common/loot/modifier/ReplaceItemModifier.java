package umpaz.nethersdelight.common.loot.modifier;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

public class ReplaceItemModifier extends LootModifier {
    public static final Supplier<Codec<ReplaceItemModifier>> CODEC = Suppliers.memoize(() ->
        RecordCodecBuilder.create(inst -> codecStart(inst).and(
            inst.group(
                Codec.INT.fieldOf("quantityOffset").forGetter(m -> m.replacementQuantityOffset),
                Codec.FLOAT.fieldOf("quantityScaleFactor").forGetter(m -> m.replacementQuantityScaleFactor),
                ForgeRegistries.ITEMS.getCodec().fieldOf("itemToReplace").forGetter(m -> m.itemToReplace),
                ForgeRegistries.ITEMS.getCodec().fieldOf("replacementItem").forGetter(m -> m.itemToReplaceWith)
            )).apply(inst, ReplaceItemModifier::new)
        ));

    private final int replacementQuantityOffset;
    private final float replacementQuantityScaleFactor;
    private final Item itemToReplace;
    private final Item itemToReplaceWith;


    public ReplaceItemModifier(
        LootItemCondition[] conditionsIn,
        int quantityOffset,
        float quantityScaleFactor,
        Item itemToReplace,
        Item replacementItem
    ) {
        super(conditionsIn);
        this.replacementQuantityOffset = quantityOffset;
        this.replacementQuantityScaleFactor = quantityScaleFactor;
        this.itemToReplace = itemToReplace;
        this.itemToReplaceWith = replacementItem;
    }

    @NotNull
    @Override
    public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        int numItemToReplace = generatedLoot.stream().filter(stack -> stack.getItem() == itemToReplace).mapToInt(ItemStack::getCount).sum();
        generatedLoot.removeIf(x -> x.getItem() == itemToReplace);

        int quantityOfReplacementItemToAdd = Math.max(Math.round(numItemToReplace * replacementQuantityScaleFactor) + replacementQuantityOffset, 0);
        if (quantityOfReplacementItemToAdd == 0) return generatedLoot;

        generatedLoot.add(
            new ItemStack(itemToReplaceWith, quantityOfReplacementItemToAdd)
        );

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
