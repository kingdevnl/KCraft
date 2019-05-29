package com.dev.kcraft.render.shader


class BasicShader : Shader(VERTEX_FILE, FRAGMENT_FILE) {


    companion object {
        private val VERTEX_FILE = "shaders/basicVertexShader.fs"
        private val FRAGMENT_FILE = "shaders/basicFragmentShader.fs"
    }

    override fun bindAllAttributes() {
        super.bindAttribute(0, "vertices")
    }

}
