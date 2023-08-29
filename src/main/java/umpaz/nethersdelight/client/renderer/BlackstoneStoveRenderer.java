package umpaz.nethersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import umpaz.nethersdelight.common.block.BlackstoneStoveBlock;
import umpaz.nethersdelight.common.block.entity.BlackstoneStoveBlockEntity;
import org.joml.AxisAngle4f;
import java.lang.Math;

public class BlackstoneStoveRenderer implements BlockEntityRenderer<BlackstoneStoveBlockEntity>
{
    public BlackstoneStoveRenderer(BlockEntityRendererProvider.Context context) {
    }

    private static final Quaternionf itemPoseXRotation = new Quaternionf(new AxisAngle4f((float)Math.toRadians(90), 1F, 0F, 0F));

    @Override
    public void render(BlackstoneStoveBlockEntity blackstoneStoveEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = blackstoneStoveEntity.getBlockState().getValue(BlackstoneStoveBlock.FACING).getOpposite();

        ItemStackHandler inventory = blackstoneStoveEntity.getInventory();
        int posLong = (int) blackstoneStoveEntity.getBlockPos().asLong();

        for (int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack stoveStack = inventory.getStackInSlot(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();

                // Center item above the stove
                poseStack.translate(0.5D, 1.02D, 0.5D);

                // Rotate item to face the stove's front side
                float f = (float)Math.toRadians(-direction.toYRot());
                poseStack.mulPose(new Quaternionf(new AxisAngle4f(f, 0F, 1F, 0F)));

                // Rotate item flat on the stove. Use X and Y from now on
                poseStack.mulPose(itemPoseXRotation);

                // Neatly align items according to their index
                Vec2 itemOffset = blackstoneStoveEntity.getStoveItemOffset(i);
                poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

                // Resize the items
                poseStack.scale(0.375F, 0.375F, 0.375F);

                if (blackstoneStoveEntity.getLevel() != null)
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemTransforms.TransformType.FIXED, LevelRenderer.getLightColor(blackstoneStoveEntity.getLevel(), blackstoneStoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, posLong + i);
                poseStack.popPose();
            }
        }
    }
}
