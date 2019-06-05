package com.kcraft.engine;

import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.utils.ITickable;
import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;

@Getter
public class GameItem {

    @Setter
    private Mesh mesh;
    private Vector3f position;
    @Setter
    private float scale;
    private Vector3f rotation;


    @Getter
    @Setter
    private ITickable tickable;


    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = .5f;
        rotation = new Vector3f(0, 0, 0);
    }




    public boolean hasTickable() {
        return tickable != null;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setPosition(Vector3f pos) {
        this.position = pos;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void setTexture(Texture texture) {
        mesh.setTexture(texture);
    }

}
