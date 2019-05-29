package com.dev.kcraft.engine.render

import com.dev.kcraft.engine.render.model.BaseModel
import com.dev.kcraft.engine.render.model.TexturedModel
import com.dev.kcraft.engine.render.model.UntexturedModel
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

object ModelRender {


    public fun renderModel(model: BaseModel) {

        if (model is UntexturedModel) {

            GL30.glBindVertexArray(model.vertexArrayID)
            GL20.glEnableVertexAttribArray(0)
            GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0)
            GL20.glDisableVertexAttribArray(0)
            GL30.glBindVertexArray(0)
        }

        if(model is TexturedModel) {

            val texturedModel = model

            GL30.glBindVertexArray(model.vertexArrayID)
            GL20.glEnableVertexAttribArray(0)
            GL20.glEnableVertexAttribArray(1)
            GL13.glActiveTexture(GL13.GL_TEXTURE0)

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.texture.id)

            GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0)
            GL20.glDisableVertexAttribArray(0)
            GL20.glDisableVertexAttribArray(1)
            GL30.glBindVertexArray(0)
        }

    }






















}
