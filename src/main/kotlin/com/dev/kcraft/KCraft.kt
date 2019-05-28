package com.dev.kcraft

import com.dev.kcraft.display.Display

object KCraft {

   lateinit var display: Display


    fun start() {

    println("Loading up KCraft.")
        display = Display(1080, 720, "KCraft")
        display.create()


        display.clearColor()
        while(display.shouldShow()) {
            display.render()
        }
    }

}
