package com.nmy_prime.items.entities;

import com.nmy_prime.items.ReplyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class CopperCoinEntity extends ThrownItemEntity {

    public CopperCoinEntity(EntityType<? extends CopperCoinEntity> type, World world){
        super(type, world);
    }

    public CopperCoinEntity(World world, LivingEntity owner){
        super(ReplyEntities.COPPER_COIN, owner, world);
    }

    @Override
    protected void onCollision(HitResult hitResult){
        super.onCollision(hitResult);
        if(!world.isClient) {
            if(hitResult instanceof EntityHitResult entityHitResult) {
                Entity target = entityHitResult.getEntity();
                target.damage(DamageSource.thrownProjectile(this, getOwner()), 1);
            }
        }
        spawnParticles();

        discard();
    }

    private void spawnParticles(){
        if(world instanceof ServerWorld serverWorld){
            serverWorld.spawnParticles(
                    ParticleTypes.CRIT, getX(), getY(), getZ(),
                    10,
                    0.2,
                    0.2,
                    0.2,
                    0.1
            );
        }
    }

    @Override
    protected float getGravity() {
        return 0.01F;
    }

    @Override
    public void tick(){
        super.tick();

        if(!world.isClient){
            LivingEntity target = findTarget();
            if(target != null) {
                Vec3d direction = target.getPos().add(0,target.getHeight()/2,0).subtract(getPos()).normalize();
                double dot = getVelocity().normalize().dotProduct(direction);
                if (dot > 0) {
                    Vec3d velocity = getVelocity();
                    setVelocity(velocity.lerp(direction.multiply(1.5), 0.5));
                }
            }
        }
    }

    private LivingEntity findTarget(){
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, getBoundingBox().expand(8), entity -> entity != getOwner() && entity.isAlive());
        return entities.isEmpty() ? null : entities.get(0);
    }

    @Override
    protected Item getDefaultItem() {
        return ReplyItems.COPPER_COIN;
    }

}