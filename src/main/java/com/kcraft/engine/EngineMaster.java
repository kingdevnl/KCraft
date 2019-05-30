package com.kcraft.engine;

import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.input.MouseInput;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public enum EngineMaster {
    INSTANCE;


    public Display display;

    public Shader baseShader;

    public RenderMaster renderMaster = new RenderMaster();

    public Camera camera;
    private Vector3f cameraInc = new Vector3f(0, 0, 0);
    private static final float CAMERA_POS_STEP = 0.05f;
    private static final float MOUSE_SENSITIVITY = 0.2f;
    private MouseInput mouseInput;

    private boolean wireFrameMode = false;
    public void start() {
        display = new Display(1080, 720, "KCraft");

        display.setVisible(true);

        baseShader = new Shader("vertex.fs", "fragment.fs");
        baseShader.create();

        renderMaster.setDisplay(display);
        renderMaster.setShader(baseShader);

        camera = new Camera();
        camera.setPosition(5, 1.4f, -3);
        renderMaster.init();
        mouseInput = new MouseInput();
        mouseInput.init(display);


//        glfwSetInputMode(display.getWindowID(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);




    }

    public void startLoop() {
        while (!display.isCloseRequested()) {

            glfwPollEvents();

            if (display.isKeyDown(GLFW_KEY_ESCAPE)) {
                glfwSetInputMode(display.getWindowID(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
            }

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            if(wireFrameMode) {
                glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
            } else {
                glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            }
            renderMaster.render(camera);
            display.swapBuffers();
            handleCameraInput();
            camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        }

        baseShader.remove();
    }

    private void handleCameraInput() {
        cameraInc.set(0, 0, 0);
        if (display.isKeyDown(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (display.isKeyDown(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (display.isKeyDown(GLFW_KEY_A)) {
            cameraInc.x = -1.5f;
        } else if (display.isKeyDown(GLFW_KEY_D)) {
            cameraInc.x = 1.5f;
        }
        if (display.isKeyDown(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (display.isKeyDown(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }


        if (display.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            cameraInc.y = 1;
        } else if (display.isKeyDown(GLFW_KEY_LEFT_CONTROL)) {
            cameraInc.y = -1;
        }


        int speedInc = 9;

        if (display.isKeyDown((GLFW_KEY_LEFT))) {
            camera.moveRotation(0, -MOUSE_SENSITIVITY * speedInc, 0);
        }
        if (display.isKeyDown((GLFW_KEY_RIGHT))) {
            camera.moveRotation(0, MOUSE_SENSITIVITY * speedInc, 0);
        }

        if (display.isKeyDown((GLFW_KEY_UP))) {
            camera.moveRotation(-MOUSE_SENSITIVITY * speedInc, 0, 0);
        }
        if (display.isKeyDown((GLFW_KEY_DOWN))) {
            camera.moveRotation(MOUSE_SENSITIVITY * speedInc, 0, 0);
        }


        mouseInput.input(display);
//        if (!mouseInput.isRightButtonPressed()) {
//            Vector2f rotVec = mouseInput.getDisplVec();
//
//            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
//        }
    }

    public void onKeyPress(int key) {
        if(key == GLFW_KEY_F3) {
            wireFrameMode =!wireFrameMode;
        }

    }

    public void onKeyRelease(int key) {
    }

    private void render() {
    }


    private void updateState() {
    }


}
