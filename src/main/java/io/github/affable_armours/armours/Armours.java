package io.github.affable_armours.armours;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.ArmorMaterials.TURTLE;

public class Armours {
	public static final Item TURTLE_CARAPACE = new ArmorItem(TURTLE, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item TURTLE_PLASTRON = new ArmorItem(TURTLE, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item TURTLE_FLIPPERS = new ArmorItem(TURTLE, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static final ArmorMaterial PHANTOM_ARMOUR_MATERIAL = new PhantomArmourMaterial();

	public static final Item PHANTOM_HOOD = new ArmorItem(PHANTOM_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item PHANTOM_MANTLE = new ArmorItem(PHANTOM_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item PHANTOM_SLIPPERS = new ArmorItem(PHANTOM_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static void register() {
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_carapace"), TURTLE_CARAPACE);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_plastron"), TURTLE_PLASTRON);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_flippers"), TURTLE_FLIPPERS);

		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_hood"), PHANTOM_HOOD);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_mantle"), PHANTOM_MANTLE);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_slippers"), PHANTOM_SLIPPERS);
	}
}
