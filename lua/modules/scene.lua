Scene = java.import('dev.bk20x.ktd.Scene')
---@alias Scene Scene

---@param  name string
---@return Scene
function NewScene(name)
    return Scene.Companion:NewScene(name)
end
