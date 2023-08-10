package com.nmy_prime;

import com.nmy_prime.blocks.FabricBlocks;
import net.fabricmc.api.ModInitializer;
import com.nmy_prime.enchantment.FabricEnchantments;
import com.nmy_prime.items.FabricItems;
import com.nmy_prime.items.ReplyItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final String ModId = "tutorial";

	// 物品组
	public static final ItemGroup MY_GROUP = FabricItemGroupBuilder.create(new Identifier("tutorial","my_group"))
			.icon(() -> new ItemStack(ReplyItem.FABRIC_ITEM))
			.build();


	@Override
	public void onInitialize() {
		FabricEnchantments.registerFabricEnchantments();
		FabricBlocks.registerFabricBlocks();
		FabricItems.register();
	}
}
