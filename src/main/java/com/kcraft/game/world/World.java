package com.kcraft.game.world;

import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderer;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.utils.VectorUtils;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;
import lombok.Getter;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Optional;

public class World implements IRenderer {


    @Getter
    private String name;
    @Getter
    private int size;

    @Getter
    private ArrayList<Block> blocks = new ArrayList<>();

    public World(String name, int size) {
        this.name = name;
        this.size = size;
    }


    public void generateFlatWorld() {
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                Block grass = new Block(BlockType.GRASS);
                grass.setPosition(1 + x, 0, -2 + -z);
                addBlock(grass);

                Block cobble = new Block(BlockType.COBBLE);
                cobble.setPosition(1+x, -1, -2+-z);
                addBlock(cobble);
            }
        }
    }

    @Override
    public void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState state) {
        if (state == RenderState.PRE) {
            for (Block block : blocks) {
                renderMaster.renderGameItem(block);
            }

        }
    }

    public Block getBlockAtPosition(int x, int y, int z) {
        Optional<Block> first = blocks.stream().filter(block -> block.getPosition().x == x && block.getPosition().y == y && block.getPosition().z == z).findFirst();

        if(first.isPresent()) {
            return first.get();
        }

        return null;

    }

    public void setBlock(int x, int y, int z, Block set) {
        Optional<Block> first = blocks.stream().filter(block -> block.getPosition().x == x && block.getPosition().y == y && block.getPosition().z == z).findFirst();

        if(first.isPresent()) {
            Block found = first.get();

            blocks.remove(found);
            if(set != null) {
                addBlock(set);
            }
        }

    }

    public void addBlock(Block block) {

        Vector3f position = VectorUtils.normalize3f(block.getPosition());
        Vector3i posI = VectorUtils.convertToInt(position);

        if(getBlockAtPosition(posI.x ,posI.y, posI.z) == null) {
            block.setPosition(position);

            this.blocks.add(block);
        }

    }

    public void stop() {

    }
}
