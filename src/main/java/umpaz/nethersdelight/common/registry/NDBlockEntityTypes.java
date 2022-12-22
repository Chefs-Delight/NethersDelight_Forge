package umpaz.nethersdelight.common.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.NethersDelight;
import umpaz.nethersdelight.common.block.BlackstoneBlastFurnaceBlock;
import umpaz.nethersdelight.common.block.entity.BlackstoneBlastFurnaceBlockEntity;
import umpaz.nethersdelight.common.block.entity.BlackstoneFurnaceBlockEntity;
import umpaz.nethersdelight.common.block.entity.BlackstoneStoveBlockEntity;
import umpaz.nethersdelight.common.block.entity.NetherBrickSmokerBlockEntity;

public class NDBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NethersDelight.MODID);

    public static final RegistryObject<BlockEntityType<BlackstoneStoveBlockEntity>> BLACKSTONE_STOVE = TILES.register("blackstone_stove",
            () -> BlockEntityType.Builder.of(BlackstoneStoveBlockEntity::new, NDBlocks.BLACKSTONE_STOVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlackstoneFurnaceBlockEntity>> BLACKSTONE_FURNACE = TILES.register("blackstone_furnace",
            () -> BlockEntityType.Builder.of(BlackstoneFurnaceBlockEntity::new, NDBlocks.BLACKSTONE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<NetherBrickSmokerBlockEntity>> NETHER_BRICK_SMOKER = TILES.register("nether_brick_smoker",
            () -> BlockEntityType.Builder.of(NetherBrickSmokerBlockEntity::new, NDBlocks.NETHER_BRICK_SMOKER.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlackstoneBlastFurnaceBlockEntity>> BLACKSTONE_BLAST_FURNACE = TILES.register("blackstone_blast_furnace",
            () -> BlockEntityType.Builder.of(BlackstoneBlastFurnaceBlockEntity::new, NDBlocks.BLACKSTONE_BLAST_FURNACE.get()).build(null));
}
