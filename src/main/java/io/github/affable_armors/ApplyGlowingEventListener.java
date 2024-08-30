package io.github.affable_armors;

import io.github.affable_armors.effects.Effects;
import io.github.affable_armors.mixin.LivingEntityInvoker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Holder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.PositionSource;
import net.minecraft.world.event.listener.GameEventListener;

public record ApplyGlowingEventListener(PositionSource positionSource, int range) implements GameEventListener {
	@Override
	public PositionSource getPositionSource() {
		return this.positionSource;
	}

	@Override
	public int getRange() {
		return this.range;
	}

	@Override
	public boolean listen(ServerWorld world, Holder<GameEvent> event, GameEvent.Context context, Vec3d pos) {
		if (event.equals(GameEvent.SCULK_SENSOR_TENDRILS_CLICKING)) {
			((LivingEntityInvoker) context.sourceEntity()).invokeAddStatusEffect(new StatusEffectInstance(Effects.E, 100, 0, false, true, true));
			return true;
		}

		return false;
	}

	@Override
	public DeliveryMode getDeliveryMode() {
		return DeliveryMode.UNSPECIFIED;
	}
}
