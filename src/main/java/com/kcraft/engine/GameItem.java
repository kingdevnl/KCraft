package com.kcraft.engine;

import com.kcraft.engine.render.Mesh;
import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

@Getter
public class GameItem {

    private Mesh mesh;
    private Vector3f position;
    @Setter
    private float scale;
    private Vector3f rotation;

    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
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


}
