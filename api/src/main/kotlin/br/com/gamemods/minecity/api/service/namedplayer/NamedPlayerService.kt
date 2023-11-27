package br.com.gamemods.minecity.api.service.namedplayer

import br.com.gamemods.minecity.api.annotation.threading.SyncFriendly
import br.com.gamemods.minecity.api.id.NamedPlayer
import kotlinx.coroutines.Deferred
import java.util.*

/**
 * Manages the [NamedPlayer] objects.
 */
public interface NamedPlayerService {
    /**
     * Gets [NamedPlayer] for the given player [uuid], attempts to use a cached value before starting a query.
     */
    public operator fun get(uuid: UUID): Deferred<NamedPlayer?>

    /**
     * Gets [NamedPlayer] for the given player [name], attempts to use a cached value before starting a query.
     */
    public operator fun get(name: String): Deferred<NamedPlayer?>

    /**
     * Checks if the given player [uuid] has ready-to-use cached value.
     */
    @SyncFriendly
    public operator fun contains(uuid: UUID): Boolean

    /**
     * Checks if the given player [name] has ready-to-use cached value.
     */
    @SyncFriendly
    public operator fun contains(name: String): Boolean
}
