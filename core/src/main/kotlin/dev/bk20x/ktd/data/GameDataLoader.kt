package dev.bk20x.ktd.data

import com.badlogic.gdx.graphics.Texture
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.bk20x.ktd.Globals
import dev.bk20x.ktd.entity.mob.Mob
import dev.bk20x.ktd.entity.projectile.Projectile
import dev.bk20x.ktd.towers.Tower
import java.nio.file.Files
import java.nio.file.Paths

object GameDataLoader {
    // TODO: add table for adding extra files to the search path for entities instead of only in the data directory

    val gson = Gson()
    inline fun <reified T> objFromFile(path: String): T? {
        try {
            val reader = Files.newBufferedReader(Paths.get(path))
            val json = gson.fromJson(reader, T::class.java)
            reader.close()
            return json
        }
        catch (e: Exception) {
            System.err.println("Error parsing file $path; ${e.message}")
        }
        return null
    }

    fun arrayFromFile(path: String): JsonArray? = objFromFile<JsonArray>(path)


    fun setMobValues(mob: Mob){
        val mobs = objFromFile<JsonObject>("data/mobs.json") ?: let {
            System.err.println("No data for Mob: ${mob.name}")
            return
        }
        if (!mobs.has(mob.name)) return
        val mobData = mobs[mob.name].asJsonObject
        mob.health  = mobData["health"].asFloat
        mob.speed   = mobData["speed"].asFloat
        mob.damage  = mobData["damage"].asFloat

        val texturePath   = "${Globals.MOB_ASSET_PATH}/${mob.name}.png"
        val texture       = Texture(texturePath)
        val mobAnimations = objFromFile<JsonObject>(Globals.MOB_ANIM_PATH)!!
        mob.setAnimations(mobAnimations, mob.name, texture)
    }

    fun setProjectileValues(projectile: Projectile){
        val projectiles = objFromFile<JsonObject>("data/projectiles.json") ?: let {
            System.err.println("No data for Projectile: ${projectile.name}")
            return
        }
        if (!projectiles.has(projectile.name)) return
        val projectileData              = projectiles[projectile.name].asJsonObject
        projectile.speed                = projectileData["speed"].asFloat
        projectile.body.bounds.width    = projectileData["width"].asFloat
        projectile.body.bounds.height   = projectileData["height"].asFloat

        val texturePath          = "${Globals.PROJECTILE_ASSET_PATH}/${projectile.name}.png"
        val texture              = Texture(texturePath)
        val projectileAnimations = objFromFile<JsonObject>(Globals.PROJECTILE_ANIM_PATH)!!
        projectile.setAnimations(projectileAnimations, projectile.name, texture)
    }

    fun setTowerValues(tower: Tower){
        val towers = objFromFile<JsonObject>("data/towers.json") ?: let {
            System.err.println("No data for Tower: ${tower.name}")
            return
        }
        if (!towers.has(tower.name)) return
        val towerData = towers[tower.name].asJsonObject
        tower.health  = towerData["health"].asFloat
        tower.cost    = towerData["cost"].asInt

        val texturePath         = "${Globals.TOWER_ASSET_PATH}/${tower.name}.png"
        val texture             = Texture(texturePath)
        val towerAnimations     = objFromFile<JsonObject>(Globals.TOWER_ANIM_PATH)!!
        tower.setAnimations(towerAnimations, tower.name, texture)
    }
}
