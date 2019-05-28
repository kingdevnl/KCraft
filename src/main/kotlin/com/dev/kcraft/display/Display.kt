package com.dev.kcraft.display

import org.lwjgl.glfw.Callbacks.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.*
import org.lwjgl.system.MemoryUtil.*

class Display(var width: Int, var height: Int, var title: String) {

    var windowID: Long = 0


    fun create() {

        if (!glfwInit()) {
            System.err.println("Error initializing glfw")
            System.exit(1)
        }

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        windowID = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL)

        glfwMakeContextCurrent(this.windowID)
        glfwSwapInterval(1)
        glfwShowWindow(windowID)

        GL.createCapabilities()

    }

    fun render() {

        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

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
