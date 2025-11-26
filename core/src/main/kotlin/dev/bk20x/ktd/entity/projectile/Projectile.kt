package dev.bk20x.ktd.entity.projectile

import com.badlogic.gdx.math.Vector2
import dev.bk20x.ktd.animations.AnimatedEntity
import dev.bk20x.ktd.data.GameDataLoader

class Projectile(name: String): AnimatedEntity() {

    var speed = 0f
    var start = Vector2()

    init {
        this.name = name
        GameDataLoader.setProjectileValues(this)
        activeAnimation = "active"
    }

    override fun update(delta: Float) {
        super.update(delta)
        val pos = body.position
        val vel = body.velocity
        pos.add(vel.cpy().scl(delta))
    }

    fun init(src: Vector2, target: Vector2) {
        this.start           = src.cpy()
        this.body.position   = src.cpy()
        this.body.direction  = target.cpy().nor()
        this.body.velocity   = this.body.direction.cpy().scl(speed)
    }
}
