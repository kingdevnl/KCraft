#version 400 core

in vec3 position;
in vec2 textCoords;

out vec2 passTextCoords;

uniform float myScale;

void main() {

    gl_Position = vec4(position*myScale, 1.0);
    passTextCoords = textCoords;
}