package com.kcraft.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kcraft.engine.IGameLogic;
import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.utils.VectorUtils;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;
import com.kcraft.game.hud.HUD;
import com.kcraft.game.meshes.BlockMeshes;
import com.kcraft.game.world.World;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;


public class KCraft extends IGameLogic {

    public static Gson gson = new GsonBuilder().create();
    public static Random random = new Random();


    private HUD hud = new HUD();

    public static KCraft kCraft;


    public static void main(String[] args) {
        engine.setGameLogic(new KCraft());
        engine.start();
    }


    private World world;


    @Override
    public void init(Display display) {
        kCraft = this;
        world = new World("Default", 30);
        try {
            BlockMeshes.createMeshes();

            addRenderer(world);
            addRenderer(hud);

            world.generateFlatWorld();
            engine.camera.setPosition(7, .9f, -1);



        } catch (Exception e) {
            e.printStackTrace();
        }

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        hud.init();


        engine.startLoop();

    }

    @Override
    public boolean canCameraMove(Camera camera, Vector3f oldPos, Vector3f position) {

        if(position.y < oldPos.y) {
            //Going down

            Vector3i blockPos = VectorUtils.convertToInt(position);


        }


        return true;
    }

    @Override
    public void render(RenderMaster renderMaster, Camera camera, Shader shader, RenderState state) {

        if (state == RenderState.PRE) {
            GL11.glClearColor(100f / 255f, 149f / 255f, 237f / 255f, 1f);
        }
    }


    private int frames;

    @Override
    public void update() {

        frames++;
        if (frames >= 10) {

            frames = 0;
        }

    }

    @Override
    public void onKeyPress(int key) {
        if(key == GLFW.GLFW_KEY_INSERT) {

            Vector3f position = engine.camera.getPosition();
            Block block = new Block(BlockType.COBBLE);
            block.setPosition(position.x, position.y, position.z);
            world.addBlock(block);

        }
        if(key == GLFW.GLFW_KEY_DELETE) {
            Vector3i pos = VectorUtils.convertToInt(VectorUtils.normalize3f(engine.camera.getPosition()));

            world.setBlock(pos.x, pos.y, pos.z, null);
        }
    }

    public void stop() {
        world.stop();
        world.getBlocks().clear();
        BlockMeshes.cobbleMesh.remove();
        BlockMeshes.grassMesh.remove();
        BlockMeshes.simpleBlockMesh.remove();
    }

    public World getWorld() {
    return this.world;
    }
}
