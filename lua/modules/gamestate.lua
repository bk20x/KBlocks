GameState = java.import('dev.bk20x.ktd.GameState').INSTANCE

---@param entity Entity
function AddEntity(entity)
    local activeScene = GameState:getActiveScene()
    activeScene:addEntity(entity)
end

---@param scene Scene
function SetScene(scene)
    GameState:setScene(scene)
end

---@return table<number, Entity>
function GetEntities()
    return java.luaify(GameState:getEntities())
end

---@param  id number
---@return Entity
function GetEntity(id)
    return GameState:getEntity(id)
end
