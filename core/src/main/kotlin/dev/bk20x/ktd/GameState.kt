package dev.bk20x.ktd

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import dev.bk20x.ktd.entity.Entity
import dev.bk20x.ktd.lua.LuaRt
import dev.bk20x.ktd.ui.UIState
import ktx.graphics.use
import java.nio.file.Files
import java.nio.file.Paths


object GameState {

    var cameraLocked = true
    var camera: OrthographicCamera = OrthographicCamera()
    lateinit var activeScene: Scene
    var isSceneActive = false
    val batch = SpriteBatch()

    init {
        camera.setToOrtho(false, 640f, 360f)
    }


    fun run(delta: Float) {
        if(!::activeScene.isInitialized) return
        clampCamToSceneBounds()
        batch.use { sb ->
            activeScene.setView(camera)
            activeScene.render(sb, delta)
            LuaRt.callFunc("ScriptMain")
        }
        UIState.render(delta)
    }

    fun setView(camera: OrthographicCamera) {
        this.camera = camera
    }

    fun setScene(scene: Scene) {
        isSceneActive = false
        if(this::activeScene.isInitialized) this.activeScene.dispose()
        this.activeScene = scene
        isSceneActive = true


        val name = scene.name
        val scriptPath = "scenescripts/$name/$name"
        if (Files.exists(Paths.get("").resolve("lua/$scriptPath.lua"))) {
            LuaRt.loadModule(scriptPath)
        }
        else {
            System.err.println("Script not found for scene: $name")
        }
    }

    fun getEntity(id: Int): Entity? {
        return if(this.activeScene.entityManager.entities.containsKey(id))
            this.activeScene.entityManager.getEntity(id)!!
         else
            null
    }

    private const val MIN_ZOOM = 0.5f
    private fun clampCamToSceneBounds() {
        val cam = this.camera
        if (!::activeScene.isInitialized || !cameraLocked) {
            cam.update(true)
            return
        }

        val scene = activeScene
        val worldWidth = scene.getMapWidth()
        val worldHeight = scene.getMapHeight()

        val maxZoomX = worldWidth / cam.viewportWidth
        val maxZoomY = worldHeight / cam.viewportHeight
        var maxZoom  = maxZoomX.coerceAtMost(maxZoomY)
        if (maxZoom.isNaN() || maxZoom <= 0f) {
            maxZoom = 1f
        }

        if (cam.zoom < MIN_ZOOM) {
            cam.zoom = MIN_ZOOM
        }

        if (cam.zoom > maxZoom) {
            cam.zoom = maxZoom
        }
        val halfViewW = (cam.viewportWidth * cam.zoom) * 0.5f
        val halfViewH = (cam.viewportHeight * cam.zoom) * 0.5f

        var clampedX = worldWidth * 0.5f
        var clampedY = worldHeight * 0.5f
        if (worldWidth >= cam.viewportWidth * cam.zoom) {
            clampedX = MathUtils.clamp(cam.position.x, halfViewW, worldWidth - halfViewW)
        }

        if (worldHeight >= cam.viewportHeight * cam.zoom) {
            clampedY = MathUtils.clamp(cam.position.y, halfViewH, worldHeight - halfViewH)
        }

        if (cam.position.x != clampedX || cam.position.y != clampedY) {
            cam.position.set(clampedX, clampedY, 0f)
            cam.update(true)
        } else {
            cam.update(true)
        }
    }

    fun getEntities(): MutableMap<Int, Entity> = activeScene.entityManager.entities

    fun isInitialized(): Boolean { return this::activeScene.isInitialized }
}
