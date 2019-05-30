package com.kcraft.engine.math;

import com.kcraft.engine.GameItem;
import com.kcraft.engine.camera.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f projectionMatrix, modelViewMatrix, viewMatrix;

    public Transformation() {
        projectionMatrix = new Matrix4f();
        modelViewMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();

    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {

        float aspect = width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspect, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f getModelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        modelViewMatrix.set(viewMatrix).translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        return modelViewMatrix;
    }

    public Matrix4f getViewMatrix(Camera camera) {

        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        viewMatrix.identity();


        viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));

        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }
}
