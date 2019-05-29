#version 400 core

in vec3 verices;
in vec2 textCoords;

out vec2 passTextCoords;


void main() {

    gl_Position = vec4(verices, 1.0);
    passTextCoords = textCoords;
}