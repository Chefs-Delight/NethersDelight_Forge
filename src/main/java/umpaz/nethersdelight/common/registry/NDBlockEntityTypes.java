package umpaz.nethersdelight.common.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.block.entity.*;

public class NDBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NethersDelight.MODID);

    public static final RegistryObject<BlockEntityType<BlackstoneStoveBlockEntity>> BLACKSTONE_STOVE = BLOCK_ENTITY_TYPES.register("blackstone_stove",
            () -> BlockEntityType.Builder.of(BlackstoneStoveBlockEntity::new, NDBlocks.BLACKSTONE_STOVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlackstoneFurnaceBlockEntity>> BLACKSTONE_FURNACE = BLOCK_ENTITY_TYPES.register("blackstone_furnace",
            () -> BlockEntityType.Builder.of(BlackstoneFurnaceBlockEntity::new, NDBlocks.BLACKSTONE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<NetherBrickSmokerBlockEntity>> NETHER_BRICK_SMOKER = BLOCK_ENTITY_TYPES.register("nether_brick_smoker",
            () -> BlockEntityType.Builder.of(NetherBrickSmokerBlockEntity::new, NDBlocks.NETHER_BRICK_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlackstoneBlastFurnaceBlockEntity>> BLACKSTONE_BLAST_FURNACE = BLOCK_ENTITY_TYPES.register("blackstone_blast_furnace",
            () -> BlockEntityType.Builder.of(BlackstoneBlastFurnaceBlockEntity::new, NDBlocks.BLACKSTONE_BLAST_FURNACE.get()).build(null));
}
