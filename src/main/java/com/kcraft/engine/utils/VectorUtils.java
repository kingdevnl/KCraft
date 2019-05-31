package com.kcraft.engine.utils;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class VectorUtils {

    public static Vector3f normalize3f(Vector3f vector) {

        return new Vector3f(Math.round(vector.x), Math.round(vector.y), Math.round(vector.z));
    }

    public static Vector3i convertToInt(Vector3f vector) {

        return new Vector3i(Math.round(vector.x), Math.round(vector.y), Math.round(vector.z));
    }
}

