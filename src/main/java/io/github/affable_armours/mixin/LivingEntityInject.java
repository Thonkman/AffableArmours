package io.github.affable_armours.mixin;

import io.github.affable_armours.ApplyGlowingEventListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.event.EntityPositionSource;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.listener.DynamicGameEventListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(Entity.class)
public class LivingEntityInject {
	@Shadow
	private World world;

	@SuppressWarnings("all")
	@Inject(method = "updateDynamicGameEventListener", at = @At("TAIL"))
	public void update(BiConsumer<DynamicGameEventListener<?>, ServerWorld> updater, CallbackInfo ci) {
		if (((Object) this) instanceof PlayerEntity && world instanceof ServerWorld serverWorld) {
			updater.accept(new DynamicGameEventListener<>(new ApplyGlowingEventListener(new EntityPositionSource((Entity) ((Object) this), 2), GameEvent.SCULK_SENSOR_TENDRILS_CLICKING.getRange())), serverWorld);
		}
	}
}
