package io.github.affable_armours;

import io.github.affable_armours.armours.Armours;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AffableArmours implements ModInitializer {
	public static final String MOD_ID = "affable_armours";


	public static Identifier id(String path) {
		if (!path.startsWith(MOD_ID + ":")) {
			return new Identifier(idString(path));
		} else {
			return new Identifier(path);
		}
	}

	public static String idString(String path) {
		return MOD_ID + ":" + path;
	}
	public static final Logger LOGGER = LoggerFactory.getLogger("Affable Mod");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		Armours.register();
	}
}
