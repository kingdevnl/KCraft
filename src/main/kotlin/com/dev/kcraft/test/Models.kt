package com.dev.kcraft.test

import com.dev.kcraft.engine.render.model.BaseModel
import com.dev.kcraft.engine.render.model.ModelEntity
import com.dev.kcraft.engine.render.model.TexturedModel
import com.dev.kcraft.engine.render.texture.Texture
import com.dev.kcraft.engine.render.texture.TextureLoader
import org.joml.Vector3f

object Models {

    lateinit var testModel: TexturedModel
    lateinit var testModelTexture: Texture

    lateinit var testEntity: ModelEntity

    var models: ArrayList<BaseModel> = ArrayList()

    fun create() {
        testModelTexture = TextureLoader.loadTexture("dirt.png")
        testModel = TexturedModel(
            floatArrayOf(
                -0.5f, 0.5f, 0f,  //TOP LEFT V0
                0.5f, 0.5f, 0f,  //TOP RIGHT V1
                0.5f, -0.5f, 0f, //BOTTOM RIGHT V2
                -0.5f, -0.5f, 0f  //BOTTOM LEFT V3
            ),
            floatArrayOf(
                0f, 0f,           //TOP LEFT V0
                1f, 0f,           //TOP RIGHT V1
                1f, 1f,           //BOTTOM RIGHT V2
                0f, 1f            //BOTTOM LEFT V3
            ),
            intArrayOf(
                0, 1, 2,        //Triangle 1
                2, 3, 0         //Triangle 2
            ),
            testModelTexture
        )
        models.add(testModel)

        testEntity = ModelEntity(testModel, Vector3f(-1f, 0f,0f), Vector3f(0f, 0f, 0f), Vector3f(1f, 0.5f, 1f) )


    }
    fun cleanup() {
        models.forEach(BaseModel::remove)
    }
}