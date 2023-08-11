package com.nmy_prime;

import com.nmy_prime.recipes.ConsecrationRecipe;
import net.fabricmc.api.ModInitializer;
import com.nmy_prime.enchantment.FabricEnchantments;
import com.nmy_prime.items.ReplyItems;
import com.nmy_prime.items.ReplyItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final String ModId = "reply";
	// 物品组
	public static final ItemGroup MY_GROUP = FabricItemGroupBuilder.create(new Identifier(ModId,"my_group"))
			.icon(() -> new ItemStack(ReplyItem.FABRIC_ITEM))
			.build();

	public static final RecipeSerializer<ConsecrationRecipe> CONSECRATION_RECIPE_SERIALIZER = RecipeSerializer.register("consecrate_craft", new SpecialRecipeSerializer<>(ConsecrationRecipe::new));

	private static final Identifier ENDER_DRAGON_LOOT_TABLE_ID = EntityType.ENDER_DRAGON.getLootTableId();
	private static final Identifier WITHER_LOOT_TABLE_ID = EntityType.WITHER.getLootTableId();

	@Override
	public void onInitialize() {
		// 向末影龙和凋灵战利品表添加物品
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) ->{
			if (ENDER_DRAGON_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(ReplyItems.GLINT_SWORD_SOUL));
				table.pool(poolBuilder);
			} else if (WITHER_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.with(ItemEntry.builder(ReplyItems.GLINT_SWORD_SOUL));
				table.pool(poolBuilder);
			}
		});

		FabricEnchantments.registerFabricEnchantments();
		ReplyItems.register();
	}
}
