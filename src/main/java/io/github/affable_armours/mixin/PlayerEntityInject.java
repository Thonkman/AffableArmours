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
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityInject extends LivingEntity {


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
		updateDragonArmour();
	}




	@Unique
	private void updateTurtleArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if ((this.getHealth() <= (float) MAX_HEALTH /4 ) && headEquipment.isOf(Items.TURTLE_HELMET) && chestEquipment.isOf(Armours.TURTLE_CARAPACE) && legEquipment.isOf(Armours.TURTLE_PLASTRON) && feetEquipment.isOf(Armours.TURTLE_FLIPPERS)) {
			provideResistance(20);
			provideSlowness(20);
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
	private void updateDragonArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);
		if (headEquipment.isOf(Armours.DRAGON_VISOR) && chestEquipment.isOf(Items.ELYTRA) && legEquipment.isOf(Armours.DRAGON_SCALEMAIL) && feetEquipment.isOf(Armours.DRAGON_CLAWS)) {
			//Holding shift for 5 seconds while on the ground will shoot you up in the air (maybe holding it for longer will send you higher?) I'm talking to like +150y height.
			// Holding shift while gliding (its called fall flying) Will spread Dragons breath where the player is facing like how the dragon does
		}
	}

	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideResistance(int rduration) {
		((LivingEntityInvoker) this).invokeAddStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, rduration, 3, false, true, true));
	}
	@SuppressWarnings("SameParameterValue")
	@Unique
	private void provideSlowness(int sduration) {
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


}
