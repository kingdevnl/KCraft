package com.dev.kcraft.engine.display

import com.sun.org.apache.xpath.internal.operations.Bool
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.glClearColor
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.MemoryUtil.NULL

class Display(var width: Int, var height: Int, var title: String) {

    var windowID: Long = 0

    private var debug = false


    var pressedKeys = ArrayList<Int>()


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



        glfwSetKeyCallback(windowID) { window, key, scancode, action, mods ->

            if (action == GLFW_PRESS) {
                pressedKeys.add(key)
            } else if (action == GLFW_RELEASE) {
                pressedKeys.remove(key)
            }


            if (key == GLFW_KEY_END && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true) // We will detect this in the rendering loop
            }

        }


        glfwMakeContextCurrent(this.windowID)



        glfwSwapInterval(1)
        glfwShowWindow(windowID)

        GL.createCapabilities()
        if (debug)
            GLUtil.setupDebugMessageCallback()

        glfwSetWindowSizeCallback(windowID) {window, width, height ->
            glViewport(0,0, width, height)
        }
    }

    fun isKeyDown(key: Int): Boolean {
        return pressedKeys.contains(key)
    }


    fun shouldShow(): Boolean {
        return !glfwWindowShouldClose(this.windowID)
    }

    fun clearColor(r: Float, g: Float, b: Float) {
        glClearColor(r, g, b, 0f)
    }

}
