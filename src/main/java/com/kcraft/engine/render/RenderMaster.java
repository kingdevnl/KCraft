package com.kcraft.engine.render;

import com.kcraft.engine.GameItem;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.math.Transformation;
import com.kcraft.engine.render.model.OBJLoader;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.texture.TextureLoader;
import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

@Getter
public class RenderMaster {

    @Setter
    private Display display;
    @Setter
    private Shader shader;
    private float FOV = (float) Math.toRadians(60.0f);
    private float Z_NEAR = 0.01f;

    private float Z_FAR = 100.0f;

    private Matrix4f projectionMatrix;


    @Getter
    private Transformation transformation = new Transformation();


    public void init() {
        float aspectRatio = (float) display.getWidth() / display.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shader.createUniform("projectionMatrix");
        shader.createUniform("modelViewMatrix");
        shader.createUniform("colour");
        shader.createUniform("useColour");


        int size = 10;
        try {

            Mesh mesh = OBJLoader.loadMesh("cube.obj");
            Texture texture = TextureLoader.loadTexture("grassblock.png");
            mesh.setTexture(texture);
            for (int x = 0; x < size; x++) {

                for (int z = 0; z < size; z++) {


                    GameItem gameItem = new GameItem(mesh);
                    gameItem.setScale(0.5f);
                    gameItem.setPosition(x, 0, -2 + -z);

                    gameItems.add(gameItem);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


//
//        testMesh = new Mesh(positions, textCoords, indices, TextureLoader.loadTexture("grassblock.png"));
//        GameItem testItem = new GameItem(testMesh);
//        float rotation = testItem.getRotation().x + 1.5f;
//        testItem.setRotation(rotation, rotation, rotation);
//        testItem.setPosition(0, 0, -1.5f);
//        gameItems.add(testItem);

    }


    public void renderMesh(Mesh mesh) {
        if (mesh.getTexture() != null) {
            // Activate firs texture bank
            glActiveTexture(GL_TEXTURE0);
            // Bind the texture
            glBindTexture(GL_TEXTURE_2D, mesh.getTexture().getTextureID());
        }

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private ArrayList<GameItem> gameItems = new ArrayList<>();

    public void render(Camera camera) {
        shader.bind();
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, display.getWidth(), display.getHeight(), Z_NEAR, Z_FAR);

        shader.setUniform("projectionMatrix", projectionMatrix);
        Matrix4f viewMatrix = transformation.getViewMatrix(camera);
        for (GameItem gameItem : gameItems) {
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shader.setUniform("modelViewMatrix", modelViewMatrix);
            shader.setUniform("colour", gameItem.getMesh().getColour());
            shader.setUniform("useColour", gameItem.getMesh().hasTexture() ? 0 : 1);

            glEnable(GL_DEPTH_TEST);
            renderMesh(gameItem.getMesh());
            glDisable(GL_DEPTH_TEST);
        }
    }


}
