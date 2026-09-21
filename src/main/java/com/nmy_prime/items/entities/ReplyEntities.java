package com.nmy_prime.items.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ReplyEntities {

    public static final EntityType<CopperCoinEntity> COPPER_COIN =
            register("copper_coin", CopperCoinEntity::new, 0.25f, 0.25f);

    private static <T extends Entity> EntityType<T> register(String id, EntityType.EntityFactory<T> factory, float width, float height){
        return Registry.register(Registry.ENTITY_TYPE, new Identifier("reply", id),
                FabricEntityTypeBuilder
                        .create(SpawnGroup.MISC, factory)
                        .dimensions(EntityDimensions.fixed(width, height))
                        .build());
    }

    public static void registerEntities(){

    }
}