package dev.bk20x.ktd.lua

import party.iroiro.luajava.lua54.Lua54
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Paths

class Interop {

    val lua = Lua54()


    init {
        lua.openLibraries()
        lua.setExternalLoader { path, _ ->
            try {
                val cwd = Paths.get("").toAbsolutePath()
                val scriptPath = cwd.resolve("lua/$path.lua")

                if (Files.exists(scriptPath) && Files.isRegularFile(scriptPath)) {
                    val bytes = Files.readAllBytes(scriptPath)
                    val buffer = ByteBuffer.allocateDirect(bytes.size)
                    buffer.put(bytes)
                    buffer.flip()
                    return@setExternalLoader buffer
                } else {
                    return@setExternalLoader null // File not found
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return@setExternalLoader null
            }
        }
    }

    fun loadModule(moduleName: String) {
        lua.loadExternal(moduleName)
        lua.pCall(0, 0)
    }

    fun callFunc(funcName: String) {
        lua.getGlobal(funcName)
        lua.pCall(0, 0)
    }
}
