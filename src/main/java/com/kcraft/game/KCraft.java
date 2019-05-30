package com.kcraft.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kcraft.engine.EngineMaster;
import com.kcraft.engine.GameItem;
import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.render.model.OBJLoader;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.texture.TextureLoader;
import com.kcraft.game.meshes.BlockMeshes;
import com.kcraft.game.res.BlockLoader;

import java.util.Random;


public class KCraft {
    public static Gson gson = new GsonBuilder().create();
    public static Random random = new Random();

    public static void main(String[] args) {


        EngineMaster.INSTANCE.start();


        BlockMeshes.createMeshes();
        BlockLoader.loadBlocks();


        int size = 40;
        try {

            Mesh grassMesh = OBJLoader.loadMesh("cube.obj");
            Texture grassTexture = TextureLoader.loadTexture("grass.png");
            grassMesh.setTexture(grassTexture);
            Mesh cobbleMesh = OBJLoader.loadMesh("cube.obj");
            Texture cobbleTexture = TextureLoader.loadTexture("cobble.png");
            cobbleMesh.setTexture(cobbleTexture);


            Random random = new Random();
            for (int x = 0; x < size; x++) {

                for (int z = 0; z < size; z++) {

                    int min =1;
                    int max = 2;

                    int r = min+random.nextInt(max);

                    System.out.println(r);

                    GameItem gameItem;

                    if(r == 2) {
                        gameItem = new GameItem(grassMesh);
                    } else {
                        gameItem = new GameItem(cobbleMesh);
                    }


                    gameItem.setPosition(x, 0, -2 + -z);

                    EngineMaster.INSTANCE.renderMaster.getGameItems().add(gameItem);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        EngineMaster.INSTANCE.startLoop();
    }
}
