package com.nmy_prime.items.tool_items;

import com.nmy_prime.Main;
import com.nmy_prime.items.ReplyItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ReplyMaterial implements ToolMaterial {
    // 实例
    public static final ReplyMaterial INSTANCE = new ReplyMaterial();
    // GOOFY斧头
    public static final ToolItem GOOFY_AXE = new ReplyAxeItem(INSTANCE, 5.0F, -3.0F,
            new Item.Settings().group(Main.MY_GROUP));
    // 圣水杯
    public static final ToolItem GOOFY_SWORD = new MyArtifactItem(INSTANCE, 8, -0.5F,
            new Item.Settings().group(Main.MY_GROUP));
    // GOOFY镐子
    public static final ToolItem GOOFY_PICKAXE = new ReplyPickaxeItem(INSTANCE, 4, -2.5F,
            new FabricItemSettings().group(Main.MY_GROUP));

    // 耐久度
    @Override
    public int getDurability() {
        return 1600;
    }

    // 挖掘速度
    @Override
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }

    // 武器伤害
    @Override
    public float getAttackDamage() {
        return 999.0F;
    }

    // 挖矿等级
    @Override
    public int getMiningLevel() {
        return 3;
    }

    // 附魔权重
    @Override
    public int getEnchantability() {
        return 15;
    }

    // 修复材料
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ReplyItem.REPLY_ITEM);
    }

    // 注册方法
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("reply", "goofy_pickaxe"), GOOFY_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("reply", "goofy_axe"), GOOFY_AXE);
        Registry.register(Registry.ITEM, new Identifier("reply", "goofy_sword"), GOOFY_SWORD);
    }

}
