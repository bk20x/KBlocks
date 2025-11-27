package dev.bk20x.ktd.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import dev.bk20x.ktd.GameState
import dev.bk20x.ktd.entity.Entity
import ktx.actors.centerPosition
import ktx.actors.onClick
import ktx.actors.onEnter
import ktx.actors.setScrollFocus
import ktx.scene2d.KTable
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actors
import ktx.scene2d.listWidget
import ktx.scene2d.scrollPane
import ktx.scene2d.table
import ktx.scene2d.window
import org.codehaus.groovy.ast.tools.GeneralUtils.block
import java.awt.Color
import kotlin.apply


object UIState {

    const val DEFAULT_SKIN = "ui/c64/skin/uiskin.json"

    val stage: Stage = Stage()
    var skin = Skin(Gdx.files.internal(DEFAULT_SKIN))

    init {
        Scene2DSkin.defaultSkin = skin
    }

    fun show(){
        Gdx.input.inputProcessor = stage
        stage.actors {
            table {
                setFillParent(true)
                debug = true
                if(GameState.isInitialized()){
                    val e = GameState.getEntity(1)
                    if(e is Entity){
                        entityInspectBox(e)
                    }
                }
            }
        }
    }

    fun KTable.entityInspectBox(e: Entity?) {
        scrollPane {
            val propList = listWidget<String>(skin = skin.also {
                it.getFont("commodore-64").data.setScale(.30f)
            }) {
                onEnter{
                    setItems(e?.inspect())
                    refreshItems()
                }

                onClick {
                    setItems(e?.inspect())
                    refreshItems()
                }
            }
        }.cell(width = 128f).pack()
    }

    fun addActor(actor: Actor){
        stage.addActor(actor)
    }

    fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }

    fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    fun dispose() {
        stage.dispose()
        skin.dispose()
    }

    fun reload(){
        stage.clear()
        show()
    }
}
