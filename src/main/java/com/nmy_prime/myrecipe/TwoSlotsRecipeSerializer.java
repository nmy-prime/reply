package com.nmy_prime.myrecipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TwoSlotsRecipeSerializer implements RecipeSerializer<TwoSlotsRecipe> {

    private TwoSlotsRecipeSerializer() {}
    public static final Identifier ID = new Identifier("reply:two_slots_recipe");
    public static final TwoSlotsRecipeSerializer INSTANCE = new TwoSlotsRecipeSerializer();
    @Override
    public TwoSlotsRecipe read(Identifier id, JsonObject json) {
        TwoSlotsRecipeJsonFormat recipeJson = new Gson().fromJson(json, TwoSlotsRecipeJsonFormat.class);
        // Validate all fields are there
        if (recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        // We'll allow to not specify the output, and default it to 1.
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new TwoSlotsRecipe(inputA, inputB, output, id);
    }

    @Override
    public TwoSlotsRecipe read(Identifier id, PacketByteBuf buf) {
        // Make sure the read in the same order you have written!
        Ingredient inputA = Ingredient.fromPacket(buf);
        Ingredient inputB = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new TwoSlotsRecipe(inputA, inputB, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, TwoSlotsRecipe recipe) {
        recipe.getInputA().write(buf);
        recipe.getInputB().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }
}
