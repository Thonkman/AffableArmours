package io.github.affable_armours.mixin;


import io.github.affable_armours.armours.Armours;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
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

	@Shadow
	@Final
	private static Logger LOGGER;

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
	int dragonCharge = 0;
	@Unique
	private void updateDragonArmour() {
		ItemStack headEquipment = getEquippedStack(EquipmentSlot.HEAD);
		ItemStack chestEquipment = getEquippedStack(EquipmentSlot.CHEST);
		ItemStack legEquipment = getEquippedStack(EquipmentSlot.LEGS);
		ItemStack feetEquipment = getEquippedStack(EquipmentSlot.FEET);

		if (headEquipment.isOf(Armours.DRAGON_VISOR) && chestEquipment.isOf(Items.ELYTRA) && legEquipment.isOf(Armours.DRAGON_SCALEMAIL) && feetEquipment.isOf(Armours.DRAGON_CLAWS) && this.isOnGround() && this.isSneaking()) {
			//Holding shift for 5 seconds while on the ground will shoot you up in the air (maybe holding it for longer will send you higher?) I'm talking to like +150y height.
			// Holding shift while gliding (it's called fall flying) Will spread Dragons breath where the player is facing like how the dragon does
			dragonCharge++;
			LOGGER.info(String.valueOf(dragonCharge));
			if (dragonCharge >= 50) {
				this.getWorld().addParticle(ParticleTypes.PORTAL, this.getParticleX(2), this.getRandomBodyY() - 0.25, this.getParticleZ(2), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
			}
		}

		if (!this.isSneaking()) {
			if (dragonCharge >= 50) {
				this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_FLAP, SoundCategory.PLAYERS, 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
				LOGGER.info("flight inbound");
				LOGGER.info(String.valueOf(dragonCharge));
				dragonCharge = 0;
				LOGGER.info(String.valueOf(dragonCharge));
				this.addVelocity(0, 5, 0);
			}
			if (dragonCharge <= 0) {
				dragonCharge = 0;
			}
		}
//		Vec3d vec3d = (new Vec3d(this.getX(), 0.0, this.getZ())).normalize();
		double d = this.getX();
		double e = this.getZ();
		double g = this.getY();
		double h = g;
		BlockPos.Mutable mutable = new BlockPos.Mutable(d, g, e);

		while(this.getWorld().isAir(mutable)) {
			--h;
			if (h < 0.0) {
				h = g;
				break;
			}

			mutable.set(d, h, e);
		}

		if (this.isSneaking() && !this.isOnGround() && dragonCharge >= 0) {
			AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), d, h, e);
			areaEffectCloudEntity.setRadius(3.0F);
			areaEffectCloudEntity.setRadiusOnUse(-0.5F);
			areaEffectCloudEntity.setDuration(200);
			areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
			areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE));
			areaEffectCloudEntity.setParticleType(ParticleTypes.DRAGON_BREATH);
			areaEffectCloudEntity.setOwner(this);
			this.getWorld().spawnEntity(areaEffectCloudEntity);
			dragonCharge = 0;
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
