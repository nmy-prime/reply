package com.nmy_prime.blocks;

import com.nmy_prime.Main;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ReplyBlocks {

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(Main.ModId, "incarnon_craft_block"), INCARNON_CRAFT_BLOCK);

    }

    public static final Block INCARNON_CRAFT_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));

}
