package com.nmy_prime.armors;

import com.nmy_prime.Main;
import com.nmy_prime.items.ReplyItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ReplyArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {4, 6, 8, 4};

    @Override // 耐久度
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 90;
    }

    @Override // 护甲值
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override // 附魔权重
    public int getEnchantability() {
        return 15;
    }

    @Override // 穿戴盔甲声音
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ReplyItem.REPLY_ITEM);
    }

    @Override
    public String getName() {
        return "reply";
    }

    @Override // 盔甲韧性
    public float getToughness() {
        return 1.0F;
    }

    @Override // 抗击退
    public float getKnockbackResistance() {
        return 1.0F;
    }

    private static final ArmorMaterial REPLY_ARMOR_MATERIAL = new ReplyArmorMaterial();
    private static final Item REPLY_MATERIAL_HELMET = new ArmorItem(REPLY_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(Main.MY_GROUP));
    private static final Item REPLY_MATERIAL_CHESTPLATE = new ArmorItem(REPLY_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(Main.MY_GROUP));
    private static final Item REPLY_MATERIAL_LEGGINGS = new ArmorItem(REPLY_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(Main.MY_GROUP));
    private static final Item REPLY_MATERIAL_BOOTS = new ArmorItem(REPLY_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(Main.MY_GROUP));
    public static void register() {
        Registry.register(Registry.ITEM,new Identifier("reply", "reply_material_helmet"), REPLY_MATERIAL_HELMET);
        Registry.register(Registry.ITEM,new Identifier("reply", "reply_material_chestplates"), REPLY_MATERIAL_CHESTPLATE);
        Registry.register(Registry.ITEM,new Identifier("reply", "reply_material_leggings"), REPLY_MATERIAL_LEGGINGS);
        Registry.register(Registry.ITEM,new Identifier("reply", "reply_material_boots"), REPLY_MATERIAL_BOOTS);
    }
}
