package dev.bk20x.ktd

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2


data class Body(@JvmField val position: Vector2 = Vector2.Zero.cpy(),
                @JvmField val direction: Vector2 = Vector2.Zero.cpy(),
                val bounds: Rectangle = Rectangle())


