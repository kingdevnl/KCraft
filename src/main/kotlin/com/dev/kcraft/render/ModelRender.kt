package com.dev.kcraft.render

import com.dev.kcraft.model.Model
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

object ModelRender {


    public fun renderModel(model: Model) {


        GL30.glBindVertexArray(model.vertexArrayID)

        GL20.glEnableVertexAttribArray(0)

       GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0)

        GL20.glDisableVertexAttribArray(0)


        GL30.glBindVertexArray(0)
    }

}