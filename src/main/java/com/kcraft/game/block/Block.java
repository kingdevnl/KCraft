package com.kcraft.game.block;

import com.kcraft.engine.GameItem;
import com.kcraft.game.meshes.BlockMeshes;
import lombok.Getter;
import lombok.Setter;

public class Block extends GameItem {

    @Getter
    @Setter
    private String name;


    public Block(BlockType type) {
        super(BlockMeshes.simpleBlockMesh);

        setScale(.5f);

        if(type == BlockType.GRASS) {
            setMesh(BlockMeshes.grassMesh);
        }
        if(type == BlockType.COBBLE) {
            setMesh(BlockMeshes.cobbleMesh);
        }

    }

}
