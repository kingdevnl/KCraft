package com.kcraft.engine.utils;

import com.kcraft.engine.EngineMaster;
import com.kcraft.engine.render.RenderMaster;

public interface ITickable {
    public void tick(EngineMaster engineMaster, RenderMaster renderMaster);
}
