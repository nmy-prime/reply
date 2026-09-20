package com.nmy_prime.items.weapons;

import com.nmy_prime.reverse.NewLightningEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import java.util.List;
import java.util.Objects;

public class MyArtifactItem extends SwordItem {

    public MyArtifactItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (!stack.hasNbt()) return false;
        assert stack.getNbt() != null;
        return stack.getNbt().getInt("soul") == 1;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("tooltip.reply.goofy_sword.tooltip").formatted(Formatting.GRAY));
        if (itemStack.getNbt() != null && !itemStack.getNbt().isEmpty() && itemStack.getNbt().getInt("soul") == 1) {
            tooltip.add(new TranslatableText("tooltip.reply.sword_with_soul").formatted(Formatting.BLUE));
            tooltip.set(1, new TranslatableText("tooltip.reply.goofy_sword_with_soul").formatted(Formatting.GOLD));
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getNbt() != null && !stack.getNbt().isEmpty() && stack.getNbt().getInt("soul") == 1) {
            World world = attacker.getEntityWorld();
            if (target != null) {
                ServerWorld serverWorld = Objects.requireNonNull(world.getServer()).getWorld( (target).getEntityWorld().getRegistryKey());
                if (serverWorld != null) {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 2, 999), attacker);
                    NewLightningEntity lightning = new NewLightningEntity(EntityType.LIGHTNING_BOLT, serverWorld);
                    lightning.setPos(target.getX(), target.getY(), target.getZ());
                    serverWorld.spawnEntity(lightning);
                    serverWorld.playSoundFromEntity(null, lightning, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    serverWorld.spawnParticles(ParticleTypes.EXPLOSION, (target).getX(), (target).getY(), (target).getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,20 * 5,1));
            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,20 * 10,0));
        }
        return super.postHit(stack, target, attacker);
    }

}
