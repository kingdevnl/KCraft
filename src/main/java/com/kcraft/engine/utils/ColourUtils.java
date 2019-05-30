package com.kcraft.engine.utils;

import org.lwjgl.nanovg.NVGColor;

public class ColourUtils {
    public static NVGColor setColour(String hex, NVGColor colour) {
        colour.r(Integer.valueOf(hex.substring(1, 3), 16) / 255f);
        colour.g(Integer.valueOf(hex.substring(3, 5), 16) / 255f);
        colour.b(Integer.valueOf(hex.substring(5, 7), 16) / 255f);
        colour.a(Integer.valueOf(hex.substring(7, 9), 16) / 255f);
        return colour;
    }

    public static NVGColor setColour(String hex) {
        return setColour(hex, NVGColor.create());
    }

    public static NVGColor setColour(float r, float g, float b, float a) {
        return setColour(r, g, b, a, NVGColor.create());
    }

    public static NVGColor setColour(float r, float g, float b, float a, NVGColor colour) {
        colour.r(r);
        colour.g(g);
        colour.b(b);
        colour.a(a);
        return colour;
    }

    public static NVGColor rgba(float r, float g, float b, float a, NVGColor colour) {
        return setColour(r / 255f, g / 255f, b / 255f, a / 255f, colour);
    }

    public static NVGColor rgba(float r, float g, float b, float a) {
        return setColour(r / 255f, g / 255f, b / 255f, a / 255f);
    }
}
