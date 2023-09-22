package umpaz.nethersdelight.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import umpaz.nethersdelight.common.registry.NDBlocks;

@Mixin(FungusBlock.class)
public class FungusBlockMixin {
    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(NDBlocks.SOUL_COMPOST.get()) || state.is(NDBlocks.RICH_SOUL_SOIL.get())) {
            cir.setReturnValue(true);
        }
    }
}
