package me.vierdant.enderflight.item;

import me.vierdant.enderflight.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

public class ModItemGroups {

    public static void modifyIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModItems.DRAGON_SCALE);
        entries.add(ModItems.SHULKER_SHARD);
    }

    public static void modifyFunctionalItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.FLIGHT_ANCHOR);
    }

    public static void modifyBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.CHORATED_SHULKER);
    }
}
