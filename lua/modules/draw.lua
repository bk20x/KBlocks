Texture = java.import('com.badlogic.gdx.graphics.Texture')
---@alias Texture Texture

---@param  name string
---@return Texture
function NewTexture(name)
    return java.new(Texture, name)
end



---@param texture Texture
---@param x       number
---@param y       number
function DrawTexture(texture, x, y)
    GameState:getBatch():draw(texture, x, y)
end
