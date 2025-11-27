Repeater = {}
Repeater.__index = Repeater
---@alias Repeater Repeater

---@param repetitions number
---@param rep_speed   number
---@param event       function
---@return Repeater
function Repeater:New(repetitions, rep_speed, event)
    local t = setmetatable({}, self)
    t.event          = event
    t.finished       = false
    t.cooldown       = 1.0 / rep_speed
    t.completed      = 0
    t.repetitions    = repetitions
    t.cooldown_timer = 0
    return t
end



function Repeater:update(delta)
    self.cooldown_timer = self.cooldown_timer + delta
    if self.cooldown_timer >= self.cooldown and not self.finished then
        self.cooldown_timer = 0
        self.event()
        self.completed = self.completed + 1
        if self.repetitions ~= 'inf' then
            if self.completed == self.repetitions then
                self.finished = true
            end
        end
    end
end


