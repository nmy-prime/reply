package com.nmy_prime.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlintSwordSoulItem extends Item {
    public GlintSwordSoulItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
