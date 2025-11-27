Tower = java.import('dev.bk20x.ktd.towers.Tower')

function NewTower(name)
    return java.new(Tower, name)
end

function ArcherTower(x, y)
    local result = NewTower('ArcherTower')
    local body   = result:getBody()
    body.position.x = x
    body.position.y = y
    body.bounds:setSize(65, 129)
    return result
end
