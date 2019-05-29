package com.dev.kcraft.engine.render.texture

import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.glGenerateMipmap
import java.nio.ByteBuffer


object TextureLoader {

    fun loadTexture(fileName: String): Texture {

        //load png file
        val decoder = PNGDecoder(TextureLoader.javaClass.getResourceAsStream("/textures/$fileName"))

        //create a byte buffer big enough to store RGBA values
        val buffer = ByteBuffer.allocateDirect(4 * decoder.width * decoder.height)

        //decode
        decoder.decode(buffer, decoder.width * 4, PNGDecoder.Format.RGBA)

        //flip the buffer so its ready to read
        buffer.flip()

        //create a texture
        val id = glGenTextures()

        //bind the texture
        glBindTexture(GL_TEXTURE_2D, id)

        //tell opengl how to unpack bytes
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        var glLin = 0x2601


        //set the texture parameters, can be GL_LINEAR or GL_NEAREST
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, glLin.toFloat())
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, glLin.toFloat())

        //upload texture
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.width, decoder.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer)

        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D)

        return Texture(id)
    }

}