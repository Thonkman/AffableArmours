package io.github.affable_armours.armours;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PhantomArmourMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[] {77, 0, 105, 91};
	private static final int[] PROTECTION_VALUES = new int[] {2, 4, 5, 2};
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
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.PHANTOM_MEMBRANE);
	}

	@Override
	public String getName() {
		return "phantom_material";
	}

	@Override
	public float getToughness() {
		return 0;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}
}
