package io.github.affable_armours.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityInvoker {

	@SuppressWarnings("UnusedReturnValue")
	@Invoker
	boolean invokeAddStatusEffect(StatusEffectInstance statusEffectInstance);


}
