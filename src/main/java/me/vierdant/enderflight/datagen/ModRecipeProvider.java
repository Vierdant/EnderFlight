package me.vierdant.enderflight.datagen;

import me.vierdant.enderflight.block.ModBlocks;
import me.vierdant.enderflight.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FLIGHT_ANCHOR, 1)
                .pattern("DDD")
                .pattern("DSD")
                .pattern("OOO")
                .input('D', ModItems.DRAGON_SCALE)
                .input('S', ModItems.SHULKER_SHARD)
                .input('O', Items.CRYING_OBSIDIAN)
                .criterion(hasItem(ModItems.SHULKER_SHARD), conditionsFromItem(ModItems.SHULKER_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FLIGHT_ANCHOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHORATED_SHULKER, 1)
                .pattern("ADA")
                .pattern("DSD")
                .pattern("ADA")
                .input('D', Items.POPPED_CHORUS_FRUIT)
                .input('S', ModItems.SHULKER_SHARD)
                .input('A', Items.AIR)
                .criterion(hasItem(ModItems.SHULKER_SHARD), conditionsFromItem(ModItems.SHULKER_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CHORATED_SHULKER)));
    }
}
