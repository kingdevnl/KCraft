package com.kcraft.engine.camera;

import com.kcraft.engine.EngineMaster;
import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

public class Camera {


    @Getter
    @Setter
    private Vector3f position, rotation;

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Camera() {
        this.position = new Vector3f(0f, 0f, 0f);
        this.rotation = new Vector3f(0f, 0f, 0f);
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
        rotation.x += offsetX;
        rotation.y += offsetY;
        rotation.z += offsetZ;
    }
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        Vector3f oldPos = new Vector3f(position.x, position.y, position.z);
        if ( offsetZ != 0 ) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if ( offsetX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
        }
        position.y += offsetY;

        if(!EngineMaster.INSTANCE.getGameLogic().canCameraMove(this, oldPos, position)) {
            this.position.x = oldPos.x;
            this.position.y = oldPos.y;
            this.position.z = oldPos.z;
        }


    }

}
