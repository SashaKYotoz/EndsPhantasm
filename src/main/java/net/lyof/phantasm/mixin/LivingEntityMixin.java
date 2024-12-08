package net.lyof.phantasm.mixin;

import net.lyof.phantasm.effect.ModEffects;
import net.lyof.phantasm.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);
    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    public void keepOblifruit(ItemStack instance, int amount) {
        if (instance.isOf(ModItems.OBLIFRUIT) && Math.random() < 0.05 && instance.getCount() < instance.getMaxCount()) {
            instance.increment(1);
            return;
        }
        if (instance.isOf(ModItems.OBLIFRUIT) && Math.random() < 0.4) return;
        instance.decrement(1);
    }

    @Inject(method = "modifyAppliedDamage", at = @At("RETURN"), cancellable = true)
    public void applyVulnerability(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if (!this.hasStatusEffect(ModEffects.CORROSION)) return;

        int i = this.getStatusEffect(ModEffects.CORROSION).getAmplifier() + 1;
        cir.setReturnValue(amount * (1 + 0.2f * i));
    }

    @Inject(method = "travel", at = @At("TAIL"))
    public void lowGravity(Vec3d movementInput, CallbackInfo ci) {
        Vec3d velocity = this.getVelocity();
        double y = velocity.y;
        if (y != 0 && !this.hasNoGravity() && this.hasStatusEffect(ModEffects.FLOATATION) && !this.isSneaking()) {
            y = (y / 0.98) + 0.08 - 0.02;

            this.setVelocity(velocity.x, y, velocity.z);
        }
    }

    @Inject(method = "computeFallDamage", at = @At("RETURN"), cancellable = true)
    public void lowGravityFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Integer> cir) {
        if (this.hasStatusEffect(ModEffects.FLOATATION)) cir.setReturnValue(cir.getReturnValue() / 4);
    }
}
