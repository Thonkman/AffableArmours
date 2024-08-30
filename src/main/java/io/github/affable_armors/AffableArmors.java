package io.github.affable_armors;

import io.github.affable_armors.armors.Armors;
import io.github.affable_armors.effects.Effects;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	}
}
