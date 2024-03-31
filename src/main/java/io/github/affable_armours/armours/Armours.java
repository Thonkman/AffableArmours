package io.github.affable_armours.armours;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Arm;
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

	public static final ArmorMaterial SCULK_ARMOUR_MATERIAL = new SculkArmourMaterial();

	public static final Item SCULK_HELMET = new ArmorItem(SCULK_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item SCULK_CHESTPLATE = new ArmorItem(SCULK_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings());
	public static final Item SCULK_LEGGINGS = new ArmorItem(SCULK_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item SCULK_BOOTS = new ArmorItem(SCULK_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static final ArmorMaterial DRAGON_ARMOUR_MATERIAL = new DragonArmourMaterial();

	public static final Item DRAGON_VISOR = new ArmorItem(DRAGON_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item DRAGON_SCALEMAIL = new ArmorItem(DRAGON_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item DRAGON_CLAWS = new ArmorItem(DRAGON_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());

	public static final ArmorMaterial SHULKER_ARMOUR_MATERIAL = new ShulkerArmourMaterial();

	public static final Item SHULKER_SHELL = new ArmorItem(SHULKER_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.HELMET, new Item.Settings());
	public static final Item SHULKER_PADS = new ArmorItem(SHULKER_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings());
	public static final Item SHULKER_BOOTS = new ArmorItem(SHULKER_ARMOUR_MATERIAL, ArmorItem.ArmorSlot.BOOTS, new Item.Settings());


	public static void register() {
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_carapace"), TURTLE_CARAPACE);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_plastron"), TURTLE_PLASTRON);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "turtle_flippers"), TURTLE_FLIPPERS);

		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_hood"), PHANTOM_HOOD);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_mantle"), PHANTOM_MANTLE);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "phantom_slippers"), PHANTOM_SLIPPERS);

		Registry.register(Registries.ITEM, new Identifier("affable_armours", "sculk_helmet"), SCULK_HELMET);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "sculk_chestplate"), SCULK_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "sculk_leggings"), SCULK_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "sculk_boots"), SCULK_BOOTS);

		Registry.register(Registries.ITEM, new Identifier("affable_armours", "dragon_visor"), DRAGON_VISOR);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "dragon_scalemail"), DRAGON_SCALEMAIL);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "dragon_claws"), DRAGON_CLAWS);

		Registry.register(Registries.ITEM, new Identifier("affable_armours", "shulker_shell"), SHULKER_SHELL);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "shulker_pads"), SHULKER_PADS);
		Registry.register(Registries.ITEM, new Identifier("affable_armours", "shulker_boots"), SHULKER_BOOTS);
	}
}
