Gdx         = java.import('com.badlogic.gdx.Gdx')
Keys        = java.import('com.badlogic.gdx.Input').Keys
Vector2     = java.import('com.badlogic.gdx.math.Vector2')
Vector3     = java.import('com.badlogic.gdx.math.Vector3')
Rectangle   = java.import('com.badlogic.gdx.math.Rectangle')

function NewRectangle(x, y, width, height)
    return java.new(Rectangle, x, y, width, height)
end

function NewVector2(x, y)
    return java.new(Vector2, x, y)
end

function NewVector3(x, y, z)
    return java.new(Vector3, x, y, z)
end

function PutLn(obj)
    local System = java.import('java.lang.System')
    System.out:println(obj)
end

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


function GetDirectionTo(src, dst)
    return dst:cpy():sub(src):nor()
end
