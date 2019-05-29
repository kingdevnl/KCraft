package com.dev.kcraft.model

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30.*

class Model(val vertecies: FloatArray, val indices: IntArray) {

    var vertexArrayID: Int = 0
    var vertexBufferID: Int = 0
    var indicesBufferID: Int = 0
    var vertexCount: Int = 0


    init {
        vertexCount = indices.size
        println("VertexCount: $vertexCount")
    }

    public fun create() {


        var buffer = BufferUtils.createFloatBuffer(vertecies.size)
        buffer.put(vertecies)
        buffer.flip()

        var indicesBuffer = BufferUtils.createIntBuffer(indices.size)
        indicesBuffer.put(indices)
        indicesBuffer.flip()

        vertexArrayID = glGenVertexArrays()
        glBindVertexArray(vertexArrayID)

        vertexBufferID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID)
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW)
        indicesBufferID = glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferID)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW)


        glEnableVertexAttribArray(0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
        glBindVertexArray(0)
        glDisableVertexAttribArray(0)


    }

    public fun remove() {
        glDeleteVertexArrays(vertexArrayID)
        glDeleteBuffers(vertexBufferID)
        glDeleteBuffers(indicesBufferID)
    }


}