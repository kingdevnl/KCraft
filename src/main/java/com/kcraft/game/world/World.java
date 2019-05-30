package com.kcraft.game.world;

import com.kcraft.game.KCraft;
import com.kcraft.game.block.Block;
import com.kcraft.game.block.BlockType;

import java.util.ArrayList;

public class World {


    private String name;
    private int size;

    private ArrayList<Block> blocks = new ArrayList<>();

    public World(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void updateRenderItems() {
        for (Block block : blocks) {

            if (!KCraft.engine.getGameLogic().getRenderMaster().getGameItems().contains(block)) {
                KCraft.engine.getGameLogic().getRenderMaster().getGameItems().add(block);
            }
        }
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
}
