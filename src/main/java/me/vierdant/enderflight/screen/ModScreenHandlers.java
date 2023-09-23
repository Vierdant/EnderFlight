package me.vierdant.enderflight.screen;

import me.vierdant.enderflight.EnderFlight;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<FlightAnchorScreenHandler> FLIGHT_ANCHOR_SCREEN_HANDLER
            = new ScreenHandlerType<>(FlightAnchorScreenHandler::new, FeatureSet.empty());

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(EnderFlight.MOD_ID, "flight_anchor"), FLIGHT_ANCHOR_SCREEN_HANDLER);
    }
}
