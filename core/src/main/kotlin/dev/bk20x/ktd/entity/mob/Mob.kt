package dev.bk20x.ktd.entity.mob


import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import dev.bk20x.ktd.Utils.getDirectionTo
import dev.bk20x.ktd.animations.AnimatedEntity
import dev.bk20x.ktd.data.GameDataLoader

open class Mob(name: String): AnimatedEntity() {

    var health   = 0f
    var damage   = 0f
    var speed    = 0f

    init {
        this.name = name
        GameDataLoader.setMobValues(this)
    }

    override fun update(delta: Float) {
        super.update(delta)
        this.updateBody(delta)
    }

    override fun render(sb: SpriteBatch) {
        super.render(sb)
    }

    fun moveToTarget(target: Vector2){
        val direction       = this.body.direction
        val velocity        = this.body.velocity
        this.body.direction = this.body.position.getDirectionTo(target)
        velocity.set(direction.cpy().nor().scl(this.speed))
    }


    override fun updateBody(delta: Float) {
        super.updateBody(delta)
        val position  = this.body.position
        val velocity  = this.body.velocity
        position.add(velocity.scl(delta))
    }

    override fun dispose() {
        super.dispose()
    }

    override fun toString(): String {
        return "Mob(name=$name, health=$health, damage=$damage, speed=$speed)"
    }

    override fun isAlive(): Boolean = super.isAlive() && health > 0f

}
