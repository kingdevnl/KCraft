package com.dev.kcraft.engine.display

import com.dev.kcraft.engine.render.ModelRender
import com.dev.kcraft.engine.render.model.TexturedModel
import com.dev.kcraft.engine.render.model.UntexturedModel
import com.dev.kcraft.engine.render.shader.BasicShader
import com.dev.kcraft.engine.render.texture.Texture
import com.dev.kcraft.engine.render.texture.TextureLoader
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.MemoryUtil.NULL

class Display(var width: Int, var height: Int, var title: String) {

    var windowID: Long = 0



    private var debug = false


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


    }

    fun render() {


    }

    fun shouldShow(): Boolean {
        return !glfwWindowShouldClose(this.windowID)
    }

    fun clearColor(r: Float, g: Float, b: Float) {
        glClearColor(r, g, b, 0f)
    }

}
