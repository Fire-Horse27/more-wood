package net.fire_horse27.morewood.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final List<Item> MATERIAL = List.of(Items.OAK_LOG, Items.SPRUCE_LOG, Items.BIRCH_LOG,
            Items.JUNGLE_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.MANGROVE_LOG, Items.CHERRY_LOG,
            Items.PALE_OAK_LOG, Items.CRIMSON_STEM, Items.WARPED_STEM);

    private static final List<Item> WOOD = List.of(Items.OAK_WOOD, Items.SPRUCE_WOOD, Items.BIRCH_WOOD,
            Items.JUNGLE_WOOD, Items.ACACIA_WOOD, Items.DARK_OAK_WOOD, Items.MANGROVE_WOOD,
            Items.CHERRY_WOOD, Items.PALE_OAK_WOOD, Items.CRIMSON_HYPHAE, Items.WARPED_HYPHAE);

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                for(int i = 0; i < 11; i++) {
                    createShaped(RecipeCategory.BUILDING_BLOCKS, WOOD.get(i), 4)
                            .pattern("##")
                            .pattern("##")
                            .group("bark")
                            .input('#', MATERIAL.get(i))
                            .criterion(hasItem(MATERIAL.get(i)), conditionsFromItem(MATERIAL.get(i)))
                            .offerTo(exporter);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
