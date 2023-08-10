package com.nmy_prime.items.tool_items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
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
/**
 * @author NMY_PRIME
 * 对应牛子的圣遗物，appendTooltip方法为其添加物品描述，
 * postHit方法中是一段来自美西螈桶的召唤闪电代码，
 * 将给予玩家2秒999级的抗性提升，10秒的抗火和5秒的力量2效果
 * 在ReplayMaterial类里注册
 */
public class MyArtifact extends SwordItem {

    public MyArtifact(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.tutorial.goofy_sword.tooltip").formatted(Formatting.GOLD));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        World world = attacker.getEntityWorld();

        if (target != null) {

            ServerWorld serverWorld = Objects.requireNonNull(world.getServer())
                    .getWorld( (target)
                            .getEntityWorld()
                            .getRegistryKey());

            if (serverWorld != null) {
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 2, 999), attacker);
                EntityType.LIGHTNING_BOLT.spawnFromItemStack(serverWorld,
                        null,
                        (PlayerEntity) attacker,
                        target.getBlockPos(),
                        SpawnReason.MOB_SUMMONED,
                        true,
                        false);
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, serverWorld);
                serverWorld.playSoundFromEntity(null,
                        lightning,
                        SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                        SoundCategory.NEUTRAL,
                        1.0f,
                        1.0f);
                serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
                        (target).getX(),
                        (target).getY(),
                        (target).getZ(),
                        1, 0.0, 0.0, 0.0, 0.0);
            }
        }

        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,20 * 5,1));
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,20 * 10,0));

        return super.postHit(stack, target, attacker);
    }

}
