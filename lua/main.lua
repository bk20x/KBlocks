require('core')
require('event/event')
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

SetScene(NewScene('Plains1'))

function Slime(color, x, y)
    local mob = NewMob(color .. ' slime')
    mob:setPosition(x, y)
    mob:getBounds():setSize(32, 32)
    return mob
end

local point = NewVector2(250, 250)

function ScriptMain()
    DbgCam()
    GlobalEvents:run()
    if KeyPressed(Keys.G) then
        local clickPos = GetMousePos()
        local mob = Slime('blue', clickPos.x, clickPos.y)
        AddEntity(mob)
    end

    if MouseDown() then
        point:set(GetMousePos().x, GetMousePos().y)
    end

    for _, entity in pairs(GetEntities()) do
        if entity:isMob() then
            MoveToPoint(entity, point)
        end
    end

    if KeyPressed(Keys.ESCAPE) then
        Gdx.app:exit()
    end
end

