package com.dev.kcraft.engine.render.model

import org.lwjgl.opengl.GL30.*

class UntexturedModel(var vertices: FloatArray, var indices: IntArray) : BaseModel() {


    init {

        this.vertexArrayID = super.createVertexArray()
        this.indicesBufferID = super.bindIndicesBuffer(this.indices)
        this.vertexBufferID = super.storeData(0, 3, vertices)
        vertexCount = indices.size

        glBindVertexArray(0)


    }

    override fun remove() {

        glDeleteVertexArrays(this.vertexArrayID)
        glDeleteBuffers(this.vertexBufferID)
        glDeleteBuffers(this.indicesBufferID)
    }

    fun getVertexC(): Int {

        return vertexCount
    }


}