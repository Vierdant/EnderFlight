package me.vierdant.enderflight.item;

import me.vierdant.enderflight.EnderFlight;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DRAGON_SCALE =
            registerItem("dragon_scale", new Item(new FabricItemSettings()));
    public static final Item SHULKER_SHARD =
            registerItem("shulker_shard", new Item(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(EnderFlight.MOD_ID, name), item);
    }

    public static void registerModItems() {
        EnderFlight.LOGGER.info("Registering Mod Items for " + EnderFlight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemGroups::modifyIngredientItemGroup);
    }
}
