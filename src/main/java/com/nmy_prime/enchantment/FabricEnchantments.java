package com.nmy_prime.enchantment;

import com.nmy_prime.Main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.enchantment.Enchantment.Rarity;


public class FabricEnchantments {

    private static final EquipmentSlot[] MAINHAND = new EquipmentSlot[]{EquipmentSlot.MAINHAND};

    private static final Enchantment THUNDER = new ThunderEnchantment(Rarity.RARE, EnchantmentTarget.WEAPON, MAINHAND);
    private static final Enchantment POISON = new PoisonEnchantment(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, MAINHAND);

    public static void registerFabricEnchantments() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("tutorial", "poison"), POISON);
        Registry.register(Registry.ENCHANTMENT, new Identifier("tutorial", "thunder"), THUNDER);
        System.out.println("Registering FabricBlocks for" + Main.ModId);
    }
}
