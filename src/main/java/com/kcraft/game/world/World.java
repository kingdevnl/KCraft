package com.kcraft.game.world;

import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderer;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;

import java.util.ArrayList;

public class World implements IRenderer {


    private String name;
    private int size;

    private ArrayList<Block> blocks = new ArrayList<>();

    public World(String name, int size) {
        this.name = name;
        this.size = size;
    }


    public void generateFlatWorld() {
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                Block block = new Block(BlockType.GRASS);
                block.setPosition(1 + x, 0, -2 + -z);

                blocks.add(block);
            }
        }
    }

    @Override
    public void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState state) {
        if(state == RenderState.PRE) {
            for (Block block : blocks) {
                renderMaster.renderGameItem(block);
            }

        }

    }
}
