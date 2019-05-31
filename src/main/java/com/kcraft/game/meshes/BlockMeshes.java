package com.kcraft.game.meshes;

import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.render.model.OBJLoader;
import com.kcraft.engine.texture.TextureLoader;

public class BlockMeshes {

    public static Mesh simpleBlockMesh = null;
    public static Mesh grassMesh = null;
    public static Mesh cobbleMesh = null;




    public static void createMeshes() {
        try {
            simpleBlockMesh = OBJLoader.loadMesh("block.obj");
            grassMesh = OBJLoader.loadMesh("block.obj");
            grassMesh.setTexture(TextureLoader.loadTexture("grass.png"));


            cobbleMesh = OBJLoader.loadMesh("cube.obj");
            cobbleMesh.setTexture(TextureLoader.loadTexture("cobble.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
