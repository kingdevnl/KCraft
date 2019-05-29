package com.dev.kcraft.engine.render.model

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL30.*
import java.nio.IntBuffer
import org.lwjgl.opengl.GL20
import java.nio.FloatBuffer
import org.lwjgl.opengl.GL30




abstract class BaseModel {
    var vertexArrayID: Int = 0
    var vertexBufferID: Int = 0
    var textureCoordsBufferID: Int = 0
    var indicesBufferID: Int = 0
    var vertexCount: Int = 0

    protected fun createVertexArray(): Int {
        val vertexArrayID = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vertexArrayID)
        return vertexArrayID
    }

    protected fun storeData(attributeNumber: Int, coordSize: Int, data: FloatArray): Int {
        val buffer = BufferUtils.createFloatBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        val bufferID = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumber, coordSize, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
        return bufferID
    }

    protected fun bindIndicesBuffer(indices: IntArray): Int {
        val buffer = BufferUtils.createIntBuffer(indices.size)
        buffer.put(indices)
        buffer.flip()
        val indicesBufferID = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferID)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
        return indicesBufferID
    }

    abstract fun remove()

}