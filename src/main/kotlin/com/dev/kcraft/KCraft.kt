package com.dev.kcraft

import com.dev.kcraft.engine.display.Display
import com.dev.kcraft.engine.render.model.TexturedModel

object KCraft {

    lateinit var display: Display


    fun start() {

        println("Loading up KCraft.")
        display = Display(1080, 720, "KCraft")
        display.create()




        display.clearColor(0f, 0f, 0f)
        while (display.shouldShow()) {
            display.render()
        }
        println("Stop")
        display.myTexturedModel.remove()
        display.myShader.remove()

    }

}
