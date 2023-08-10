package com.nmy_prime.myrecipe;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class TwoSlotsRecipeSerializer implements RecipeSerializer<TwoSlotsRecipe> {
    @Override
    public TwoSlotsRecipe read(Identifier id, JsonObject json) {
        return null;
    }

    @Override
    public TwoSlotsRecipe read(Identifier id, PacketByteBuf buf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf buf, TwoSlotsRecipe recipe) {

    }
}
