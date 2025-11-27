Vector2     = java.import('com.badlogic.gdx.math.Vector2')
---@alias Vector2 Vector2
Vector3     = java.import('com.badlogic.gdx.math.Vector3')
---@alias Vector3 Vector3
Rectangle   = java.import('com.badlogic.gdx.math.Rectangle')
---@alias Rectangle Rectangle

---@param  x number
---@param  y number
---@return Vector2
function NewVector2(x, y)
    return java.new(Vector2, x, y)
end

---@param  x number
---@param  y number
---@param  z number
---@return Vector3
function NewVector3(x, y, z)
    return java.new(Vector3, x, y, z)
end

---@param  x number
---@param  y number
---@param  width number
---@param  height number
---@return Rectangle
function NewRectangle(x, y, width, height)
    return java.new(Rectangle, x, y, width, height)
end

---@param src Vector2
---@param dst Vector2
---@return    Vector2
function GetDirectionTo(src, dst)
    return dst:cpy():sub(src):nor()
end

---@return number
function GetDeltaTime()
    return Gdx.graphics:getDeltaTime()
end

CardinalDirection = java.import('dev.bk20x.ktd.types.CardinalDirection')
---@alias CardinalDirection CardinalDirection


---@param   direction Vector2
---@return  CardinalDirection
function CardinalFromVector(direction)
    return CardinalDirection.Companion:fromVector(direction)
end


function Put(obj)
    local System = java.import('java.lang.System')
    System.out:println(obj)
end
