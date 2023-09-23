package me.vierdant.enderflight;

import me.vierdant.enderflight.screen.FlightAnchorScreen;
import me.vierdant.enderflight.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class EnderFlightClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.FLIGHT_ANCHOR_SCREEN_HANDLER, FlightAnchorScreen::new);
    }
}
