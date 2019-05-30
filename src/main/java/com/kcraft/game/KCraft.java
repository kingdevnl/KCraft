package com.kcraft.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kcraft.engine.GameItem;
import com.kcraft.engine.IGameLogic;
import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.render.model.OBJLoader;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.texture.TextureLoader;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;
import com.kcraft.game.meshes.BlockMeshes;

import java.util.ArrayList;
import java.util.Random;


public class KCraft extends IGameLogic {

    public static Gson gson = new GsonBuilder().create();
    public static Random random = new Random();


    public static void main(String[] args) {
        engine.setGameLogic(new KCraft());
        engine.start();
    }


    private ArrayList<GameItem> blocks = new ArrayList<>();

    @Override
    public void init(Display display) {

        try {
            BlockMeshes.createMeshes();

            for (int x = 0; x < 20; x++) {
                for (int z = 0; z < 20; z++) {
                    Block block = new Block(BlockType.GRASS);

                    block.setPosition(1+x, 0, -2+-z);

                    addGameItem(block);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        engine.startLoop();

    }

    @Override
    public void render(RenderMaster renderMaster, Camera camera, Shader shader, RenderState state) {

    }


    @Override
    public void update() {

    }


    @Override
    public void onKeyPress(int key) {

    }

    @Override
    public void onKeyRelease(int key) {

    }

    @Override
    public void stop() {

    }
}
