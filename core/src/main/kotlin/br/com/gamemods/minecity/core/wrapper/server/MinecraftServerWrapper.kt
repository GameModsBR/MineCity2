package br.com.gamemods.minecity.core.wrapper.server

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.core.wrapper.Wrapper

/**
 * Wraps a native MinecraftServer object and gives access to its features
 *
 * @property serverIp The IP being used to host the server
 */
@InternalMineCityApi
interface MinecraftServerWrapper: Wrapper {
    val serverIp: String

    /**
     * Checks if the current thread is the main thread
     */
    fun isSync(): Boolean
}
