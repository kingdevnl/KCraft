package com.dev.kcraft.engine.render.shader


class BasicShader : Shader(VERTEX_FILE, FRAGMENT_FILE) {


    private var scaleLocation: Int = 0

    companion object {
        private val VERTEX_FILE = "basicVertexShader.fs"
        private val FRAGMENT_FILE = "basicFragmentShader.fs"
    }

    override fun bindAllAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textCoords")
    }

    override fun getAllUniforms() {
        scaleLocation = super.getUniform("myScale")
        println("loc $scaleLocation")

    }
    public fun loadScale(scale: Float) {
        super.loadFloatUniform(scaleLocation, scale)
    }
}
