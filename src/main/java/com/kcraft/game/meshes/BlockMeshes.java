package com.kcraft.game.meshes;

import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.render.model.OBJLoader;

public class BlockMeshes {

    public static Mesh simpleBlockMesh = null;




    public static void createMeshes() {
        try {
            simpleBlockMesh = OBJLoader.loadMesh("cube.obj");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
