package io.github.affable_armors.mixin.client;

import io.github.affable_armors.AffableArmors;
import io.github.affable_armors.AffableNetworking;
import io.github.affable_armors.armors.Armors;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientInject {
	@Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
	public void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		ItemStack headEquipment = player.getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = player.getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = player.getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = player.getEquippedStack(EquipmentSlot.FEET);
		LivingEntity livingEntity;
		if (entity instanceof LivingEntity) {
			livingEntity = (LivingEntity) entity;
			if (headEquipment.isOf(Armors.sculkHelmet) && chestEquipment.isOf(Armors.sculkChestplate) && legEquipment.isOf(Armors.sculkLeggings) && feetEquipment.isOf(Armors.sculkBoots)) {
				ClientPlayNetworking.send(new AffableNetworking.CheckEntityPayload(livingEntity.getUuidAsString()));
				ClientPlayNetworking.registerGlobalReceiver(AffableNetworking.ResultPayload.ID, ((payload, context) -> {
					AffableArmors.LOGGER.info("Received signal that entity is living, checking if true");
					if (payload.result()) {
						AffableArmors.LOGGER.info("Result is true sending true signal");
						cir.setReturnValue(true);
						AffableArmors.LOGGER.info("Sent true signal");
						//TODO: why the heck doesn't this do its job
						//something tells me this isn't going to work unless the check is performed entirely on the client?
						//I'm guessing that hasOutline runs every tick and having this run on the server isn't fast enough
						}
				}));
			}
		}
	}
}
