package dev.bk20x.ktd

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

data class Body(@JvmField var position:    Vector2   = Vector2(),
                @JvmField var velocity:    Vector2   = Vector2(),
                @JvmField var direction:   Vector2   = Vector2(),
                @JvmField var bounds:      Rectangle = Rectangle())


