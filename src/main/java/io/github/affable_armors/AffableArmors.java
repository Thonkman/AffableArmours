package io.github.affable_armors;

import io.github.affable_armors.armors.Armors;
import io.github.affable_armors.effects.Effects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AffableArmors implements ModInitializer {
	public static final String MOD_ID = "affable_armors";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		Armors.register();
		Effects.register();

		PayloadTypeRegistry.playC2S().register(AffableNetworking.CheckEntityPayload.ID, AffableNetworking.CheckEntityPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(AffableNetworking.ResultPayload.ID, AffableNetworking.ResultPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(AffableNetworking.CheckEntityPayload.ID, ((payload, context) -> {
			String checkEntity = payload.checkEntity();

			LivingEntity entity = (LivingEntity) context.player().getServerWorld().getEntity(UUID.fromString(checkEntity));
			boolean checkStatus = entity.hasStatusEffect(Effects.E);
			if (entity.hasStatusEffect(Effects.E)) {
				ServerPlayNetworking.send(context.player(), new AffableNetworking.ResultPayload(true));
			}
		}));
	}
}
