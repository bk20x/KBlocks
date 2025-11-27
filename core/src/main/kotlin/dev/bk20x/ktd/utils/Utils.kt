package dev.bk20x.ktd.utils

import com.badlogic.gdx.math.Vector2

object Utils {

    fun Vector2.getDirectionTo(target: Vector2): Vector2 {
        return target.cpy().sub(this).nor()
    }
}
