package com.nmy_prime.items;

import com.nmy_prime.Main;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import java.util.List;

public class ReplyItem extends Item {

    public ReplyItem(Settings settings) {
        super(settings);
    }
    // 新物品
    public static final ReplyItem REPLY_ITEM = new ReplyItem(new FabricItemSettings()
            .group(Main.MY_GROUP)
            .maxCount(64));

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("item.reply.custom.tooltip"));
    }
}

