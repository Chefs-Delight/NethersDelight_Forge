package umpaz.nethersdelight.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import umpaz.nethersdelight.common.registry.NDBlockEntityTypes;

public class BlackstoneStoveBlockEntity extends AbstractStoveBlockEntity<CampfireCookingRecipe, RecipeType<CampfireCookingRecipe>> {

    public BlackstoneStoveBlockEntity(BlockPos pos, BlockState state) {
        super(
                NDBlockEntityTypes.BLACKSTONE_STOVE.get(),
                6,
                RecipeType.CAMPFIRE_COOKING,
                pos,
                state,
                new Vec2[]{
                        new Vec2(0.3F, 0.2F),
                        new Vec2(0.0F, 0.2F),
                        new Vec2(-0.3F, 0.2F),
                        new Vec2(0.3F, -0.2F),
                        new Vec2(0.0F, -0.2F),
                        new Vec2(-0.3F, -0.2F)
                }
        );
    }
}
