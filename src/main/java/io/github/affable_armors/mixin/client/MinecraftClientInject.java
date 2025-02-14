package io.github.affable_armors.mixin.client;

import io.github.affable_armors.AffableArmors;
import io.github.affable_armors.armors.Armors;
import io.github.affable_armors.effects.Effects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(MinecraftClient.class)
public class MinecraftClientInject {
	@Shadow
	@Final
	private static Logger LOGGER;

	@Inject(method = "hasOutline", at = @At("HEAD"))
	public void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		ItemStack headEquipment = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = player.getEquippedStack(EquipmentSlot.FEET);
		LivingEntity livingEntity = null;
		if (entity instanceof LivingEntity) {
			livingEntity = (LivingEntity) entity;
		}
		if (livingEntity != null) {
			Collection<StatusEffectInstance> effects = livingEntity.getStatusEffects();
			boolean bl1 = livingEntity.hasStatusEffect(Effects.E);
			boolean bl2 = headEquipment.isOf(Armors.sculkHelmet);
			boolean bl3 = chestEquipment.isOf(Armors.sculkChestplate);
			boolean bl4 = legEquipment.isOf(Armors.sculkLeggings);
			boolean bl5 = feetEquipment.isOf(Armors.sculkBoots);
			if (headEquipment.isOf(Armors.sculkHelmet) && chestEquipment.isOf(Armors.sculkChestplate) && legEquipment.isOf(Armors.sculkLeggings) && feetEquipment.isOf(Armors.sculkBoots) && livingEntity.hasStatusEffect(Effects.E)) {
				cir.setReturnValue(true);
				LOGGER.info(livingEntity.toString());
			}
		}


	}
}
