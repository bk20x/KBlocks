GameState = java.import('dev.bk20x.ktd.GameState').INSTANCE

function AddEntity(entity)
    local activeScene = GameState:getActiveScene()
    activeScene:addEntity(entity)
end

function SetScene(scene)
    GameState:setScene(scene)
end
