package net.lyof.phantasm.entity.custom;

import net.lyof.phantasm.effect.ModEffects;
import net.lyof.phantasm.entity.ModEntities;
import net.lyof.phantasm.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class ChoralArrowEntity extends ArrowEntity {
    public ChoralArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    public int lifetime = 0;

    public static ChoralArrowEntity create(World world, LivingEntity shooter) {
        ChoralArrowEntity arrow = create(world, shooter.getX(), shooter.getEyeY() - 0.10000000149011612D, shooter.getZ());
        arrow.setOwner(shooter);
        return arrow;
    }

    public static ChoralArrowEntity create(World world, double x, double y, double z) {
        ChoralArrowEntity arrow = new ChoralArrowEntity(ModEntities.CHORAL_ARROW, world);
        arrow.setPosition(x, y, z);
        return arrow;
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.addStatusEffect(new StatusEffectInstance(ModEffects.CHARM, 40, 0));
    }

    @Override
    public ItemStack asItemStack() {
        return ModItems.CHORAL_ARROW.getDefaultStack();
    }

    @Override
    protected float getDragInWater() {
        return 0.2f;
    }

    @Override
    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        super.setVelocity(x, y, z, speed * 0.75f, divergence * 4f);
    }

    @Override
    public boolean isCritical() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient() && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.NOTE,
                    this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D),
                    this.lifetime / 20f, 0, 0);
        }
        this.lifetime++;
    }
}
