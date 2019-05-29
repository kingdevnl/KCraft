package com.dev.kcraft.engine.math

import org.joml.Matrix4f
import org.joml.Vector3f

class MatrixMath {
    companion object {
        public fun createTransformationMatrix(translation: Vector3f, rotation: Vector3f, scale: Vector3f): Matrix4f {
            val matrix = Matrix4f().identity()!!
            matrix.translate(translation.x, translation.y, translation.z)
            matrix.rotateAffineXYZ(rotation.x, rotation.y, rotation.z)
            matrix.scale(scale.x, scale.y, scale.z)
            return matrix
        }
    }
}