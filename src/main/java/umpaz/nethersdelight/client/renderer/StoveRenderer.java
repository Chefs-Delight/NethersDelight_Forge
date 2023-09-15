package umpaz.nethersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import umpaz.nethersdelight.common.block.entity.AbstractStoveBlockEntity;
import vectorwing.farmersdelight.common.block.StoveBlock;

public class StoveRenderer<T extends AbstractStoveBlockEntity<?,?>> implements BlockEntityRenderer<T> {
    public StoveRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(T stoveEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = ((Direction)stoveEntity.getBlockState().getValue(StoveBlock.FACING)).getOpposite();
        NonNullList<ItemStack> inventory = stoveEntity.getItems();
        int posLong = (int)stoveEntity.getBlockPos().asLong();

        for(int i = 0; i < inventory.size(); ++i) {
            ItemStack stoveStack = inventory.get(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1.02, 0.5);
                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                Vec2 itemOffset = stoveEntity.getStoveItemOffset(i);
                poseStack.translate((double)itemOffset.x, (double)itemOffset.y, 0.0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                if (stoveEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(stoveEntity.getLevel(), stoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, stoveEntity.getLevel(), posLong + i);
                }

                poseStack.popPose();
            }
        }

    }
}
