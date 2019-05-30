package com.kcraft.engine.render;

import com.kcraft.engine.GameItem;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.math.Transformation;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.texture.TextureLoader;
import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;

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

    private Mesh testMesh;

    @Getter
    private Transformation transformation = new Transformation();


    public void init() {
        float aspectRatio = (float) display.getWidth() / display.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shader.createUniform("projectionMatrix");
        shader.createUniform("modelViewMatrix");

        float[] positions = new float[]{
                // V0
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,
                // For text coords in top face
                // V8: V4 repeated
                -0.5f, 0.5f, -0.5f,
                // V9: V5 repeated
                0.5f, 0.5f, -0.5f,
                // V10: V0 repeated
                -0.5f, 0.5f, 0.5f,
                // V11: V3 repeated
                0.5f, 0.5f, 0.5f,
                // For text coords in right face
                // V12: V3 repeated
                0.5f, 0.5f, 0.5f,
                // V13: V2 repeated
                0.5f, -0.5f, 0.5f,
                // For text coords in left face
                // V14: V0 repeated
                -0.5f, 0.5f, 0.5f,
                // V15: V1 repeated
                -0.5f, -0.5f, 0.5f,
                // For text coords in bottom face
                // V16: V6 repeated
                -0.5f, -0.5f, -0.5f,
                // V17: V7 repeated
                0.5f, -0.5f, -0.5f,
                // V18: V1 repeated
                -0.5f, -0.5f, 0.5f,
                // V19: V2 repeated
                0.5f, -0.5f, 0.5f,};
        float[] textCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                // For text coords in top face
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,
                // For text coords in right face
                0.0f, 0.0f,
                0.0f, 0.5f,
                // For text coords in left face
                0.5f, 0.0f,
                0.5f, 0.5f,
                // For text coords in bottom face
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f,};
        int[] indices = new int[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                8, 10, 11, 9, 8, 11,
                // Right face
                12, 13, 7, 5, 12, 7,
                // Left face
                14, 15, 6, 4, 14, 6,
                // Bottom face
                16, 18, 19, 17, 16, 19,
                // Back face
                4, 6, 7, 5, 4, 7,};

        testMesh = new Mesh(positions, textCoords, indices, TextureLoader.loadTexture("grassblock.png"));
        GameItem testItem = new GameItem(testMesh);
        float rotation = testItem.getRotation().x + 1.5f;
        testItem.setRotation(rotation, rotation, rotation);
        testItem.setPosition(0, 0, -1.5f);
        gameItems.add(testItem);

    }


    public void renderMesh(Mesh mesh) {
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);



        //
//        GL13.glActiveTexture(GL13.GL_TEXTURE0)
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.texture.id)

        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);


        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

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

            Vector3f currentRot = gameItem.getRotation();

            currentRot.x+=.2f;
            currentRot.y+=.2f;
            gameItem.setRotation(currentRot.x, currentRot.y, currentRot.z);

            glEnable(GL_DEPTH_TEST);
            renderMesh(gameItem.getMesh());
            glDisable(GL_DEPTH_TEST);
        }
    }


}
