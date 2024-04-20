package io.github.affable_armours;

import io.github.affable_armours.mixin.LivingEntityInvoker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
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
	public boolean listen(ServerWorld world, GameEvent event, GameEvent.Context context, Vec3d pos) {
		if (event == GameEvent.SCULK_SENSOR_TENDRILS_CLICKING) {
			world.getEntitiesByType(TypeFilter.instanceOf(Entity.class), (d) -> d instanceof LivingEntity).forEach((e) -> {
				((LivingEntityInvoker) context.sourceEntity()).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 423, 0, false, true, true));
			});

			return true;
		}

		return false;
	}
}
