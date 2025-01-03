package net.lyof.phantasm.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lyof.phantasm.Phantasm;
import net.lyof.phantasm.block.ModBlocks;
import net.lyof.phantasm.config.ConfigEntries;
import net.lyof.phantasm.entity.ModEntities;
import net.lyof.phantasm.entity.custom.HarmonicArrowEntity;
import net.lyof.phantasm.item.custom.HarmonicArrowItem;
import net.lyof.phantasm.item.custom.ShatteredPendantItem;
import net.lyof.phantasm.setup.ModRegistry;
import net.lyof.phantasm.setup.ModTags;
import net.lyof.phantasm.sound.ModSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.client.particle.NoteParticle;
import net.minecraft.data.client.Models;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class ModItems {
    public static void register() {
        Phantasm.log("Registering Items for modid : " + Phantasm.MOD_ID);

        DispenserBlock.registerBehavior(HARMONIC_ARROW, new ProjectileDispenserBehavior() {
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                ArrowEntity arrowEntity = new HarmonicArrowEntity(world, position.getX(), position.getY(), position.getZ());
                arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return arrowEntity;
            }
        });
    }


    public static final Item PREAM_BERRY = ModRegistry.ofItem("pream_berry",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.PREAM_BERRY))).model().build();
    public static final Item OBLIFRUIT = ModRegistry.ofItem("oblifruit",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.OBLIFRUIT))).model().build();

    public static final Item CRYSTALLINE_SHOVEL = ModRegistry.ofItem("crystalline_shovel",
                    new ShovelItem(ModTiers.CRYSTALLINE, 2, -3f, new FabricItemSettings()))
            .model(Models.HANDHELD).tag(ModTags.Items.XP_BOOSTED, ItemTags.SHOVELS).build();
    public static final Item CRYSTALLINE_PICKAXE = ModRegistry.ofItem("crystalline_pickaxe",
            new PickaxeItem(ModTiers.CRYSTALLINE, 2, -2.8f, new FabricItemSettings()))
            .model(Models.HANDHELD).tag(ModTags.Items.XP_BOOSTED, ItemTags.PICKAXES).build();
    public static final Item CRYSTALLINE_AXE = ModRegistry.ofItem("crystalline_axe",
                    new AxeItem(ModTiers.CRYSTALLINE, 7, -3f, new FabricItemSettings()))
            .model(Models.HANDHELD).tag(ModTags.Items.XP_BOOSTED, ItemTags.AXES).build();
    public static final Item CRYSTALLINE_HOE = ModRegistry.ofItem("crystalline_hoe",
                    new HoeItem(ModTiers.CRYSTALLINE, 0, -3f, new FabricItemSettings()))
            .model(Models.HANDHELD).tag(ModTags.Items.XP_BOOSTED, ItemTags.HOES).build();
    public static final Item CRYSTALLINE_SWORD = ModRegistry.ofItem("crystalline_sword",
                    new SwordItem(ModTiers.CRYSTALLINE, 4, -2.4f, new FabricItemSettings()))
            .model(Models.HANDHELD).tag(ModTags.Items.XP_BOOSTED, ItemTags.SWORDS).build();

    public static final Item PREAM_SIGN = ModRegistry.ofItem("pream_sign",
                    new SignItem(new FabricItemSettings(), ModBlocks.PREAM_SIGN, ModBlocks.PREAM_WALL_SIGN))
            .fuel(200)
            .build();
    public static final Item PREAM_HANGING_SIGN = ModRegistry.ofItem("pream_hanging_sign",
                    new HangingSignItem(ModBlocks.PREAM_HANGING_SIGN, ModBlocks.PREAM_WALL_HANGING_SIGN, new FabricItemSettings()))
            .fuel(200)
            .build();

    public static final Item CHORUS_FRUIT_SALAD = ModRegistry.ofItem("chorus_fruit_salad",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.CHORUS_SALAD).recipeRemainder(Items.BOWL).maxCount(ConfigEntries.chorusSaladStack)) {
                @Override
                public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
                    if (world instanceof ServerWorld server && user.canUsePortals() && !user.isSneaking() && ConfigEntries.chorusSaladTp) {
                        RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
                        ServerWorld serverWorld = server.getServer().getWorld(registryKey);
                        if (serverWorld == null) {
                            return super.finishUsing(stack, world, user);
                        }
                        user.moveToWorld(serverWorld);
                    }

                    super.finishUsing(stack, world, user);
                    if (user instanceof PlayerEntity player) {
                        if (stack.isEmpty()) {
                            if (player.getInventory().contains(this.getRecipeRemainder(stack)))
                                player.giveItemStack(this.getRecipeRemainder(stack));
                            else
                                return this.getRecipeRemainder(stack);
                        }
                        else if (!player.isCreative())
                            player.giveItemStack(this.getRecipeRemainder(stack));
                    }
                    return stack;
                }
            })
            .model().build();

    public static final Item BEHEMOTH_MEAT = ModRegistry.ofItem("behemoth_meat",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.BEHEMOTH_MEAT)))
            .model().build();
    public static final Item BEHEMOTH_STEAK = ModRegistry.ofItem("behemoth_steak",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.BEHEMOTH_STEAK)))
            .model().build();

    public static final Item SHATTERED_PENDANT = ModRegistry.ofItem("shattered_pendant",
                new ShatteredPendantItem(new FabricItemSettings()))
            .model().build();


    public static final Item POME_SLICE = ModRegistry.ofItem("pome_slice",
            new Item(new FabricItemSettings().food(ModRegistry.Foods.POME_SLICE))).model().build();

    public static final Item MUSIC_DISC_ABRUPTION = ModRegistry.ofItem("music_disc_abruption",
            new MusicDiscItem(4, ModSounds.MUSIC_DISC_ABRUPTION, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 239))
            .model().tag(ItemTags.MUSIC_DISCS).build();

    public static final Item HARMONIC_ARROW = ModRegistry.ofItem("harmonic_arrow",
            new HarmonicArrowItem(new FabricItemSettings()))
            .model().tag(ItemTags.ARROWS).build();

    public static final Item CRYSTIE_SPAWN_EGG = ModRegistry.ofItem("crystie_spawn_egg",
                new SpawnEggItem(ModEntities.CRYSTIE, 0xfaf0ff, 0xa0a0ff, new FabricItemSettings()))
            .build();
    public static final Item BEHEMOTH_SPAWN_EGG = ModRegistry.ofItem("behemoth_spawn_egg",
                new SpawnEggItem(ModEntities.BEHEMOTH, 0xafa0ff, 0x0f000f, new FabricItemSettings()))
            .build();
}
