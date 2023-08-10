package com.nmy_prime.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class GameModeToolItem extends Item {
    public GameModeToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient)
        {
            outputChangedMessage(user, user.getServer().getDefaultGameMode());
        }
        return super.use(world, user, hand);
    }

    private void outputChangedMessage(PlayerEntity player, GameMode gameMode) {
        player.sendMessage(Text.of("your gamemode is " + gameMode.toString() + " now"), true);
    }
}
