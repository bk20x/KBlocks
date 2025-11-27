package dev.bk20x.ktd.entity.mob


import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import dev.bk20x.ktd.utils.Utils.getDirectionTo
import dev.bk20x.ktd.animations.AnimatedEntity
import dev.bk20x.ktd.data.GameDataLoader
import dev.bk20x.ktd.entity.Entity
import dev.bk20x.ktd.entity.EntityState
import dev.bk20x.ktd.types.CardinalDirection

open class Mob(name: String): AnimatedEntity() {

    var damage   = 0f
    var range    = 0f
    var speed    = 0f
    var atkSpeed: Float = 0f

    private var attackCooldown: Float = 0f
    private var cooldownTimer: Float = 0f
    var attackReady = false


    init {
        this.name = name
        GameDataLoader.setMobValues(this)
        attackCooldown = 1.0f / atkSpeed
    }

    override fun update(delta: Float) {
        super.update(delta)
        this.updateBody(delta)
        this.tickAttackCooldown(delta)
        if(animationFinished || activeAnimation.isBlank()) this.activeAnimation = CardinalDirection.fromVector(body.direction).toString().lowercase()
    }

    override fun render(sb: SpriteBatch) {
        super.render(sb)
    }

    protected fun tickAttackCooldown(delta: Float) {
        cooldownTimer += delta
        if(cooldownTimer >= attackCooldown){
            cooldownTimer = 0f
            attackReady = true
        }
    }

    override fun updateBody(delta: Float) {
        super.updateBody(delta)
    }

    override fun dispose() {
        super.dispose()
    }

    override fun toString(): String {
        return "Mob(name=$name, state=$entityState, health=$health, position=${this.body.position}, bounds=${this.body.bounds})"
    }

    override fun isAlive(): Boolean = super.isAlive() && health > 0f

    fun isAttackReady() = attackReady
}
