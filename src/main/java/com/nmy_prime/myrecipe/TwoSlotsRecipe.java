package com.nmy_prime.myrecipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class TwoSlotsRecipe implements Recipe<CraftingInventory> {

    // TLS means TwoSlotsRecipe
    public static class TLSRecipeType implements RecipeType<TwoSlotsRecipe> {
        private TLSRecipeType() {}
        public static final TLSRecipeType INSTANCE =  new TLSRecipeType();
        public static final String id = "two_slots_recipe";
    }
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack outputStack;
    private final Identifier id;

    public TwoSlotsRecipe(Ingredient inputA, Ingredient inputB, ItemStack outputStack, Identifier id) {
        this.id = id;
        this.inputA = inputA;
        this.inputB = inputB;
        this.outputStack = outputStack;
    }

    public Ingredient getInputA() {
        return inputA;
    }

    public Ingredient getInputB() {
        return inputB;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.size() < 2) return false;
        return inputA.test(inventory.getStack(0)) && inputB.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TwoSlotsRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return TLSRecipeType.INSTANCE;
    }
}
