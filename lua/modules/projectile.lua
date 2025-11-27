Projectile = java.import('dev.bk20x.ktd.entity.projectile.Projectile')
---@alias Projectile Projectile

---@param  name string
---@return Projectile
function NewProjectile(name)
    return java.new(Projectile, name)
end

