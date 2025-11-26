require('event/event')
require('core')
GlobalEvents = Events:New()

local function DbgCam()
    local camera = GameState:getCamera()
    if KeyDown(Keys.Z) then
        camera.zoom = camera.zoom + 0.01
    end
    if KeyDown(Keys.X) then
        camera.zoom = camera.zoom - 0.01
    end
    if KeyDown(Keys.LEFT) then
        camera.position.x = camera.position.x - 25
    end
    if KeyDown(Keys.RIGHT) then
        camera.position.x = camera.position.x + 25
    end
    if KeyDown(Keys.UP) then
        camera.position.y = camera.position.y + 25
    end
    if KeyDown(Keys.DOWN) then
        camera.position.y = camera.position.y - 25
    end
end


local scene = NewScene('Plains1')
SetScene(scene)

function ScriptMain()
    DbgCam()
    GlobalEvents:run()
    if MouseClicked() then
        local clickPos = GetMousePos()
        AddEntity(ArcherTower(clickPos.x, clickPos.y))
    end
    if KeyPressed(Keys.ESCAPE) then
        Gdx.app:exit()
    end
end

