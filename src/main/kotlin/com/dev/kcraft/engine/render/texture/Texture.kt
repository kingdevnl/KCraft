package com.dev.kcraft.engine.render.texture

import org.lwjgl.opengl.GL11

class Texture(val id: Int) {

    fun remove() {
        GL11.glDeleteTextures(id)
    }
}