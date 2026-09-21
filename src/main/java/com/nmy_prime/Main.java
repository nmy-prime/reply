package com.nmy_prime;

import com.nmy_prime.blocks.IncarnonCraftBlockEntity;
import com.nmy_prime.blocks.ReplyBlocks;
import com.nmy_prime.items.entities.ReplyEntities;
import com.nmy_prime.recipes.ConsecrationRecipe;
import net.fabricmc.api.ModInitializer;
import com.nmy_prime.enchantment.ReplyEnchantments;
import com.nmy_prime.items.ReplyItems;
import com.nmy_prime.items.ReplyItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.nmy_prime.blocks.ReplyBlocks.INCARNON_CRAFT_BLOCK;

public class Main implements ModInitializer {
	public static final String ModId = "reply";
	// 物品组
	public static final ItemGroup MY_GROUP = FabricItemGroupBuilder.create(new Identifier(ModId,"my_group"))
			.icon(() -> new ItemStack(ReplyItem.REPLY_ITEM))
			.build();

	public static final Identifier SWITCH_PACKET_ID = new Identifier(ModId, "switch_slot");

	public static final RecipeSerializer<ConsecrationRecipe> CONSECRATION_RECIPE_SERIALIZER = RecipeSerializer.register("consecrate_craft", new SpecialRecipeSerializer<>(ConsecrationRecipe::new));
	private static final Identifier ENDER_DRAGON_LOOT_TABLE_ID = EntityType.ENDER_DRAGON.getLootTableId();
	private static final Identifier WITHER_LOOT_TABLE_ID = EntityType.WITHER.getLootTableId();
	public static final BlockEntityType<IncarnonCraftBlockEntity> INCARNON_CRAFT_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			new Identifier("tutorial", "demo_block_entity"),
			FabricBlockEntityTypeBuilder.create(IncarnonCraftBlockEntity::new, INCARNON_CRAFT_BLOCK).build()
	);

	@Override
	public void onInitialize() {
        ReplyEntities.registerEntities();

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

		ReplyEnchantments.registerFabricEnchantments();
		ReplyItems.register();
		ReplyBlocks.register();

		ServerPlayNetworking.registerGlobalReceiver(SWITCH_PACKET_ID, (server, player, handler, buf, responseSender) -> {
			int slot = buf.readInt();
			server.execute(() -> {
				if (slot == 2) {
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 0, false, true, true));
				} else {
					player.removeStatusEffect(StatusEffects.SPEED);
				}
			});
		});
	}
}
