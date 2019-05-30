package com.kcraft.game.hud;

import com.kcraft.engine.RenderState;
import com.kcraft.engine.camera.Camera;
import com.kcraft.engine.display.Display;
import com.kcraft.engine.render.IRenderer;
import com.kcraft.engine.render.RenderMaster;
import com.kcraft.engine.shader.Shader;
import com.kcraft.engine.texture.Texture;

public class HUD implements IRenderer {

    private Texture texture;

    public void init() {
//        texture = TextureLoader.loadTexture("hud.png");
    }

    @Override
    public void render(RenderMaster renderMaster, Display display, Camera camera, Shader shader, RenderState state) {

        if (state == RenderState.POST) {


//
//            glPushMatrix();
//
//            glRotatef(180, 0, 1, 0);
//            glRotatef(-90, 0, 0, 1);
//
//
//            glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
//            glEnable(GL_TEXTURE_2D);
//
//            glBegin(GL_QUADS);
//
//            glTexCoord2f(0, 0);
//            glVertex3f(-.5f, .5f, 0); //T left
//
//            glTexCoord2f(0, 1);
//            glVertex3f(.5f, .5f, 0); //T right
//
//            glTexCoord2f(1, 1);
//            glVertex3f(.5f, -.5f, 0); //B right
//
//            glTexCoord2f(1, 0);
//            glVertex3f(-.5f, -.5f, 0); //B left
//
//
//            glEnd();
//
//            glPopMatrix();

        }


    }
}
