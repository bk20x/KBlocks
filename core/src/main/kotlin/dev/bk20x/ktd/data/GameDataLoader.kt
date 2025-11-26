package dev.bk20x.ktd.data

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dev.bk20x.ktd.entity.mob.Mob
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
        val mobs = objFromFile<JsonObject>("data/mobs.json") ?: return
        if (!mobs.has(mob.name)) return
        val mobData = mobs[mob.name].asJsonObject
        mob.health  = mobData["health"].asFloat
        mob.speed   = mobData["speed"].asFloat
        mob.damage  = mobData["damage"].asFloat
    }

    fun setTowerValues(tower: Tower){
        val towers = objFromFile<JsonObject>("data/towers.json") ?: return
        if (!towers.has(tower.name)) return
        val towerData = towers[tower.name].asJsonObject
        tower.health  = towerData["health"].asFloat
        tower.cost    = towerData["cost"].asInt
    }
}
