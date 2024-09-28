package net.lyof.phantasm.world.feature;

import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.block.ModBlocks;
import net.lyof.phantasm.world.feature.custom.*;
import net.lyof.phantasm.world.feature.custom.config.BoulderFeatureConfig;
import net.lyof.phantasm.world.feature.custom.config.CrystalSpikeFeatureConfig;
import net.lyof.phantasm.world.feature.custom.config.DralgaeFeatureConfig;
import net.lyof.phantasm.world.feature.custom.config.SingleBlockFeatureConfig;
import net.lyof.phantasm.world.feature.custom.tree.custom.PreamFoliagePlacer;
import net.lyof.phantasm.world.feature.custom.tree.custom.PreamTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;

import java.util.List;

public class ModConfiguredFeatures {
    public static RegistryKey<ConfiguredFeature<?, ?>> create(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Phantasm.makeID(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                  RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, PREAM, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.PREAM_LOG),
                new PreamTrunkPlacer(3, 2, 6),
                BlockStateProvider.of(ModBlocks.PREAM_LEAVES),
                new PreamFoliagePlacer(UniformIntProvider.create(3, 5), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 2)
                ).dirtProvider(BlockStateProvider.of(Blocks.END_STONE))
                .decorators(List.of(
                        new AttachedToLeavesTreeDecorator(
                                0.2f,
                                1,
                                0,
                                new WeightedBlockStateProvider(
                                        DataPool.of(ModBlocks.HANGING_PREAM_LEAVES.getDefaultState())
                                ),
                                1,
                                List.of(Direction.DOWN)
                        )
                )
        ).build());

        register(context, CRYSTAL_SPIKE, CrystalSpikeFeature.INSTANCE,
                new CrystalSpikeFeatureConfig(UniformIntProvider.create(3, 5), 0.3f));

        register(context, FALLEN_STAR, SingleBlockFeature.INSTANCE,
                new SingleBlockFeatureConfig(UniformIntProvider.create(110, 180), BlockStateProvider.of(ModBlocks.FALLEN_STAR)));

        register(context, VIVID_NIHILIS, Feature.FLOWER, new RandomPatchFeatureConfig(
                48, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.VIVID_NIHILIS)))));

        register(context, TALL_VIVID_NIHILIS, Feature.FLOWER, new RandomPatchFeatureConfig(
                20, 10, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.TALL_VIVID_NIHILIS)))));

        register(context, STARFLOWER, Feature.FLOWER, new RandomPatchFeatureConfig(
                2, 8, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.STARFLOWER)))));

        register(context, OBSIDIAN_TOWER, ObsidianTowerStructure.INSTANCE,
                new CountConfig(UniformIntProvider.create(30, 50)));

        register(context, OBLIVINE, OblivineFeature.INSTANCE,
                BlockColumnFeatureConfig.create(UniformIntProvider.create(5, 8), BlockStateProvider.of(ModBlocks.OBLIVINE)));

        register(context, ACIDIC_NIHILIS, Feature.FLOWER, new RandomPatchFeatureConfig(
                48, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ACIDIC_NIHILIS)))));

        register(context, TALL_ACIDIC_NIHILIS, Feature.FLOWER, new RandomPatchFeatureConfig(
                20, 10, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.TALL_ACIDIC_NIHILIS)))));

        register(context, DRALGAE, DralgaeFeature.INSTANCE,
                new DralgaeFeatureConfig(UniformIntProvider.create(5, 10), BlockStateProvider.of(ModBlocks.DRALGAE),
                        BlockStateProvider.of(ModBlocks.DRALGAE)));

        register(context, TALL_DRALGAE, DralgaeFeature.INSTANCE,
                new DralgaeFeatureConfig(UniformIntProvider.create(5, 20), BlockStateProvider.of(ModBlocks.DRALGAE),
                        BlockStateProvider.of(ModBlocks.POME)));

        register(context, HUGE_DRALGAE, HugeDralgaeFeature.INSTANCE,
                new DralgaeFeatureConfig(UniformIntProvider.create(15, 30), BlockStateProvider.of(Blocks.OBSIDIAN),
                        BlockStateProvider.of(ModBlocks.ACIDIC_MASS)));

        register(context, CIRITE, BoulderFeature.INSTANCE,
                new BoulderFeatureConfig(UniformIntProvider.create(3, 7), new WeightedBlockStateProvider(
                        DataPool.<BlockState>builder().add(ModBlocks.CIRITE.getDefaultState(), 7)
                                .add(ModBlocks.CIRITE_IRON_ORE.getDefaultState(), 1))));
    }



    public static final RegistryKey<ConfiguredFeature<?, ?>> PREAM = create("pream");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CRYSTAL_SPIKE = create("crystal_spike");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_STAR = create("fallen_star");

    public static final RegistryKey<ConfiguredFeature<?, ?>> VIVID_NIHILIS = create("patch_vivid_nihilis");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_VIVID_NIHILIS = create("patch_tall_vivid_nihilis");

    public static final RegistryKey<ConfiguredFeature<?, ?>> STARFLOWER = create("patch_starflower");

    public static final RegistryKey<ConfiguredFeature<?, ?>> OBSIDIAN_TOWER = create("obsidian_tower");

    public static final RegistryKey<ConfiguredFeature<?, ?>> OBLIVINE = create("patch_oblivine");


    public static final RegistryKey<ConfiguredFeature<?, ?>> ACIDIC_NIHILIS = create("patch_acidic_nihilis");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_ACIDIC_NIHILIS = create("patch_tall_acidic_nihilis");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DRALGAE = create("dralgae");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_DRALGAE = create("tall_dralgae");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_DRALGAE = create("huge_dralgae");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CIRITE = create("cirite_boulder");
}
