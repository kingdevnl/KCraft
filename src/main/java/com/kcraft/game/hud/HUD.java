package com.kcraft.game.hud;

import com.kcraft.engine.EngineMaster;
import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderable;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.state.GameState;
import com.kcraft.engine.texture.Texture;
import com.kcraft.engine.utils.IOUtils;
import com.kcraft.game.KCraft;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoaderEx;
import org.joml.Vector3f;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import static com.kcraft.engine.utils.ColourUtils.rgba;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;
import static org.lwjgl.opengl.GL11.*;

public class HUD implements IRenderable {

    private Texture texture;

    private long vg;

    private NVGColor colour;


    private static final String FONT_NAME = "Roboto";

    ByteBuffer fontBuffer;

    private int font;


    private DoubleBuffer posx;

    private DoubleBuffer posy;


    private JImGui imGui;


    public void init() {
//        texture = TextureLoader.loadTexture("hud.png");


        vg = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
        if (vg == 0) {
            System.err.println("Error initializing NanoVG!");
        }
        colour = NVGColor.create();

        try {
            fontBuffer = IOUtils.ioResourceToByteBuffer("/fonts/Roboto.ttf", 150 * 1024);
        } catch (IOException e) {
            e.printStackTrace();

            System.exit(1);
        }
        font = nvgCreateFontMem(vg, FONT_NAME, fontBuffer, 0);
        if (font == -1) {
            System.err.println("Error creating font!");
            System.exit(1);
        }
        posx = MemoryUtil.memAllocDouble(1);
        posy = MemoryUtil.memAllocDouble(1);

        System.out.println("ptr: " + KCraft.engine.display.getWindowID());


        JniLoaderEx.loadGlfw();


        imGui = JImGui.fromExistingPointer(KCraft.engine.display.getWindowID());


    }


    public void renderTextAlfa(int x, int y, String text, float fontSize, float alfa) {
        // Render hour text
        nvgFontSize(vg, fontSize);
        nvgFontFace(vg, FONT_NAME);
        nvgTextAlign(vg, NVG_ALIGN_LEFT | NVG_ALIGN_TOP);
        nvgFillColor(vg, rgba(2, 2, 2, alfa, colour));

        nvgText(vg, x, y, text);

    }


    public void renderText(int x, int y, String text, float fontSize) {

        renderTextAlfa(x, y, text, fontSize, 255);
    }

    @Override
    public void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState renderState, GameState gameState) {

        if(gameState != GameState.INGAME) {
            return;
        }

        if (renderState == RenderState.POST) {

            boolean prevWireModState = KCraft.engine.wireFrameMode;

            if (prevWireModState) {
                glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            }


            glPushMatrix();


            nvgBeginFrame(vg, display.getWidth(), display.getHeight(), 1);

            Vector3f position = EngineMaster.INSTANCE.camera.getPosition();

            renderText(20, 10, String.format("Pos: x: %d, y: %d, z: %d", (int) position.x, (int) position.y, (int) position.z), 24);

            Vector3f rotation = EngineMaster.INSTANCE.camera.getRotation();


            renderText(20, 35, String.format("Rot: x: %f, y: %f", rotation.x, rotation.y), 24);

            renderText(20, 60, String.format("Blocks: %d", KCraft.kCraft.getWorld().getBlocks().size()), 24);


            renderText(20, display.getHeight() - 40, "Press insert to place a block", 24);
            renderText(20, display.getHeight() - 24, "Press delete to remove a block", 24);


            renderText(display.getWidth() - 45, 10, "V0.1", 24);

            nvgEndFrame(vg);


            renderTextAlfa(display.getWidth() / 2, display.getHeight() / 2 - 80, "+", 80, 200);


            display.restoreState();

            glPopMatrix();


            if (prevWireModState) {
                glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            }
        }


    }
}
