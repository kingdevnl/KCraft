package com.kcraft.engine;

import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;

public abstract class IGameLogic {


    public static EngineMaster engine = EngineMaster.INSTANCE;

    public abstract void init(Display display);

    public abstract void render(RenderMaster renderMaster, Camera camera, Shader shader, RenderState state);

    public abstract void update();

    public abstract void onKeyPress(int key);

    public abstract void onKeyRelease(int key);

    public abstract void stop();


    public RenderMaster getRenderMaster() {
        return engine.renderMaster;
    }

    public void addGameItem(GameItem gameItem) {
        getRenderMaster().getGameItems().add(gameItem);
    }



}
