package com.dev.kcraft

import com.dev.kcraft.engine.display.Display
import com.dev.kcraft.engine.render.ModelRender
import com.dev.kcraft.engine.render.model.TexturedModel
import com.dev.kcraft.engine.render.shader.BasicShader
import com.dev.kcraft.engine.render.shader.Shader
import com.dev.kcraft.test.Models
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11

object KCraft {

    lateinit var display: Display

    lateinit var shader: BasicShader
    lateinit var modelRender: ModelRender

    fun start() {

        println("Loading up KCraft.")
        display = Display(1080, 720, "KCraft")
        display.create()

        shader = BasicShader()
        shader.create()
        modelRender = ModelRender(shader)
        Models.create()


        display.clearColor(0f, 0f, 0f)
        while (display.shouldShow()) {

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)


            shader.bind()
            modelRender.renderModelEntity(Models.testEntity)

            GLFW.glfwSwapBuffers(this.display.windowID)

            GLFW.glfwPollEvents()

        }
        println("Stop")




        Models.cleanup()
        shader.remove()

    }

}
