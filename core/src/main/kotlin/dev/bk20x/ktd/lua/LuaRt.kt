package dev.bk20x.ktd.lua

object LuaRt {

    val lua = Interop()

    fun callFunc(func: String)     = lua.callFunc(func)
    fun loadModule(module: String) = lua.loadModule(module)


}
