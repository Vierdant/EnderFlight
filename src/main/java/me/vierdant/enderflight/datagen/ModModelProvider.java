package me.vierdant.enderflight.datagen;

import me.vierdant.enderflight.block.ModBlocks;
import me.vierdant.enderflight.datagen.util.ModTextureMap;
import me.vierdant.enderflight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeWithCustomTextures(
                ModBlocks.FLIGHT_ANCHOR, null,
                (block, ignored) -> ModTextureMap.sideTopBottom(ModBlocks.FLIGHT_ANCHOR));

        blockStateModelGenerator.registerCubeWithCustomTextures(
                ModBlocks.CHORATED_SHULKER, null,
                (block, ignored) -> ModTextureMap.sideTopBottom(ModBlocks.CHORATED_SHULKER));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DRAGON_SCALE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHULKER_SHARD, Models.GENERATED);
    }
}
