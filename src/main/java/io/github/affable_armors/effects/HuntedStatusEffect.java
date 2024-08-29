package io.github.affable_armors.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class HuntedStatusEffect extends StatusEffect {
	protected HuntedStatusEffect(StatusEffectType type, int color) {
		super(StatusEffectType.HARMFUL, 0x000000);
	}
}
