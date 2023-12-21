package br.com.gamemods.minecity.api.server

import br.com.gamemods.minecity.api.MineCityPlatform
import kotlinx.coroutines.CoroutineScope

/**
 * Server side instance that handles all server features
 *
 * @property platform The platform where MineCity is running
 * @property serverIp The IP being used to host the server
 */
public interface MineCityServer: CoroutineScope {
    public val platform: MineCityPlatform
    public val serverIp: String

    /**
     * Checks if the current thread is the main thread
     */
    public fun isSync(): Boolean
}
