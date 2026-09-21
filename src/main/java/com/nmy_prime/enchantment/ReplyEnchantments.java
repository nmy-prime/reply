package com.nmy_prime.enchantment;

import com.nmy_prime.Main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.enchantment.Enchantment.Rarity;

public class ReplyEnchantments {

    private static final EquipmentSlot[] MAIN_HAND = new EquipmentSlot[]{EquipmentSlot.MAINHAND};
    private static final Enchantment THUNDER = new ThunderEnchantment(Rarity.RARE, EnchantmentTarget.WEAPON, MAIN_HAND);
    private static final Enchantment POISON = new PoisonEnchantment(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, MAIN_HAND);

    public static void registerFabricEnchantments() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(Main.ModId, "poison"), POISON);
        Registry.register(Registry.ENCHANTMENT, new Identifier(Main.ModId, "thunder"), THUNDER);
    }
}
