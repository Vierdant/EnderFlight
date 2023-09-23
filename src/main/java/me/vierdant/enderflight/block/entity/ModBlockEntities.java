package me.vierdant.enderflight.block.entity;

import me.vierdant.enderflight.EnderFlight;
import me.vierdant.enderflight.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<FlightAnchorBlockEntity> FLIGHT_ANCHOR;

    public static void registerBlockEntities() {
        FLIGHT_ANCHOR = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(EnderFlight.MOD_ID, "flight_anchor"),
                FabricBlockEntityTypeBuilder.create(FlightAnchorBlockEntity::new,
                ModBlocks.FLIGHT_ANCHOR).build(null));
    }
}
