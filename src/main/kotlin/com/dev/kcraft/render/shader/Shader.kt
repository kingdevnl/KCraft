package com.dev.kcraft.render.shader

import org.lwjgl.opengl.GL11.GL_FALSE
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.*
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

abstract class Shader(var vertexFile: String, var fragmentFile: String) {

    var vertexShaderID = 0
    var fragmentShaderID = 0
    var programID = 0

    public fun create() {
        programID = GL20.glCreateProgram()

        vertexShaderID = GL20.glCreateShader(GL_VERTEX_SHADER)
        GL20.glShaderSource(vertexShaderID, readFile(vertexFile))
        GL20.glCompileShader(vertexShaderID)
        if (GL20.glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Error vertex shader - ${GL20.glGetShaderInfoLog(vertexShaderID)}")
        }


        fragmentShaderID = GL20.glCreateShader(GL_FRAGMENT_SHADER)
        GL20.glShaderSource(fragmentShaderID, readFile(fragmentFile))
        GL20.glCompileShader(fragmentShaderID)
        if (GL20.glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Error fragment shader - ${GL20.glGetShaderInfoLog(fragmentShaderID)}")
        }

        GL20.glAttachShader(programID, vertexShaderID)
        GL20.glAttachShader(programID, fragmentShaderID)

        GL20.glLinkProgram(programID)
        if (GL20.glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Error program link - ${GL20.glGetShaderInfoLog(programID)}")
        }

        GL20.glValidateProgram(programID)
        if (GL20.glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.err.println("Error validate  - ${GL20.glGetShaderInfoLog(programID)}")
        }


    }

    public fun bind() {
        GL20.glUseProgram(programID)

    }
    public fun remove() {
        GL20.glDetachShader(programID, vertexShaderID)
        GL20.glDetachShader(programID, fragmentShaderID)
        GL20.glDeleteShader(vertexShaderID)
        GL20.glDeleteShader(fragmentShaderID)
        GL20.glDeleteProgram(programID)
    }

    public abstract fun bindAllAttributes()
    public fun bindAttribute(index: Int, location: String)  {
        GL20.glBindAttribLocation(programID,index, location)

    }

    private fun readFile(file: String): String {
        try {
            return String(Files.readAllBytes(Paths.get(javaClass.getResource("/$file").toURI())))

        } catch (err: IOException) {
            err.printStackTrace()
            System.exit(1)
        }
        return "error"
    }

}