package com.nmy_prime.items;

import com.nmy_prime.items.entities.CopperCoinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CopperCoinItem extends Item {
    public CopperCoinItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient) {
            CopperCoinEntity copperCoinEntity = new CopperCoinEntity(world, user);
            copperCoinEntity.setItem(itemStack);
            copperCoinEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 1.0F);
            world.spawnEntity(copperCoinEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }

}
