package com.nmy_prime.recipes;

import com.google.common.collect.Lists;
import com.nmy_prime.Main;
import com.nmy_prime.items.ReplyItems;
import com.nmy_prime.items.tool_items.ReplyMaterial;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;

// 圣化物品合成
public class ConsecrationRecipe extends SpecialCraftingRecipe {
    private int soulSlot = 0;
    public ConsecrationRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == ReplyMaterial.GOOFY_SWORD) {
                    list.add(stack);
                } else if (stack.getItem() == ReplyItems.GLINT_SWORD_SOUL) {
                    list.add(stack);
                }
            }
        }
        return list.size() == 2;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {

        boolean hasWeapon = false;
        boolean hasSoul = false;
        ArrayList<ItemStack> list = Lists.newArrayList();

        // 遍历合成物品栏
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == ReplyMaterial.GOOFY_SWORD) {
                    list.add(stack);
                    hasWeapon = true;
                } else if (stack.getItem() == ReplyItems.GLINT_SWORD_SOUL) {
                    list.add(stack);
                    hasSoul = true;
                    soulSlot = i;
                }
            }
        }

        if (hasSoul && hasWeapon && list.size() == 2) {
            NbtCompound compound = new NbtCompound();
            compound.putInt("soul", 1); // nbt铸魂
            byte i = 1;
            compound.putByte("Unbreakable", i); // 无法破坏
            ItemStack outputSword = new ItemStack(ReplyMaterial.GOOFY_SWORD, 1);
            outputSword.setNbt(compound);
            return outputSword;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(9, ItemStack.EMPTY);
        list.set(soulSlot, ReplyItems.BLEAK_SWORD_SOUL.getDefaultStack());
        return list;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Main.CONSECRATION_RECIPE_SERIALIZER;
    }
}
