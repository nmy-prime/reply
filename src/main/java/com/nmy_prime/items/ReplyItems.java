package com.nmy_prime.items;

import com.nmy_prime.Main;
import com.nmy_prime.blocks.ReplyBlocks;
import com.nmy_prime.items.tool_items.ReplyMaterial;
import com.nmy_prime.armors.ReplyArmorMaterial;
import com.nmy_prime.items.weapons.PeachWoodSword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// 注册类

public class ReplyItems {

    public static final Item BAKED_BREAD = new Item(new Item.Settings()
            .group(Main.MY_GROUP)
            .food(new FoodComponent.Builder()
                    .hunger(2)
                    .saturationModifier(0.3F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,2000,1),1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,2400,0),1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION,6000,0),1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,2000,1),1.0F)
                    .alwaysEdible()
                    .build())
            .maxCount(64));
    public static final Item COPPER_COIN = new CopperCoinItem(new Item.Settings().group(Main.MY_GROUP));
    public static final Item PEACH_WOODITEM = new PeachWoodSword(new ReplyMaterial(), 4, 2, new FabricItemSettings().group(Main.MY_GROUP).maxCount(1));
    private static final Item IRON_STICK = new Item(new Item.Settings().group(Main.MY_GROUP));
    public static final Item GLINT_SWORD_SOUL = new GlintSwordSoulItem(new FabricItemSettings().group(Main.MY_GROUP).maxCount(1));
    public static final Item BLEAK_SWORD_SOUL = new Item(new FabricItemSettings().group(Main.MY_GROUP).maxCount(1));
    private static final Item INCARNON_CRAFT_BLOCK_ITEM = new BlockItem(ReplyBlocks.INCARNON_CRAFT_BLOCK, new Item.Settings().group(Main.MY_GROUP));
    private static final Item YELLOW_PAPER =  new Item(new FabricItemSettings().group(Main.MY_GROUP));


    public static void register() {
        ReplyArmorMaterial.register();
        ReplyMaterial.register();
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "yellow_paper"), YELLOW_PAPER);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "copper_coin"), COPPER_COIN);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "peach_wood_sword"), PEACH_WOODITEM);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "glint_sword_soul"), GLINT_SWORD_SOUL);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "bleak_sword_soul"), BLEAK_SWORD_SOUL);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "reply_item"), ReplyItem.REPLY_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "baked_bread"), BAKED_BREAD);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "iron_stick"), IRON_STICK);
        Registry.register(Registry.ITEM, new Identifier(Main.ModId, "incarnon_craft_block_item"), INCARNON_CRAFT_BLOCK_ITEM);
    }
}
