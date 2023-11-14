package umpaz.nethersdelight.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import umpaz.nethersdelight.common.block.FungusColonyBlock;

import javax.annotation.Nullable;

public class FungusColonyItem extends BlockItem
{
    public FungusColonyItem(Block blockIn, Properties properties) {
        super(blockIn, properties);
    }

    @Override
    @Nullable
    protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState originalState = this.getBlock().getStateForPlacement(context);
        if (originalState != null) {
            BlockState matureState = originalState.setValue(FungusColonyBlock.COLONY_AGE, 3);
            return this.canPlace(context, matureState) ? matureState : null;
        }
        return null;
    }
}