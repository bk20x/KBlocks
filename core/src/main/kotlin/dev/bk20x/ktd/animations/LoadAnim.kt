package dev.bk20x.ktd.animations

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.google.gson.JsonObject

object LoadAnim {

    fun loadAnim(obj: JsonObject): Animation<TextureRegion> {
        val texture        = Texture(obj.get("source").asString)
        val frameWidth     = obj.get("frameWidth").asInt
        val frameHeight    = obj.get("frameHeight").asInt
        val frameDuration  = obj.get("frameDuration").asFloat
        val textureRegions = TextureRegion.split(texture, frameWidth, frameHeight)[0]
        val keyFrames      = obj.get("frames").asJsonArray.map { textureRegions[it.asInt] }
        return Animation<TextureRegion>(frameDuration, *keyFrames.toTypedArray())
    }

    fun loadAnim(obj: JsonObject, source: String): Animation<TextureRegion> {
        val texture        = Texture(source)
        val frameWidth     = obj.get("frameWidth").asInt
        val frameHeight    = obj.get("frameHeight").asInt
        val frameDuration  = obj.get("frameDuration").asFloat
        val textureRegions = TextureRegion.split(texture, frameWidth, frameHeight)[0]
        val keyFrames      = obj.get("frames").asJsonArray.map { textureRegions[it.asInt] }
        return Animation<TextureRegion>(frameDuration, *keyFrames.toTypedArray())
    }

    fun loadAnim(obj: JsonObject, textureRegions: Array<TextureRegion>): Animation<TextureRegion> {
        val frameDuration  = obj.get("frameDuration").asFloat
        val keyFrames      = obj.get("frames").asJsonArray.map { textureRegions[it.asInt] }
        return Animation<TextureRegion>(frameDuration, *keyFrames.toTypedArray())
    }

    fun loadAnim(obj: JsonObject, texture: Texture): Animation<TextureRegion> {
        val frameWidth     = obj.get("frameWidth").asInt
        val frameHeight    = obj.get("frameHeight").asInt
        val frameDuration  = obj.get("frameDuration").asFloat
        val textureRegions = TextureRegion.split(texture, frameWidth, frameHeight)[0]
        val keyFrames      = obj.get("frames").asJsonArray.map { textureRegions[it.asInt] }
        return Animation<TextureRegion>(frameDuration, *keyFrames.toTypedArray())
    }
}
