
Events = {}
Events.__index = Events

function Events:New()
    local t = setmetatable({}, self)
    t.registeredEvents = {}
    return t
end


function Events:registerEvent(event)
    local coro = coroutine.create(event)
    table.insert(self.registeredEvents, coro)
end


function Events:run()
    if #self.registeredEvents == 0 then return end
    local i = 1
    while i <= #self.registeredEvents do
        local event = self.registeredEvents[i]
        local success, _ = coroutine.resume(event)
        if not success or coroutine.status(event) == "dead" then
            table.remove(self.registeredEvents, i)
        else
            i = i + 1
        end
    end
end

