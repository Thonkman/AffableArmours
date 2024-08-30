package io.github.affable_armors.effects;

import io.github.affable_armors.AffableArmors;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class Effects {
	public static Holder<StatusEffect> E;

	public static void register() {
		E = Registry.registerHolder(Registries.STATUS_EFFECT, AffableArmors.id("hunted"), new HuntedStatusEffect());
	}
}
