package dev.bk20x.ktd.entity

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.concurrent.ConcurrentHashMap

class EntityManager {

    var lastId: Int = 0
    val entities = ConcurrentHashMap<Int, Entity>()

    fun update(batch: SpriteBatch, dt: Float) {
        for (id in entities.keys) {
            val entity = entities[id]!!
            if(!entity.alive)  {
                entities.remove(id)
                continue
            }
            entity.update(dt)
            entity.render(batch)
        }
    }

    fun addEntity(entity: Entity) {
        val id = nextId()
        entity.id = id
        entities[id] = entity
        println("#$id: $entity")
    }

    fun getEntity(id: Int): Entity? {
        return entities[id]
    }

    private fun nextId(): Int = lastId++

}
