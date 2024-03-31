package io.github.affable_armours.armours;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ShulkerArmourMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[] {407, 592, 555, 481};
	private static final int[] PROTECTION_VALUES = new int[] {3, 7, 5, 3};
	@Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return BASE_DURABILITY[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return PROTECTION_VALUES[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return 20;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.BLOCK_SHULKER_BOX_CLOSE;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.SHULKER_SHELL);
	}

	@Override
	public String getName() {
		return "shulker_material";
	}

	@Override
	public float getToughness() {
		return 0;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.5F;
	}
}
