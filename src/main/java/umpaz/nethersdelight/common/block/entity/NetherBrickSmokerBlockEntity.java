package umpaz.nethersdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import umpaz.nethersdelight.common.registry.NDBlockEntityTypes;
import umpaz.nethersdelight.common.utility.NDTextUtils;

public class NetherBrickSmokerBlockEntity extends AbstractFurnaceBlockEntity {

    public NetherBrickSmokerBlockEntity(BlockPos pos, BlockState state) {
        super(NDBlockEntityTypes.NETHER_BRICK_SMOKER.get(), pos, state, RecipeType.SMOKING);
    }

    @Override
    protected Component getDefaultName() {
        return NDTextUtils.getTranslation("container.nether_brick_smoker");
    }

    @Override
    protected int getBurnDuration(ItemStack itemStack) {
        return super.getBurnDuration(itemStack) / 2;
    }

    @Override
    protected AbstractContainerMenu createMenu(int recipeBook, Inventory inventory) {
        return new SmokerMenu(recipeBook, inventory, this, this.dataAccess);
    }
}