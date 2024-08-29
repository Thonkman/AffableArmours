package io.github.affable_armors.armors;

import io.github.affable_armors.AffableArmors;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Holder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Armors {
	public static Item turtleCarapace;
	public static Item turtlePlastron;
	public static Item turtleFlippers;

	public static Item phantomHood;
	public static Item phantomMantle;
	public static Item phantomSlippers;

	public static Item sculkHelmet;
	public static Item sculkChestplate;
	public static Item sculkLeggings;
	public static Item sculkBoots;

	public static Item dragonVisor;
	public static Item dragonScalemail;
	public static Item dragonClaws;

	public static Item shulkerShell;
	public static Item shulkerPads;
	public static Item shulkerBoots;


	public static void register() {
		Map<ArmorItem.ArmorSlot, Integer> phantomDefense = new HashMap<>();
		phantomDefense.put(ArmorItem.ArmorSlot.BOOTS, 2);
		phantomDefense.put(ArmorItem.ArmorSlot.LEGGINGS, 4);
		phantomDefense.put(ArmorItem.ArmorSlot.CHESTPLATE, 5);
		phantomDefense.put(ArmorItem.ArmorSlot.HELMET, 2);

		List<ArmorMaterial.Layer> phantomLayers = new ArrayList<>();
		phantomLayers.add(new ArmorMaterial.Layer(
			Identifier.ofDefault("phantom")
		));

		var phantomMaterial = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("phantom", "phantom_armor"), new ArmorMaterial(
			phantomDefense,
			20,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
			() -> Ingredient.ofItems(Items.PHANTOM_MEMBRANE),
			phantomLayers,
			0,
			0
		));
		var phantomHolder = Holder.createDirect(phantomMaterial);

		phantomHood = Registry.register(Registries.ITEM, AffableArmors.id("phantom_hood"), new ArmorItem(phantomHolder, ArmorItem.ArmorSlot.HELMET, new Item.Settings()));
		phantomMantle = Registry.register(Registries.ITEM, AffableArmors.id("phantom_mantle"), new ArmorItem(phantomHolder, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings()));
		phantomSlippers = Registry.register(Registries.ITEM, AffableArmors.id("phantom_slippers"), new ArmorItem(phantomHolder, ArmorItem.ArmorSlot.BOOTS, new Item.Settings()));

		Map<ArmorItem.ArmorSlot, Integer> sculkDefense = new HashMap<>();
		sculkDefense.put(ArmorItem.ArmorSlot.BOOTS, 3);
		sculkDefense.put(ArmorItem.ArmorSlot.LEGGINGS, 6);
		sculkDefense.put(ArmorItem.ArmorSlot.CHESTPLATE, 8);
		sculkDefense.put(ArmorItem.ArmorSlot.HELMET, 3);

		List<ArmorMaterial.Layer> sculkLayers = new ArrayList<>();
		sculkLayers.add(new ArmorMaterial.Layer(
			Identifier.ofDefault("sculk")
		));



		var sculkMaterial = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("sculk", "sculk_armor"), new ArmorMaterial(
			sculkDefense,
			15,
			Holder.createDirect(SoundEvents.BLOCK_SCULK_BREAK),
			() -> Ingredient.ofItems(Items.SCULK_CATALYST),
			sculkLayers,
			2,
			0.2F
		));

		var sculkHolder = Holder.createDirect(sculkMaterial);

		sculkHelmet = Registry.register(Registries.ITEM, AffableArmors.id("sculk_helmet"), new ArmorItem(sculkHolder, ArmorItem.ArmorSlot.HELMET, new Item.Settings()));
		sculkChestplate = Registry.register(Registries.ITEM, AffableArmors.id("sculk_chestplate"), new ArmorItem(sculkHolder, ArmorItem.ArmorSlot.CHESTPLATE, new Item.Settings()));
		sculkLeggings = Registry.register(Registries.ITEM, AffableArmors.id("sculk_leggings"), new ArmorItem(sculkHolder, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings()));
		sculkBoots = Registry.register(Registries.ITEM, AffableArmors.id("sculk_boots"), new ArmorItem(sculkHolder, ArmorItem.ArmorSlot.BOOTS, new Item.Settings()));

		Map<ArmorItem.ArmorSlot, Integer> shulkerDefense = new HashMap<>();
		shulkerDefense.put(ArmorItem.ArmorSlot.BOOTS, 3);
		shulkerDefense.put(ArmorItem.ArmorSlot.LEGGINGS, 5);
		shulkerDefense.put(ArmorItem.ArmorSlot.HELMET, 3);

		var shulkerMaterial = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("shulker", "shulker_armor"), new ArmorMaterial(
			shulkerDefense,
			20,
			Holder.createDirect(SoundEvents.BLOCK_SHULKER_BOX_CLOSE),
			() -> Ingredient.ofItems(Items.SHULKER_SHELL),
			null,
			2,
			0.5F
		));

		var shulkerHolder = Holder.createDirect(shulkerMaterial);

		shulkerShell = Registry.register(Registries.ITEM, AffableArmors.id("shulker_shell"), new ArmorItem(shulkerHolder, ArmorItem.ArmorSlot.HELMET, new Item.Settings()));
		shulkerPads = Registry.register(Registries.ITEM, AffableArmors.id("shulker_pads"), new ArmorItem(shulkerHolder, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings()));
		shulkerBoots = Registry.register(Registries.ITEM, AffableArmors.id("shulker_boots"), new ArmorItem(shulkerHolder, ArmorItem.ArmorSlot.BOOTS, new Item.Settings()));

		Map<ArmorItem.ArmorSlot, Integer> dragonDefense = new HashMap<>();
		dragonDefense.put(ArmorItem.ArmorSlot.BOOTS, 3);
		dragonDefense.put(ArmorItem.ArmorSlot.LEGGINGS, 6);
		dragonDefense.put(ArmorItem.ArmorSlot.HELMET, 3);

		List<ArmorMaterial.Layer> dragonLayers = new ArrayList<>();
		dragonLayers.add(new ArmorMaterial.Layer(
			Identifier.ofDefault("dragon")
		));

		var dragonMaterial = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of("dragon", "dragon_armor"), new ArmorMaterial(
			dragonDefense,
			10,
			SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
			() -> Ingredient.ofItems(Items.CRYING_OBSIDIAN),
			dragonLayers,
			2,
			-0.1F
		));

		var dragonHolder = Holder.createDirect(dragonMaterial);

		dragonVisor = Registry.register(Registries.ITEM, AffableArmors.id("dragon_visor"), new ArmorItem(dragonHolder, ArmorItem.ArmorSlot.HELMET, new Item.Settings()));
		dragonScalemail = Registry.register(Registries.ITEM, AffableArmors.id("dragon_scalemail"), new ArmorItem(dragonHolder, ArmorItem.ArmorSlot.LEGGINGS, new Item.Settings()));
		dragonClaws = Registry.register(Registries.ITEM, AffableArmors.id("dragon_claws"), new ArmorItem(dragonHolder, ArmorItem.ArmorSlot.BOOTS, new Item.Settings()));
	}
}
