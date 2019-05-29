package com.dev.kcraft.display

import com.dev.kcraft.render.ModelRender
import com.dev.kcraft.render.model.Model
import com.dev.kcraft.render.shader.BasicShader
import com.dev.kcraft.render.shader.Shader
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.MemoryUtil.NULL

class Display(var width: Int, var height: Int, var title: String) {

    var windowID: Long = 0

    lateinit var myModel: Model

    private var debug = false

    lateinit var myShader: BasicShader

    fun create() {

        if (!glfwInit()) {
            System.err.println("Error initializing glfw")
            System.exit(1)
        }

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
        if (debug)
            glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE)

        windowID = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL)

        glfwMakeContextCurrent(this.windowID)
        glfwSwapInterval(1)
        glfwShowWindow(windowID)

        GL.createCapabilities()
        if (debug)
            GLUtil.setupDebugMessageCallback()


        myShader = BasicShader()

        myModel = Model(
            floatArrayOf(
                -0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
            ),
            intArrayOf(
                0, 1, 2, 2, 3, 1

            )
        )



        myShader.create()
        myModel.create()

    }

    fun render() {

        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)


        myShader.bind()
        ModelRender.renderModel(myModel)

        glfwSwapBuffers(this.windowID)

        glfwPollEvents()
    }

    fun shouldShow(): Boolean {
        return !glfwWindowShouldClose(this.windowID)
    }

    fun clearColor() {
        glClearColor(1.0f, 0f, 0f, 0f)
    }

}
