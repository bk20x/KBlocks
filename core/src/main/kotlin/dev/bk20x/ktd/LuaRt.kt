package dev.bk20x.ktd

import dev.bk20x.ktd.lua.Interop

object LuaRt {

    val lua = Interop()


    fun loadModule(module: String) = lua.loadModule(module)
    fun callFunc(func: String) = lua.callFunc(func)

}
