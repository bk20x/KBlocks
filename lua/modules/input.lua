Gdx         = java.import('com.badlogic.gdx.Gdx')
Keys        = java.import('com.badlogic.gdx.Input').Keys

function KeyPressed(key)
    return Gdx.input:isKeyJustPressed(key)
end

function KeyDown(key)
    return Gdx.input:isKeyPressed(key)
end

function MouseClicked()
    return Gdx.input:justTouched()
end

function MouseDown()
    return Gdx.input:isTouched()
end

function GetMousePos()
    local ix, iy = Gdx.input:getX(), Gdx.input:getY()
    local unprojected = GameState:getCamera():unproject(NewVector3(ix, iy, 0))
    return NewVector2(unprojected.x, unprojected.y)
end
