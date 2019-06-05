package com.kcraft.game.world;

import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderable;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.state.GameState;
import com.kcraft.engine.utils.VectorUtils;
import com.kcraft.game.KCraft;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;
import lombok.Getter;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class World implements IRenderable {


    @Getter
    private String name;
    @Getter
    private int size;

    @Getter
    private List<Block> blocks = new CopyOnWriteArrayList<Block>();

    public World(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void genWorld() {
        generateFlatWorld();
    }

    public void generateFlatWorld() {

        Thread thread = new Thread(() -> {
            for (int x = 0; x < size; x++) {
                if(KCraft.engine.display.isCloseRequested()) {
                    return;
                }
                for (int z = 0; z < size; z++) {
                    if(KCraft.engine.display.isCloseRequested()) {
                        return;
                    }
                    Block grass = new Block(BlockType.GRASS);
                    grass.setPosition(1 + x, 0, -2 + -z);
                    addBlock(grass);

                    Block cobble = new Block(BlockType.COBBLE);
                    cobble.setPosition(1 + x, -1, -2 + -z);
                    addBlock(cobble);

                    cobble = new Block(BlockType.COBBLE);
                    cobble.setPosition(1 + x, -2, -2 + -z);
                    addBlock(cobble);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "WorldGenerator");

        thread.start();

    }

    @Override
    public void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState renderState, GameState gameState) {
        if(KCraft.engine.getGameState() != GameState.INGAME) {
            return;
        }
        if (renderState == RenderState.PRE) {
            for (Block block : blocks) {
                renderMaster.renderGameItem(block);
            }

        }
    }

    public Block getBlockAtPosition(int x, int y, int z) {
        Optional<Block> first = blocks.stream().filter(block -> block.getPosition().x == x && block.getPosition().y == y && block.getPosition().z == z).findFirst();

        if (first.isPresent()) {
            return first.get();
        }

        return null;

    }

    public void setBlock(int x, int y, int z, Block set) {
        Optional<Block> first = blocks.stream().filter(block -> block.getPosition().x == x && block.getPosition().y == y && block.getPosition().z == z).findFirst();

        if (first.isPresent()) {
            Block found = first.get();

            blocks.remove(found);
            if (set != null) {
                addBlock(set);
            }
        }

    }

    public void addBlock(Block block) {

        Vector3f position = VectorUtils.normalize3f(block.getPosition());
        Vector3i posI = VectorUtils.convertToInt(position);

        if (getBlockAtPosition(posI.x, posI.y, posI.z) == null) {
            block.setPosition(position);

            this.blocks.add(block);
        }

    }

    public void stop() {

    }
}
