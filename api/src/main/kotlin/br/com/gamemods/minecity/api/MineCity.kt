package br.com.gamemods.minecity.api

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.service.namedplayer.NamedPlayerService

private lateinit var currentInstance: MineCity

/**
 * Interface that allows other mods to interact with MineCity.
 */
public interface MineCity {
    /**
     * The current platform implementation
     */
    public val platform: MineCityPlatform

    /**
     * Allows to get the player name and UUIDs with easy
     */
    public val players: NamedPlayerService

    /**
     * This companion object allows MineCity interface to be used directly in kotlin delegating all API calls to
     * the instance that is set at [instance].
     *
     * @property instance Access to the MineCity API implementation, must be modified only by MineCity itself, can be accessed freely.
     */
    public companion object: MineCity by currentInstance {
        public var instance: MineCity
            get() = currentInstance
            @InternalMineCityApi
            set(value) {
                currentInstance = value
            }
    }
}
