package com.kcraft.engine.texture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lwjgl.opengl.GL11;

@AllArgsConstructor
@Getter
public class Texture {
    private int textureID;

    public void remove() {
        GL11.glDeleteTextures(textureID);
        System.out.println("Deleted texture " + textureID);
    }

}
