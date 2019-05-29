package com.dev.kcraft

import com.dev.kcraft.engine.display.Display
import com.dev.kcraft.engine.render.ModelRender
import com.dev.kcraft.engine.render.shader.BasicShader
import com.dev.kcraft.test.Models
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_DEPTH_TEST
import org.lwjgl.opengl.GL11.glEnable


object KCraft {

    lateinit var display: Display

    lateinit var shader: BasicShader
    lateinit var modelRender: ModelRender


    var frames = 0

    var tickInterval = 1

    fun tick() {


        frames = 0

    }

    fun start() {

        println("Loading up KCraft.")
        display = Display(1080, 720, "KCraft")
        display.create()

        shader = BasicShader()
        shader.create()
        modelRender = ModelRender(shader)
        Models.create()


        Models.cubeModelEntity.angle.y =4f
        display.clearColor(0f, 0f, 0f)
        while (display.shouldShow()) {

            glEnable(GL_DEPTH_TEST)
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

            shader.bind()

            GL11.glColor3f(1f, 0f, 0f)
            modelRender.renderModelEntity(Models.cubeModelEntity)



            Models.cubeModelEntity.angle.y +=.02f
            Models.cubeModelEntity.angle.x +=.02f

            

            frames++
            if (frames >= tickInterval) {
                tick()
            }


            GLFW.glfwSwapBuffers(this.display.windowID)

            GLFW.glfwPollEvents()

        }
        println("Stop")




        Models.cleanup()
        shader.remove()

    }

}
