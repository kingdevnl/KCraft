#version 400 core

in vec3 position;
in vec2 textCoords;

out vec2 passTextCoords;

uniform mat4 transformation;

void main() {

    gl_Position = transformation*vec4(position, 1.0);
    passTextCoords = textCoords;
}