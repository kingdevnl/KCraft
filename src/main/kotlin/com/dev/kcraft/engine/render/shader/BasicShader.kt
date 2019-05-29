package com.dev.kcraft.engine.render.shader

import org.joml.Matrix4f


class BasicShader : Shader(VERTEX_FILE, FRAGMENT_FILE) {


    private var scaleLocation: Int = 0
    private var transformationLocation: Int =0

    companion object {
        private val VERTEX_FILE = "basicVertexShader.fs"
        private val FRAGMENT_FILE = "basicFragmentShader.fs"
    }

    override fun bindAllAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textCoords")
    }

    override fun getAllUniforms() {
        transformationLocation = super.getUniform("transformation")
    }

    public fun loadTransformationMatrix(matrix: Matrix4f) {
        super.loadMatrixUniform(transformationLocation, matrix)

    }
}
