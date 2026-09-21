package com.nmy_prime.client;

import com.nmy_prime.items.entities.ReplyEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ReplyEntityRenderers {
    public static void register(){
        EntityRendererRegistry.register(ReplyEntities.COPPER_COIN, FlyingItemEntityRenderer::new);

    }
}
