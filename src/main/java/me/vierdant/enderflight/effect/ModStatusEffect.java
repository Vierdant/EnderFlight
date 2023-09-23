package me.vierdant.enderflight.effect;

import me.vierdant.enderflight.EnderFlight;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffect {
    public static final StatusEffect FLIGHT = new FlightStatusEffect();

    public static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnderFlight.MOD_ID, "flight"), FLIGHT);
    }

}
