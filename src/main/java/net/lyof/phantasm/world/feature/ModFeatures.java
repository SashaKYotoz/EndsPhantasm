package net.lyof.phantasm.world.feature;

import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.world.feature.custom.CrystalSpikeFeature;
import net.lyof.phantasm.world.feature.custom.OblivineFeature;
import net.lyof.phantasm.world.feature.custom.ObsidianTowerStructure;
import net.lyof.phantasm.world.feature.custom.SingleBlockFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModFeatures {
    public static void register() {
        Phantasm.log("Registering Features for modid : " + Phantasm.MOD_ID);

        Registry.register(Registries.FEATURE, Phantasm.makeID("crystal_spike"), CrystalSpikeFeature.INSTANCE);
        Registry.register(Registries.FEATURE, Phantasm.makeID("single_block"), SingleBlockFeature.INSTANCE);
        Registry.register(Registries.FEATURE, Phantasm.makeID("obsidian_tower"), ObsidianTowerStructure.INSTANCE);
        Registry.register(Registries.FEATURE, Phantasm.makeID("oblivine"), OblivineFeature.INSTANCE);
    }
}
