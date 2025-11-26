package dev.bk20x.ktd.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class EntityManager {

    var lastId: Int = 0
    val entities = mutableMapOf<Int, Entity>()

    fun update(batch: SpriteBatch, dt: Float) {
        for (id in entities.keys) {
            val entity = entities[id]!!
            entity.update(dt)
            entity.render(batch)
        }
    }

    fun addEntity(entity: Entity) {
        val id = nextId()
        entity.id = id
        entities[id] = entity
    }

    private fun nextId(): Int = lastId++

}
