package br.com.gamemods.minecity.api

import br.com.gamemods.minecity.api.client.MineCityClient
import br.com.gamemods.minecity.api.server.MineCityServer

/**
 * Platform specific implementations needed to run MineCity
 */
public interface MineCityPlatform {
    /**
     * The platform MineCity API implementation.
     */
    public val core: MineCity

    /**
     * `null` if this is not running on a client instance or the client was not laded.
     */
    public val client: MineCityClient?

    /**
     * `null` if this is running on a client that is connected to a server and not hosting it.
     */
    public val server: MineCityServer?
}
