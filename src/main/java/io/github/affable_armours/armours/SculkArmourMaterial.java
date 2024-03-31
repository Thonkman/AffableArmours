package io.github.affable_armours.armours;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class SculkArmourMaterial implements ArmorMaterial {
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
		return 15;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.BLOCK_SCULK_BREAK;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.SCULK_CATALYST);
	}

	@Override
	public String getName() {
		return "sculk_material";
	}

	@Override
	public float getToughness() {
		return 2;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.2F;
	}
}
