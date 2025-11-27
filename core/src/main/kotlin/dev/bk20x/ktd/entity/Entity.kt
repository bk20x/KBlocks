package dev.bk20x.ktd.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import dev.bk20x.ktd.Body
import dev.bk20x.ktd.entity.mob.Mob
import dev.bk20x.ktd.towers.Tower

open class Entity {


    var id        = 0
    var name      = ""
    var alive     = true
    var health    = 0f
    var stateTime = 0f

    val body = Body()
    var target: Entity? = null

    var entityState: EntityState            = EntityState.IDLE
    val stateEvents                         = mutableMapOf<EntityState, Runnable>()
    val properties: MutableMap<String, Any> = mutableMapOf()

    open fun update(delta: Float) {
        stateTime += delta
        this.updateBody(delta)
        if(this.body.velocity.isZero) entityState = EntityState.IDLE
        if(health <= 0f) {
            entityState = EntityState.DYING
            stateEvents[entityState]?.run()
            alive = false
        }
        entityState.let { state ->
            stateEvents[state]?.run()
        }
    }

    open fun render(sb: SpriteBatch) {
    }

    open fun updateBody(delta: Float) {
        val position = body.position
        val velocity = body.velocity
        position.add(velocity.scl(delta))
        body.bounds.setPosition(position.x, position.y)
    }

    fun setPosition(x: Float, y: Float) {
        body.position.set(x, y)
        body.bounds.setPosition(x, y)
    }

    fun getBounds(): Rectangle = this.body.bounds
    fun getPosition(): Vector2 = this.body.position

    fun getX(): Float = body.position.x
    fun getY(): Float = body.position.y
    fun getWidth(): Float = body.bounds.width
    fun getHeight(): Float = body.bounds.height

    open fun dispose() {
        stateEvents.clear()
        properties.clear()
    }

    open fun isAlive(): Boolean = alive


    fun setProperty(name: String, value: Any) {
        properties[name] = value
    }

    fun setStateEvent(entityState: EntityState, action: Runnable) {
        stateEvents[entityState] = action
    }

    fun getProperty(name: String): Any? = properties[name]


    fun collided(entity: Entity) = this.body.bounds.overlaps(entity.body.bounds) || entity.body.bounds.overlaps(this.body.bounds)

    fun isMob():   Boolean = this is Mob
    fun isTower(): Boolean = this is Tower

    fun inspect(): Array<String>  {
        return Array<String>().apply {
            add("$name $${id} ")
            add("health=$health")
            add("position=${body.position}")
        }
    }
}
