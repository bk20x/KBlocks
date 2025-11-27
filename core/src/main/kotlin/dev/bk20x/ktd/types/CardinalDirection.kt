package dev.bk20x.ktd.types

import com.badlogic.gdx.math.Vector2
import kotlin.math.abs

enum class CardinalDirection {

    North,
    South,
    East,
    West;


    companion object {
        fun fromVector(vector: Vector2): CardinalDirection {
            val x = vector.x
            val y = vector.y
            return if (abs(x) > abs(y)) {
                if (x > 0) East else West
            } else {
                if (y > 0) North else South
            }
        }

        fun fromString(direction: String): CardinalDirection {
            return when (direction.lowercase()) {
                "north" -> North
                "south" -> South
                "east" -> East
                "west" -> West
                else -> South
            }
        }

        fun toString(direction: CardinalDirection): String {
            return when (direction) {
                North -> "north"
                South -> "south"
                East  -> "east"
                West  -> "west"
            }
        }
    }

}
