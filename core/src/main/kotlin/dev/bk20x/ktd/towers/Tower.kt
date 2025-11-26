package dev.bk20x.ktd.towers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.google.gson.JsonObject
import dev.bk20x.ktd.Globals
import dev.bk20x.ktd.animations.AnimatedEntity
import dev.bk20x.ktd.data.GameDataLoader

class Tower(name: String): AnimatedEntity() {



    var cost:   Int = 0
    var health: Float = 0f

    init {
        this.name = name
        GameDataLoader.setTowerValues(this)
        activeAnimation = "active"
    }


    override fun update(delta: Float) {
        super.update(delta)
    }

    override fun render(sb: SpriteBatch) {
        this.playActiveAnimation(sb)
    }

    companion object {
        fun NewTower(name: String): Tower {
            return Tower(name)
        }
    }

    override fun dispose() {
        super.dispose()
    }

    override fun toString(): String {
        return "Tower(name='$name', cost=$cost, health=$health)"
    }
}
