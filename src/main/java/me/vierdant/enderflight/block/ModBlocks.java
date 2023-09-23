package me.vierdant.enderflight.block;

import me.vierdant.enderflight.EnderFlight;
import me.vierdant.enderflight.block.custom.FlightAnchorBlock;
import me.vierdant.enderflight.item.ModItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block FLIGHT_ANCHOR = registerBlock("flight_anchor",
            new FlightAnchorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .luminance(state -> !state.get(FlightAnchorBlock.ACTIVE) ? 5 : 0)));

    public static Block CHORATED_SHULKER = registerBlock("chorated_shulker",
            new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(EnderFlight.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(EnderFlight.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        EnderFlight.LOGGER.info("Registering Blocks for " + EnderFlight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItemGroups::modifyFunctionalItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItemGroups::modifyBuildingBlocksItemGroup);
    }
}
