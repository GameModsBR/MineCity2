package br.com.gamemods.minecity.api.service.namedplayer

import br.com.gamemods.minecity.api.annotation.threading.ASyncFriendly
import br.com.gamemods.minecity.api.annotation.threading.SyncFriendly
import br.com.gamemods.minecity.api.id.NamedPlayer
import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.coroutines.Deferred
import net.kyori.adventure.text.Component

/**
 * Manages the [NamedPlayer] objects.
 */
public interface NamedPlayerService {
    /**
     * Gets [NamedPlayer] for the given player [playerId], attempts to use a cached value before starting a query.
     */
    public operator fun get(playerId: UniqueId): Deferred<NamedPlayer?>

    /**
     * Gets [NamedPlayer] for the given player [name], attempts to use a cached value before starting a query.
     */
    public operator fun get(name: String): Deferred<NamedPlayer?>

    /**
     * Checks if the given player [playerId] has ready-to-use cached value.
     */
    @SyncFriendly
    public operator fun contains(playerId: UniqueId): Boolean

    /**
     * Checks if the given player [name] has ready-to-use cached value.
     */
    @SyncFriendly
    public operator fun contains(name: String): Boolean

    /**
     * Sends a message to the given player [playerId] if the player is online.
     */
    @SyncFriendly
    @ASyncFriendly
    public fun sendMessage(playerId: UniqueId, message: Component)

    /**
     * Checks if the given player [playerId] is in admin mode.
     */
    public fun isAdminMode(playerId: UniqueId): Boolean
}
