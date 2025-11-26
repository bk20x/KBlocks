package dev.bk20x.ktd.entity.mob

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.gson.JsonObject
import dev.bk20x.ktd.Globals
import dev.bk20x.ktd.animations.AnimatedEntity
import dev.bk20x.ktd.data.GameDataLoader

open class Mob(name: String): AnimatedEntity() {

    var health   = 0f
    var damage   = 0f
    var speed    = 0f

    init {
        this.name = name
        GameDataLoader.setMobValues(this)
        val texturePath = "${Globals.MOB_ASSET_PATH}/${this.name}.png"
        val texture = Texture(texturePath)
        val mobAnimations = GameDataLoader.objFromFile<JsonObject>(Globals.MOB_ANIM_PATH)!!
        this.setAnimations(mobAnimations, this.name, texture)
    }

    override fun update(delta: Float) {
        super.update(delta)
    }

    override fun render(sb: SpriteBatch) {
        this.playActiveAnimation(sb)
    }

    override fun dispose() {
        super.dispose()
    }

    override fun toString(): String {
        return "Mob(name=$name, health=$health, damage=$damage, speed=$speed)"
    }

    override fun isAlive(): Boolean = super.isAlive() && health > 0f

}
