package com.kcraft.game.res;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kcraft.engine.render.Mesh;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.texture.TextureLoader;
import com.kcraft.game.KCraft;
import com.kcraft.game.block.Block;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockLoader {


    public static void loadBlocks() {
        String text = readFile("/res/blocks.json");
        JsonArray jsonArray = KCraft.gson.fromJson(text, JsonArray.class);


    }


    public static String readFile(String file) {

        InputStream stream = BlockLoader.class.getResourceAsStream(file);
        try {
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            String data = new String(bytes);
            stream.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return "error";

    }

}
