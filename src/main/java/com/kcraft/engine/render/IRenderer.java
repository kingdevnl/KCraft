package com.kcraft.engine.render;

import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.shader.Shader;

public interface IRenderer {

    void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader);
}
