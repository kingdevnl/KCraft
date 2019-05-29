package com.dev.kcraft.engine.render.model

import com.dev.kcraft.engine.math.MatrixMath
import org.joml.Matrix4f
import org.joml.Vector3f

class ModelEntity(var model: TexturedModel, var position: Vector3f, var angle: Vector3f, var scale: Vector3f) {

    constructor(model: TexturedModel) : this(model, Vector3f(0f, 0f, 0f), Vector3f(0f, 0f, 0f), Vector3f(1f, 1f, 1f))

    public fun getTranslationMatrix(): Matrix4f {
        return MatrixMath.createTransformationMatrix(position, angle, scale)
    }
}