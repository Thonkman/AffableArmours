package io.github.affable_armors.mixin;


import io.github.affable_armors.armors.Armors;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityInject extends LivingEntity implements GameEventListener {
	protected PlayerEntityInject(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);


	@Shadow
	@Final
	public static int MAX_HEALTH;


	@Shadow
	public abstract boolean isSwimming();

	@Shadow
	public abstract void updateSwimming();

	@Shadow
	@Final
	private static Logger LOGGER;

	@Inject(method = "tick", at = @At("TAIL"))
	public void tick(CallbackInfo ci) {
		updateTurtleArmour();
		updatePhantomHood();
		updatePhantomArmour();
		updateSculkArmour();
	}

	@Unique
	private void updateTurtleArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if ((this.getHealth() <= (float) MAX_HEALTH /4 ) && headEquipment.isOf(Items.TURTLE_HELMET) && chestEquipment.isOf(Armors.turtleCarapace) && legEquipment.isOf(Armors.turtlePlastron) && feetEquipment.isOf(Armors.turtleFlippers)) {
			provideResistance(20);
			provideSlownessVI(20);
			this.setPose(EntityPose.SWIMMING);
		}
	}
	@Unique
	private void updatePhantomHood() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		if (headEquipment.isOf(Armors.phantomHood) && this.getWorld().getTimeOfDay() >= 13000 && this.getWorld().getTimeOfDay() <= 23000 && !this.hasStatusEffect(StatusEffects.NIGHT_VISION) ) {
			provideNightVision(1200);
		}
	}
	@Unique
	private void updatePhantomArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if ((this.isSneaking() && this.isFallFlying()) && headEquipment.isOf(Armors.phantomHood) && chestEquipment.isOf(Items.ELYTRA) && legEquipment.isOf(Armors.phantomMantle) && feetEquipment.isOf(Armors.phantomSlippers)) {
			provideSlowFalling(1);
		}
	}

	@Unique
	private void updateSculkArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if (headEquipment.isOf(Armors.sculkHelmet) && chestEquipment.isOf(Armors.sculkChestplate) && legEquipment.isOf(Armors.sculkLeggings) && feetEquipment.isOf(Armors.sculkBoots)) {
			//Make it give darkness, strength and slowness, then highlight all mobs that trigger a sculk sensor/screamer (including calibrated)
			provideDarkness(200);
			provideSlownessI(200);
			provideStrength(200);
			
		}
	}

	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideResistance(int rduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, rduration, 3, false, true, true));
	}
	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideSlownessVI(int sduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, sduration, 5, false, true, true));
	}
	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideNightVision(int nvduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, nvduration, 0, false, true, true));
	}
	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideSlowFalling(int sfduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, sfduration, 0, false, true, true));
	}
	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideDarkness(int dduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, dduration, 0, false, true, true));
	}
	@Unique
	@SuppressWarnings("SameParameterValue")
	private void provideSlownessI(int sIduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, sIduration, 0, false, true, true));
	}
	@Unique
	@SuppressWarnings("SameParameterValue")
	private void provideStrength(int stduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, stduration, 0, false, true, true));
	}
}
