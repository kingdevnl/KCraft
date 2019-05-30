package com.kcraft.engine.texture;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class TextureLoader {

    public static Texture loadTexture(String name) {
        try {
            PNGDecoder decoder = new PNGDecoder(TextureLoader.class.getResourceAsStream("/textures/"+name));
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
            decoder.decode(byteBuffer, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
            byteBuffer.flip();
            int id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            float glLin = 0x2601;
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, glLin);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, glLin);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);
            glGenerateMipmap(GL_TEXTURE_2D);
            return new Texture(id);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}

