package com.kcraft.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kcraft.engine.GameItem;
import com.kcraft.engine.IGameLogic;
import com.kcraft.engine.RenderState;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.render.model.OBJLoader;
import com.kcraft.engine.state.GameState;
import com.kcraft.engine.texture.TextureLoader;
import com.kcraft.engine.utils.ITickable;
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

import static org.lwjgl.opengl.GL11.*;


public class KCraft extends IGameLogic {

    public static Gson gson = new GsonBuilder().create();

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



        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);


        world = new World("Default", 60);
        try {
            BlockMeshes.createMeshes();


            addRenderable(world);
            addRenderable(hud);

            world.genWorld();
            engine.camera.setPosition(7, .9f, -1);


        } catch (Exception e) {

            e.printStackTrace();
        }

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        hud.init();

        GL11.glClearColor(100f / 255f, 149f / 255f, 237f / 255f, 1f);

        setGameState(GameState.INGAME);


        engine.startLoop();

    }


    @Override
    public void onKeyPress(int key) {
        if (key == GLFW.GLFW_KEY_INSERT) {

            Vector3f position = engine.camera.getPosition();
            Block block = new Block(BlockType.COBBLE);
            block.setPosition(position.x, position.y, position.z);
            world.addBlock(block);

        }
        if (key == GLFW.GLFW_KEY_DELETE) {
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
