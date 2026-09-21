package com.nmy_prime.events;

import com.nmy_prime.Main;
import com.nmy_prime.client.ReplyEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ClientEventHandler implements ClientModInitializer {
    private static int lastSlot = -1;

    @Override
    public void onInitializeClient() {
        ReplyEntityRenderers.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            int currentSlot = client.player.getInventory().selectedSlot;
            if (currentSlot != lastSlot) {
                lastSlot = currentSlot;
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeInt(currentSlot);
                ClientPlayNetworking.send(new Identifier(Main.ModId, "switch_slot"), buf);
            }
        });
    }
}
