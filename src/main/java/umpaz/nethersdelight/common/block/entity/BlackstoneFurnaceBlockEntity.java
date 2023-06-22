package umpaz.nethersdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import umpaz.nethersdelight.common.registry.NDBlockEntityTypes;
import umpaz.nethersdelight.common.utility.NDTextUtils;

public class BlackstoneFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public BlackstoneFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(NDBlockEntityTypes.BLACKSTONE_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return NDTextUtils.getTranslation("container.blackstone_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int recipeBook, Inventory inventory) {
        return new FurnaceMenu(recipeBook, inventory, this, this.dataAccess);
    }
}