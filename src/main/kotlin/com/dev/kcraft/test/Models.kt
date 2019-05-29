package com.dev.kcraft.test

import com.dev.kcraft.engine.render.model.BaseModel
import com.dev.kcraft.engine.render.model.ModelEntity
import com.dev.kcraft.engine.render.model.TexturedModel
import com.dev.kcraft.engine.render.model.UntexturedModel
import com.dev.kcraft.engine.render.texture.Texture
import com.dev.kcraft.engine.render.texture.TextureLoader

object Models {

    lateinit var testModel: TexturedModel
    lateinit var testModelTexture: Texture

    lateinit var testModelEntity: ModelEntity

    lateinit var cubeModel: TexturedModel
    lateinit var cubeModelEntity: ModelEntity

    var models: ArrayList<BaseModel> = ArrayList()

    fun create() {

        testModelTexture = TextureLoader.loadTexture("grassblock.png")
        cubeModel = TexturedModel(
            floatArrayOf(
                // V0
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,

                // For text coords in top face
                // V8: V4 repeated
                -0.5f, 0.5f, -0.5f,
                // V9: V5 repeated
                0.5f, 0.5f, -0.5f,
                // V10: V0 repeated
                -0.5f, 0.5f, 0.5f,
                // V11: V3 repeated
                0.5f, 0.5f, 0.5f,

                // For text coords in right face
                // V12: V3 repeated
                0.5f, 0.5f, 0.5f,
                // V13: V2 repeated
                0.5f, -0.5f, 0.5f,

                // For text coords in left face
                // V14: V0 repeated
                -0.5f, 0.5f, 0.5f,
                // V15: V1 repeated
                -0.5f, -0.5f, 0.5f,

                // For text coords in bottom face
                // V16: V6 repeated
                -0.5f, -0.5f, -0.5f,
                // V17: V7 repeated
                0.5f, -0.5f, -0.5f,
                // V18: V1 repeated
                -0.5f, -0.5f, 0.5f,
                // V19: V2 repeated
                0.5f, -0.5f, 0.5f
            ),
            floatArrayOf(

                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,

                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,

                // For text coords in top face
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,

                // For text coords in right face
                0.0f, 0.0f,
                0.0f, 0.5f,

                // For text coords in left face
                0.5f, 0.0f,
                0.5f, 0.5f,

                // For text coords in bottom face
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f
            ),
            intArrayOf(
                0, 1, 3, 3, 1, 2,
                // Top Face
                8, 10, 11, 9, 8, 11,
                // Right face
                12, 13, 7, 5, 12, 7,
                // Left face
                14, 15, 6, 4, 14, 6,
                // Bottom face
                16, 18, 19, 17, 16, 19,
                // Back face
                4, 6, 7, 5, 4, 7
            ),
            testModelTexture
        )


//        testModel = TexturedModel(
//            floatArrayOf(
//                -0.5f, 0.5f, 0f,  //TOP LEFT V0
//                0.5f, 0.5f, 0f,  //TOP RIGHT V1
//                0.5f, -0.5f, 0f, //BOTTOM RIGHT V2
//                -0.5f, -0.5f, 0f  //BOTTOM LEFT V3
//            ),
//            floatArrayOf(
//                0f, 0f,           //TOP LEFT V0
//                1f, 0f,           //TOP RIGHT V1
//                1f, 1f,           //BOTTOM RIGHT V2
//                0f, 1f            //BOTTOM LEFT V3
//            ),
//            intArrayOf(
//                0, 1, 2,        //Triangle 1
//                2, 3, 0         //Triangle 2
//            ),
//            testModelTexture
//        )
//        models.add(testModel)

        models.add(cubeModel)

//        testModelEntity = ModelEntity(testModel)

        cubeModelEntity = ModelEntity(cubeModel)


    }

    fun cleanup() {
        models.forEach(BaseModel::remove)
    }
}