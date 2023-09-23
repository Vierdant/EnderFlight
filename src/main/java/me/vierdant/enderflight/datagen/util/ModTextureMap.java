package me.vierdant.enderflight.datagen.util;

import net.minecraft.block.Block;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;

public class ModTextureMap extends TextureMap {

    public static TextureMap sideTopBottom(Block block) {
        return (new TextureMap())
                .put(TextureKey.SIDE, getSubId(block, "_side"))
                .put(TextureKey.TOP, getSubId(block, "_top"))
                .put(TextureKey.BOTTOM, getSubId(block, "_bottom"))
                .put(TextureKey.UP, getSubId(block, "_top"))
                .put(TextureKey.DOWN, getSubId(block, "_bottom"))
                .put(TextureKey.PARTICLE, getSubId(block, "_side"));
    }
}
