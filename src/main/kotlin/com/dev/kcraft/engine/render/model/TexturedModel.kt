package com.dev.kcraft.engine.render.model

import com.dev.kcraft.engine.render.texture.Texture

import org.lwjgl.opengl.GL30


class TexturedModel(var vertices: FloatArray, var textureCoords: FloatArray , var indices: IntArray, var texture: Texture) : BaseModel() {


    init {

        this.vertexArrayID = super.createVertexArray()
        this.indicesBufferID = super.bindIndicesBuffer(this.indices)
        this.vertexBufferID = super.storeData(0, 3, vertices)
        this.textureCoordsBufferID = super.storeData(1, 2, textureCoords)
        vertexCount = indices.size

        GL30.glBindVertexArray(0)


    }

    override fun remove() {

        GL30.glDeleteVertexArrays(this.vertexArrayID)
        GL30.glDeleteBuffers(this.vertexBufferID)
        GL30.glDeleteBuffers(this.textureCoordsBufferID)
        GL30.glDeleteBuffers(this.indicesBufferID)

        texture.remove()

    }




}