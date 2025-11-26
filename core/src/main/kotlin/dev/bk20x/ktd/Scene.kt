package dev.bk20x.ktd

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import dev.bk20x.ktd.entity.Entity
import dev.bk20x.ktd.entity.EntityManager
import ktx.tiled.height
import ktx.tiled.tileHeight
import ktx.tiled.tileWidth
import ktx.tiled.width


class Scene {

    var name: String
    var map: TiledMap
    var camera: OrthographicCamera = OrthographicCamera()
    var mapRenderer: OrthogonalTiledMapRenderer
    val entityManager: EntityManager = EntityManager()

    constructor(name: String){
        this.name   = name
        map         = loadMap(name)
        mapRenderer = OrthogonalTiledMapRenderer(map)
        mapRenderer.setView(camera)
    }

    fun render(batch: SpriteBatch, delta: Float) {
        camera.update()
        mapRenderer.render()
        mapRenderer.setView(camera)
        entityManager.update(batch, delta)
    }

    fun setView(camera: OrthographicCamera) {
        this.camera = camera
    }

    fun addEntity(entity: Entity) {
        entityManager.addEntity(entity)
    }

    fun getXTiles(): Int = map.width

    fun getYTiles(): Int = map.height

    fun getMapWidth():  Int = map.width * map.tileWidth

    fun getMapHeight(): Int = map.height * map.tileHeight

    fun getCenter(): Vector2 {
        val centerX = getMapWidth() / 2f
        val centerY = getMapHeight() / 2f
        return Vector2(centerX, centerX)
    }

    companion object {
        private fun loadMap(name: String): TiledMap {
            return TmxMapLoader().load("data/scenes/$name/$name.tmx")
        }

        fun NewScene(name: String): Scene {
            return Scene(name)
        }
    }

    fun dispose() {
        map.dispose()
        mapRenderer.dispose()
    }

    override fun toString(): String {
        return "a Scene: $name"
    }

}
