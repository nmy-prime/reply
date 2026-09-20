package com.nmy_prime.items.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class CopperCoinEntity extends ThrownEntity {

    public CopperCoinEntity(EntityType<? extends CopperCoinEntity> type, World world){
        super(type, world);
    }

    public CopperCoinEntity(World world, LivingEntity owner){
        super(ModEntities.COPPER_COIN, owner, world);
    }

    @Override
    protected void onCollision(HitResult hitResult){
        super.onCollision(hitResult);
        discard();
    }

    @Override
    protected void initDataTracker() {

    }
}