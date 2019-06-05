package com.kcraft.engine;

import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderable;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.state.GameState;
import com.kcraft.engine.utils.ITickable;
import org.joml.Vector3f;

import java.util.List;

public abstract class IGameLogic {


    public static EngineMaster engine = EngineMaster.INSTANCE;

    public abstract void init(Display display);

    public void render(RenderMaster renderMaster, Camera camera, Shader shader, RenderState state) {

    }

    public void update() {

    }

    public void onKeyPress(int key){}

    public void onKeyRelease(int key){}

    public abstract void stop();

    public void onWindowResize(long window, int width, int height){}

    public boolean canCameraMove(Camera camera, Vector3f oldPos, Vector3f position) {

        return true;
    }


    public RenderMaster getRenderMaster() {
        return engine.renderMaster;
    }

    public Display getDisplay() {
        return engine.display;
    }

    public void addGameItem(GameItem gameItem) {
        getRenderMaster().getGameItems().add(gameItem);
    }
    public void addRenderable(IRenderable renderer) {
        engine.getRenderers().add(renderer);
    }
    public void addTickable(ITickable tickable) {
        engine.getTickables().add(tickable);
    }
    public List<GameItem> getGameItems() {
        return engine.renderMaster.getGameItems();
    }

    public GameState getGameState() {
        return engine.getGameState();
    }
    public void setGameState(GameState state) {
        engine.setGameState(state);
    }


}
