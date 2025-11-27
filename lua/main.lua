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

local tow = ArcherTower(250, 250)
AddEntity(tow)

require('ui')
function ScriptMain()
    DbgCam()
    GlobalEvents:run()

    if MouseClicked() then
        local pos = GetMousePos()
        AddEntity( Slime('green', pos.x, pos.y) )
    end
    for _, v in pairs(GetEntities()) do
        if v:isMob() then
            AttackTarget(v, tow)
        end
    end
    if KeyPressed(Keys.G) then
        ReinitUi()
    end

    if KeyPressed(Keys.ESCAPE) then
        Gdx.app:exit()
    end
end

