package com.kcraft.engine.shader;

import lombok.Getter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    @Getter
    private int programID, vertexShaderID, fragmentShaderID;

    @Getter
    private String vertexShaderFile, fragmentShaderFile;

    private final Map<String, Integer> uniforms;
    public Shader(String vertexShaderFile, String fragmentShaderFile) {
        this.vertexShaderFile = vertexShaderFile;
        this.fragmentShaderFile = fragmentShaderFile;
        uniforms = new HashMap<>();
    }

    public void create() {
        programID = glCreateProgram();
        vertexShaderID = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertexShaderID, readFile(vertexShaderFile));
        compileShader(vertexShaderID, true);


        fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, readFile(fragmentShaderFile));
        compileShader(fragmentShaderID, false);

        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, fragmentShaderID);


        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Error linking shader " + glGetShaderInfoLog(programID));
        }

        glValidateProgram(programID);

        if(glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.err.println("Error validating shader " + glGetShaderInfoLog(programID));
        }

    }

    void compileShader(int shaderID, boolean vertex) {
        glCompileShader(shaderID);
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Error compiling vertex "+vertex + " "  + glGetShaderInfoLog(shaderID));
        }

    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(programID, uniformName);
        uniforms.put(uniformName, uniformLocation);
    }
    public void setUniform(String uniformName, Matrix4f value) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(uniforms.get(uniformName), false, fb);
        }
    }
    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
    }
    public void bind() {
        glUseProgram(programID);
    }
    public void remove() {
        glDetachShader(programID, fragmentShaderID);
        glDetachShader(programID, vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteProgram(programID);
        System.out.println("Deleted shader "+programID);
    }

    private String readFile(String file) {

        try {
            InputStream stream = getClass().getResourceAsStream("/shaders/" + file);
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            stream.close();
            String code = new String(bytes);

            return code;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        return "error";
    }
}
