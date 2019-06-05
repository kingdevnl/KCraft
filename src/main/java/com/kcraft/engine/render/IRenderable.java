package com.kcraft.engine.render;

import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.state.GameState;

public interface IRenderable {

    void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState renderState, GameState gameState);
}
