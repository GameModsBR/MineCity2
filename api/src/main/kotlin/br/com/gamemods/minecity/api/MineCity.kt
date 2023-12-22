package br.com.gamemods.minecity.api

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.service.MineCityInternal
import br.com.gamemods.minecity.api.service.namedplayer.NamedPlayerService
import br.com.gamemods.minecity.api.service.permission.PermissionService


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
     * Allows to get the claim permissions with easy
     */
    public val permission: PermissionService

    /**
     * This companion object allows MineCity interface to be used directly in kotlin delegating all API calls to
     * the instance that is set at [MineCityInternal.implementation].
     */
    @OptIn(InternalMineCityApi::class)
    public companion object: MineCity by MineCityInternal.implementation
}
