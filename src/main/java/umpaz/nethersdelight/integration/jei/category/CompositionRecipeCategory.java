package umpaz.nethersdelight.integration.jei.category;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.tutorial.BundleTutorial;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.registry.NDBlocks;
import umpaz.nethersdelight.common.registry.NDItems;
import umpaz.nethersdelight.common.tag.NDTags;
import umpaz.nethersdelight.common.utility.NDTextUtils;
import umpaz.nethersdelight.integration.jei.NDRecipeTypes;
import umpaz.nethersdelight.integration.jei.resource.CompositionDummy;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CompositionRecipeCategory implements IRecipeCategory<CompositionDummy>
{
    public static final ResourceLocation UID = new ResourceLocation(FarmersDelight.MODID, "composition");
    private static final int slotSize = 22;

    private final Component title;
    private final IDrawable background;
    private final IDrawable slotIcon;
    private final IDrawable icon;
    private final ItemStack soulCompost;
    private final ItemStack richSoulSoil;

    public CompositionRecipeCategory(IGuiHelper helper) {
        title = NDTextUtils.getTranslation("jei.composition");
        ResourceLocation backgroundImage = new ResourceLocation(NethersDelight.MODID, "textures/gui/jei/composition.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 118, 80);
        soulCompost = new ItemStack(NDBlocks.SOUL_COMPOST.get());
        richSoulSoil = new ItemStack(NDItems.RICH_SOUL_SOIL.get());
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, richSoulSoil);
        slotIcon = helper.createDrawable(backgroundImage, 119, 0, slotSize, slotSize);
    }

    @Override
    public RecipeType<CompositionDummy> getRecipeType() {
        return NDRecipeTypes.COMPOSITION;
    }

    @Override
    public Component getTitle() {
        return this.title;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompositionDummy recipe, IFocusGroup focusGroup) {
        List<ItemStack> accelerators = ForgeRegistries.BLOCKS.tags().getTag(NDTags.SOUL_COMPOST_ACTIVATORS).stream().map(ItemStack::new).collect(Collectors.toList());
        List<ItemStack> flames = ForgeRegistries.BLOCKS.tags().getTag(NDTags.SOUL_COMPOST_FLAMES).stream().map(ItemStack::new).collect(Collectors.toList());

        builder.addSlot(RecipeIngredientRole.INPUT, 9, 26).addItemStack(soulCompost);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 93, 26).addItemStack(richSoulSoil);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 64, 54).addItemStacks(accelerators);
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 38, 54).addItemStacks(flames);
    }

    @Override
    public void draw(CompositionDummy recipe, IRecipeSlotsView recipeSlotsView, PoseStack ms, double mouseX, double mouseY) {
        this.slotIcon.draw(ms, 63, 53);
        this.slotIcon.draw(ms, 37, 53);
    }

    @Override
    public List<Component> getTooltipStrings(CompositionDummy recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        if (inIconAt(40, 38, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".light"));
        }
        if (inIconAt(53, 38, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".fluid"));
        }
        if (inIconAt(67, 38, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".accelerators"));
        }
        if (inIconOn(49, 9, mouseX, mouseY)) {
            return ImmutableList.of(translateKey(".nether"));
        }
        return Collections.emptyList();
    }

    private static boolean inIconAt(int iconX, int iconY, double mouseX, double mouseY) {
        final int icon_size = 11;
        return iconX <= mouseX && mouseX < iconX + icon_size && iconY <= mouseY && mouseY < iconY + icon_size;
    }

    private static boolean inIconOn(int iconX, int iconY, double mouseX, double mouseY) {
        return iconX <= mouseX && mouseX < iconX + 16 && iconY <= mouseY && mouseY < iconY + 19;
    }

    private static MutableComponent translateKey(@Nonnull String suffix) {
        return Component.translatable(NethersDelight.MODID + ".jei.composition" + suffix);
    }
}