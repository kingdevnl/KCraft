package com.kcraft.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kcraft.engine.GameItem;
import com.kcraft.engine.IGameLogic;
import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;
import com.kcraft.game.meshes.BlockMeshes;
import com.kcraft.game.world.World;

import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;


public class KCraft extends IGameLogic {

    public static Gson gson = new GsonBuilder().create();
    public static Random random = new Random();


    public static void main(String[] args) {
        engine.setGameLogic(new KCraft());
        engine.start();
    }


    private World world;


    @Override
    public void init(Display display) {

        world = new World("Default", 20);
        try {
            BlockMeshes.createMeshes();
            world.generateFlatWorld();
            world.updateRenderItems();
        } catch (Exception e) {
            e.printStackTrace();
        }

        engine.startLoop();

    }

    @Override
    public void render(RenderMaster renderMaster, Camera camera, Shader shader, RenderState state) {

    }


    private int frames;
    @Override
    public void update() {

        frames++;
        if(frames >= 10) {
            world.updateRenderItems();
            frames =0;
        }

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
