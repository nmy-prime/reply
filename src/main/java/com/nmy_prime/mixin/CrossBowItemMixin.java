package com.nmy_prime.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(CrossbowItem.class)
public class CrossBowItemMixin {

    @Inject(at = @At("HEAD"), method = "appendTooltip")
    public void mixinAppendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci){
        tooltip.add(new TranslatableText("item.tutorial.mixin.crossbow"));
    }
    /**
     * @author NMY_prime
     * @reason 闲的蛋疼
     */
    @Overwrite
    private static float getSpeed(ItemStack stack) {
        if (CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET)) {
            return 50.0f;
        }
        return 60.0f;
    }

}
