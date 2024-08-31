package net.lyof.phantasm.world.feature;

import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatures {
    public static RegistryKey<PlacedFeature> create(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Phantasm.makeID(name));
    }

    private static void register(Registerable<PlacedFeature> context,
                                 RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static void register(Registerable<PlacedFeature> context,
                                 RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> config,
                                 PlacementModifier... modifiers) {
        register(context, key, config, List.of(modifiers));
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        List<PlacementModifier> modifiers = new ArrayList<>();
        //modifiers.add(RarityFilterPlacementModifier.of(2));
        modifiers.addAll(VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                PlacedFeatures.createCountExtraModifier(1, 0.5f, 2),
                ModBlocks.PREAM_SAPLING));


        register(context, PREAM, configLookup.getOrThrow(ModConfiguredFeatures.PREAM),
                modifiers);
        modifiers.add(RarityFilterPlacementModifier.of(6));
        register(context, TALL_PREAM, configLookup.getOrThrow(ModConfiguredFeatures.TALL_PREAM),
                modifiers);

        register(context, CRYSTAL_SPIKE, configLookup.getOrThrow(ModConfiguredFeatures.CRYSTAL_SPIKE),
                SquarePlacementModifier.of(),
                RarityFilterPlacementModifier.of(2));

        register(context, FALLEN_STAR, configLookup.getOrThrow(ModConfiguredFeatures.FALLEN_STAR),
                SquarePlacementModifier.of(),
                RarityFilterPlacementModifier.of(3));

        register(context, VIVID_NIHILIS, configLookup.getOrThrow(ModConfiguredFeatures.VIVID_NIHILIS),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, TALL_VIVID_NIHILIS, configLookup.getOrThrow(ModConfiguredFeatures.TALL_VIVID_NIHILIS),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, STARFLOWER_PATCH, configLookup.getOrThrow(ModConfiguredFeatures.STARFLOWER),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, OBSIDIAN_TOWER, configLookup.getOrThrow(ModConfiguredFeatures.OBSIDIAN_TOWER),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                RarityFilterPlacementModifier.of(3));

        register(context, OBLIVINE_PATCH, configLookup.getOrThrow(ModConfiguredFeatures.OBLIVINE),
                SquarePlacementModifier.of(),
                PlacedFeatures.createCountExtraModifier(10, 1, 3),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);

        register(context, ACIDIC_NIHILIS, configLookup.getOrThrow(ModConfiguredFeatures.ACIDIC_NIHILIS),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of());
    }



    public static final RegistryKey<PlacedFeature> PREAM = create("pream");
    public static final RegistryKey<PlacedFeature> TALL_PREAM = create("tall_pream");

    public static final RegistryKey<PlacedFeature> CRYSTAL_SPIKE = create("crystal_spike");
    public static final RegistryKey<PlacedFeature> FALLEN_STAR = create("fallen_star");

    public static final RegistryKey<PlacedFeature> VIVID_NIHILIS = create("patch_vivid_nihilis");
    public static final RegistryKey<PlacedFeature> TALL_VIVID_NIHILIS = create("patch_tall_vivid_nihilis");

    public static final RegistryKey<PlacedFeature> STARFLOWER_PATCH = create("patch_starflower");

    public static final RegistryKey<PlacedFeature> OBSIDIAN_TOWER = create("obsidian_tower");

    public static final RegistryKey<PlacedFeature> OBLIVINE_PATCH = create("patch_oblivine");

    public static final RegistryKey<PlacedFeature> ACIDIC_NIHILIS = create("patch_acidic_nihilis");
}
