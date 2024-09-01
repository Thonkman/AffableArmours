package io.github.affable_armors.mixin.client;

import io.github.affable_armors.armors.Armors;
import io.github.affable_armors.effects.Effects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(MinecraftClient.class)
public class MinecraftClientInject {
	@Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
	public void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		AtomicBoolean wrongArmor = new AtomicBoolean(false);
		MinecraftClient.getInstance().player.getAllArmorItems().forEach(
			item -> {
				if (!item.isOf(Armors.sculkBoots) && !item.isOf(Armors.sculkLeggings) && !item.isOf(Armors.sculkChestplate) && !item.isOf(Armors.sculkHelmet)) {
					wrongArmor.set(true);
				}
			}
		);

		if (!wrongArmor.get() && entity instanceof LivingEntity living && living.hasStatusEffect(Effects.E)) {
			cir.setReturnValue(true);
		}
	}
}
