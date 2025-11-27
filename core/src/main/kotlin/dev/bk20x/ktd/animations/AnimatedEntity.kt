package dev.bk20x.ktd.animations

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.google.gson.JsonObject
import dev.bk20x.ktd.entity.Entity

open class AnimatedEntity: Entity() {

    val animations: MutableMap<String, Animation<TextureRegion>> =
        mutableMapOf()
    var activeAnimation: String = ""
        set(anim){
            if(!animations.containsKey(anim)){
                System.err.println("No animation $anim for entity $name of type ${this::class.simpleName}")
                return
            }
            field = anim
        }
    var animationFinished: Boolean = false

    fun playActiveAnimation(sb: SpriteBatch) {
        val animation = animations[activeAnimation] ?: return
        sb.draw(animation.getKeyFrame(stateTime, true), getX(), getY())
        animationFinished = animation.isAnimationFinished(stateTime)
    }

    open fun setAnimations(animationObj: JsonObject, name: String, texture: Texture) {
        val animation = animationObj[name].asJsonObject ?: return
        for (k in animation.asMap().keys) {
            val key = k as String
            this.putAnimation(key, LoadAnim.loadAnim(animation[key].asJsonObject, texture))
        }
    }

    override fun render(sb: SpriteBatch) {
        this.playActiveAnimation(sb)
    }

    override fun update(delta: Float) {
        super.update(delta)
    }

    override fun dispose(){
        for(animation in animations.values){
            animation.keyFrames.forEach {
                it.texture.dispose()
            }
        }
        animations.clear()
    }

    fun putAnimation(name: String, animation: Animation<TextureRegion>){
        animations[name] = animation
    }

    fun resetAnimationTime() {
        stateTime = 0f
    }
}
