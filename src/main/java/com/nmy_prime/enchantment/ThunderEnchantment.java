package com.nmy_prime.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * 闪电附魔对应削弱版的牛子力量，不会给予玩家抗火效果
 */

public class ThunderEnchantment extends Enchantment {

    public ThunderEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 1 ;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        World world = user.getEntityWorld();

        if (target instanceof LivingEntity) {

            ServerWorld serverWorld = Objects.requireNonNull(world.getServer())
                    .getWorld( (target)
                            .getEntityWorld()
                            .getRegistryKey());

            if (serverWorld != null) {
                EntityType.LIGHTNING_BOLT.spawnFromItemStack(serverWorld,
                        null,
                        (PlayerEntity) user,
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
    }
}
