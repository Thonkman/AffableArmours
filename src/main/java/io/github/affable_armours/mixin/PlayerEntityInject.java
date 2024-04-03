package io.github.affable_armours.mixin;


import io.github.affable_armours.armours.Armours;
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
import net.minecraft.world.event.vibration.VibrationManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityInject extends LivingEntity implements VibrationManager {


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
		if ((this.getHealth() <= (float) MAX_HEALTH /4 ) && headEquipment.isOf(Items.TURTLE_HELMET) && chestEquipment.isOf(Armours.TURTLE_CARAPACE) && legEquipment.isOf(Armours.TURTLE_PLASTRON) && feetEquipment.isOf(Armours.TURTLE_FLIPPERS)) {
			provideResistance(20);
			provideSlownessVI(20);
			this.setPose(EntityPose.SWIMMING);
		}
	}
	@Unique
	private void updatePhantomHood() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		if (headEquipment.isOf(Armours.PHANTOM_HOOD) && this.getWorld().getTimeOfDay() >= 13000 && this.getWorld().getTimeOfDay() <= 23000 && !this.hasStatusEffect(StatusEffects.NIGHT_VISION) ) {
			provideNightVision(1200);
		}
	}
	@Unique
	private void updatePhantomArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if ((this.isSneaking() && this.isFallFlying()) && headEquipment.isOf(Armours.PHANTOM_HOOD) && chestEquipment.isOf(Items.ELYTRA) && legEquipment.isOf(Armours.PHANTOM_MANTLE) && feetEquipment.isOf(Armours.PHANTOM_SLIPPERS)) {
			provideSlowFalling(1);
		}
	}
	@Unique
	private void updateSculkArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if (headEquipment.isOf(Armours.SCULK_HELMET) && chestEquipment.isOf(Armours.SCULK_CHESTPLATE) && legEquipment.isOf(Armours.SCULK_LEGGINGS) && feetEquipment.isOf(Armours.SCULK_BOOTS)) {
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
