package com.nmy_prime.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NbtHelper {
    // 查看物品是否含有keyname的nbt
    public static boolean hasNBT(ItemStack itemStack, String keyname) {
        return !itemStack.isEmpty() && itemStack.getNbt() != null && itemStack.getNbt().contains(keyname);
    }

    public static void initCompoundNbt(ItemStack itemStack) {
        if (itemStack.getNbt() == null)
            itemStack.setNbt(new NbtCompound());
    }

    // 得到物品一个名为keyName的nbt
    public static NbtCompound getNbt(ItemStack itemStack,String keyname) {
        initCompoundNbt(itemStack);

        if (itemStack.getNbt() != null && !itemStack.getNbt().contains(keyname))
            putNbt(itemStack, keyname, new NbtCompound());

        return itemStack.getNbt().getCompound(keyname);
    }

    public static void putNbt(ItemStack itemStack, String keyname, NbtCompound nbtCompound) {
        initCompoundNbt(itemStack);

        if (itemStack.getNbt() != null) {
            itemStack.getNbt().put(keyname,nbtCompound);
        }
    }

    public static void removeNbt(ItemStack itemStack, String keyname) {
        if (itemStack.getNbt() != null)
            itemStack.getNbt().remove(keyname);
    }
}
