package net.lyof.phantasm.setup;

import net.lyof.phantasm.Phantasm;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> XP_BOOSTED = create("gets_xp_speed_boost");


        private static TagKey<Item> create(String id) {
            return TagKey.of(RegistryKeys.ITEM, Phantasm.makeID(id));
        }
    }

    public static class Blocks {



        private static TagKey<Block> create(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Phantasm.makeID(id));
        }
    }
}
