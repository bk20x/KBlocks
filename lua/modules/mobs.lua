Entity = java.import('dev.bk20x.ktd.entity.Entity')
---@alias Entity Entity

EntityState = java.import('dev.bk20x.ktd.entity.EntityState')
---@alias EntityState EntityState
--[[
    EntityState(
       IDLE,
       SEEKING,
       ATTACKING,
       DYING
    )
]]--

Mob = java.import('dev.bk20x.ktd.entity.mob.Mob')
---@alias Mob Mob

---@param  name string
---@return Mob
function NewMob(name)
    return java.new(Mob, name)
end

---@param mob    Mob
---@param target Vector2
function MoveToPoint(mob, target)
    if mob:getBounds():contains(target) then
        return
    end
    mob:setEntityState(EntityState.SEEKING)
    local body      = mob:getBody()
    local velocity  = body.velocity
    body.direction  = GetDirectionTo(mob:getPosition(), target)
    velocity:set(body.direction:cpy():nor():scl(mob:getSpeed()))
end

---@param mob Mob
---@param target Entity
function AttackTarget(mob, target)
    if not mob:isAttackReady() or not target:isAlive() then
        return
    end
    local range         = mob:getRange()
    local target_pos    = target:getPosition()
    local target_bounds = target:getBounds()
    if not mob:getBounds():overlaps(NewRectangle(target_bounds.x, target_bounds.y, target_bounds.width + range, target_bounds.height + range)) then
        MoveToPoint(mob, target_pos)
    else
        mob:setEntityState(EntityState.ATTACKING)
        target:setHealth( target:getHealth() - mob:getDamage() )
        mob:setAttackReady(false)
    end
end

