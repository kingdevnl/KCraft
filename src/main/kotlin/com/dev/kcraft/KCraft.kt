package com.dev.kcraft

import com.dev.kcraft.display.Display
import java.util.*

object KCraft {

    lateinit var display: Display


    fun start() {

        println("Loading up KCraft.")
        display = Display(1080, 720, "KCraft")
        display.create()




        display.clearColor(0f, 0f,  0f)
        while (display.shouldShow()) {
            display.render()
        }
        println("Stop")
        display.myModel.remove()
        display.myShader.remove()

    }

}
