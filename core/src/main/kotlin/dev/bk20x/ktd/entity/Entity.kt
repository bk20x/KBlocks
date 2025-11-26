package dev.bk20x.ktd.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import dev.bk20x.ktd.Body

open class Entity {


    var id: Int = 0
    var name: String = ""
    var alive: Boolean = true
    var stateTime: Float = 0f

    val body = Body()

    open fun update(delta: Float) {
        stateTime += delta
    }

    open fun render(sb: SpriteBatch) {
    }


    fun setPosition(x: Float, y: Float) {
        body.position.set(x, y)
        body.bounds.setPosition(x, y)
    }

    fun getX(): Float = body.position.x
    fun getY(): Float = body.position.y
    fun getWidth(): Float = body.bounds.width
    fun getHeight(): Float = body.bounds.height

    open fun dispose() {

    }

    open fun isAlive(): Boolean = alive
}
