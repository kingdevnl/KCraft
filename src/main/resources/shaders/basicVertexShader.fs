#version 400 core

in vec3 verices;

out vec4 color;

void main() {

    gl_Position = vec4(verices, 1.0);
    color = vec4(verices.x + 0.5, 0.0, verices.y + 0.5, 1.0);

}