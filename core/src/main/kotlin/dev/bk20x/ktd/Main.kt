package dev.bk20x.ktd

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import dev.bk20x.ktd.lua.LuaRt
import dev.bk20x.ktd.types.CardinalDirection

import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen

import ktx.async.KtxAsync


class Main : KtxGame<KtxScreen>() {
    override fun create() {
        KtxAsync.initiate()
        addScreen(FirstScreen())
        setScreen<FirstScreen>()
    }
}

class FirstScreen : KtxScreen {
    init {
        LuaRt.loadModule("main")
        val camera = OrthographicCamera()
        camera.setToOrtho(false, 640f, 360f)
        GameState.setView(camera)
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        GameState.run(delta)
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)){
            LuaRt.loadModule("main")
        }
    }

    override fun dispose() {

    }
}
