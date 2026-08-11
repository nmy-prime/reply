package com.nmy_prime.reverse;

import com.google.common.collect.Sets;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class NewLightningEntity extends LightningEntity {

    public NewLightningEntity(EntityType<? extends LightningEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.ambientTick = 2;
        this.seed = this.random.nextLong();
        this.remainingActions = this.random.nextInt(3) + 1;
        this.cosmetic = false;
    }

    private int ambientTick;
    public long seed;
    private int remainingActions;
    private final boolean cosmetic;
    @Nullable
    private ServerPlayerEntity channeler;
    private final Set<Entity> struckEntities = Sets.newHashSet();
    private int blocksSetOnFire;

    private void powerLightningRod() {
        BlockPos blockPos = this.getAffectedBlockPos();
        BlockState blockState = this.world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.LIGHTNING_ROD)) {
            ((LightningRodBlock)blockState.getBlock()).setPowered(blockState, this.world, blockPos);
        }

    }

    @Override
    public void tick() {
        if (this.ambientTick == 2) {
            if (this.world.isClient()) {
                this.world.playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 10000.0F, 0.8F + this.random.nextFloat() * 0.2F, false);
                this.world.playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 2.0F, 0.5F + this.random.nextFloat() * 0.2F, false);
            } else {
                this.powerLightningRod();
                cleanOxidization(this.world, this.getAffectedBlockPos());
                this.emitGameEvent(GameEvent.LIGHTNING_STRIKE);
            }
        }

        --this.ambientTick;
        if (this.ambientTick < 0) {
            if (this.remainingActions == 0) {
                if (this.world instanceof ServerWorld) {
                    List<Entity> difficulty = this.world.getOtherEntities(this, new Box(this.getX() - (double)15.0F, this.getY() - (double)15.0F, this.getZ() - (double)15.0F, this.getX() + (double)15.0F, this.getY() + (double)6.0F + (double)15.0F, this.getZ() + (double)15.0F), (entity) -> entity.isAlive() && !this.struckEntities.contains(entity));

                    for(ServerPlayerEntity serverPlayerEntity : ((ServerWorld)this.world).getPlayers((serverPlayerEntityx) -> serverPlayerEntityx.distanceTo(this) < 256.0F)) {
                        Criteria.LIGHTNING_STRIKE.trigger(serverPlayerEntity, this, difficulty);
                    }
                }

                this.discard();
            } else if (this.ambientTick < -this.random.nextInt(10)) {
                --this.remainingActions;
                this.ambientTick = 1;
                this.seed = this.random.nextLong();
            }
        }

        if (this.ambientTick >= 0) {
            if (!(this.world instanceof ServerWorld)) {
                this.world.setLightningTicksLeft(2);
            } else if (!this.cosmetic) {
                List<Entity> difficulty = this.world.getOtherEntities(this, new Box(this.getX() - (double)3.0F, this.getY() - (double)3.0F, this.getZ() - (double)3.0F, this.getX() + (double)3.0F, this.getY() + (double)6.0F + (double)3.0F, this.getZ() + (double)3.0F), entity -> entity.isAlive() && !(entity instanceof ItemEntity));

                for(Entity serverPlayerEntity : difficulty) {
                    serverPlayerEntity.onStruckByLightning((ServerWorld)this.world, this);
                }

                this.struckEntities.addAll(difficulty);
                if (this.channeler != null) {
                    Criteria.CHANNELED_LIGHTNING.trigger(this.channeler, difficulty);
                }
            }
        }

    }

    private BlockPos getAffectedBlockPos() {
        Vec3d vec3d = this.getPos();
        return new BlockPos(vec3d.x, vec3d.y - 1.0E-6, vec3d.z);
    }


    private static void cleanOxidization(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        BlockPos blockPos;
        BlockState blockState2;
        if (blockState.isOf(Blocks.LIGHTNING_ROD)) {
            blockPos = pos.offset(((Direction)blockState.get(LightningRodBlock.FACING)).getOpposite());
            blockState2 = world.getBlockState(blockPos);
        } else {
            blockPos = pos;
            blockState2 = blockState;
        }

        if (blockState2.getBlock() instanceof Oxidizable) {
            world.setBlockState(blockPos, Oxidizable.getUnaffectedOxidationState(world.getBlockState(blockPos)));
            BlockPos.Mutable mutable = pos.mutableCopy();
            int i = world.random.nextInt(3) + 3;

            for(int j = 0; j < i; ++j) {
                int k = world.random.nextInt(8) + 1;
                cleanOxidizationAround(world, blockPos, mutable, k);
            }

        }
    }

    private static void cleanOxidizationAround(World world, BlockPos pos, BlockPos.Mutable mutablePos, int count) {
        mutablePos.set(pos);

        for(int i = 0; i < count; ++i) {
            Optional<BlockPos> optional = cleanOxidizationAround(world, mutablePos);
            if (!optional.isPresent()) {
                break;
            }

            mutablePos.set((Vec3i)optional.get());
        }

    }

    private static Optional<BlockPos> cleanOxidizationAround(World world, BlockPos pos) {
        for(BlockPos blockPos : BlockPos.iterateRandomly(world.random, 10, pos, 1)) {
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.getBlock() instanceof Oxidizable) {
                Oxidizable.getDecreasedOxidationState(blockState).ifPresent((state) -> world.setBlockState(blockPos, state));
                world.syncWorldEvent(3002, blockPos, -1);
                return Optional.of(blockPos);
            }
        }

        return Optional.empty();
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}