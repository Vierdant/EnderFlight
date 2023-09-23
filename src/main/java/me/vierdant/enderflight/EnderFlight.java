package me.vierdant.enderflight;

import me.vierdant.enderflight.block.ModBlocks;
import me.vierdant.enderflight.block.entity.ModBlockEntities;
import me.vierdant.enderflight.effect.ModStatusEffect;
import me.vierdant.enderflight.item.ModItems;
import me.vierdant.enderflight.screen.ModScreenHandlers;
import me.vierdant.enderflight.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnderFlight implements ModInitializer {
	public static final String MOD_ID = "enderflight";
	public static final Logger LOGGER = LoggerFactory.getLogger("enderflight");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModStatusEffect.registerStatusEffects();
		ModLootTableModifiers.modifyLootTables();

		ModScreenHandlers.registerAllScreenHandlers();
	}
}