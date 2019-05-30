package com.kcraft.engine.display;


import com.kcraft.engine.EngineMaster;
import lombok.Getter;
import org.lwjgl.opengl.GL;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display {


    @Getter
    private int width, height;
    private String title;

    private long window;

    private boolean debug = true;

    public HashMap<Integer, Boolean> keys = new HashMap<>();

    public Display(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;


        if (!glfwInit()) {
            System.err.println("Error initializing glfw");
            System.exit(1);
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        if (debug)
            glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

        window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);
        GL.createCapabilities();

        glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {

            if (!keys.containsKey(key)) {
                keys.put(key, false);
            }

            if (action == GLFW_PRESS) {

                keys.replace(key, true);
                EngineMaster.INSTANCE.onKeyPress(key);
            }
            if (action == GLFW_RELEASE) {
                keys.replace(key, false);

                EngineMaster.INSTANCE.onKeyRelease(key);

            }

        });
    }


    public boolean isKeyDown(int key) {
        if (keys.containsKey(key)) {
            return keys.get(key);

        }
        return false;
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public void clearColor(float r, float g, float b) {
        glClearColor(r, g, b, 0f);
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            glfwShowWindow(window);

        } else {
            glfwHideWindow(window);
        }

    }

    public long getWindowID() {
        return window;
    }
}