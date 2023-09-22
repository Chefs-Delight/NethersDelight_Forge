package umpaz.nethersdelight.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import umpaz.nethersdelight.common.registry.NDBlocks;

@Mixin(FungusBlock.class)
public class FungusBlockMixin extends BushBlock {

    public FungusBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || state.is(Blocks.MYCELIUM) || state.is(Blocks.SOUL_SOIL) || state.is(NDBlocks.RICH_SOUL_SOIL.get()) || super.mayPlaceOn(state, level, pos);
    }
}
