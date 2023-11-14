package umpaz.nethersdelight.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import umpaz.nethersdelight.common.registry.NDBlocks;

@Mixin(RootsBlock.class)
public class RootsBlockMixin {
    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(NDBlocks.RICH_SOUL_SOIL.get())) {
            cir.setReturnValue(true);
        }
    }
}
