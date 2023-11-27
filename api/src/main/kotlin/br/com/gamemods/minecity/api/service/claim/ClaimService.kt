package br.com.gamemods.minecity.api.service.claim

import br.com.gamemods.minecity.api.annotation.threading.SyncFriendly
import br.com.gamemods.minecity.api.claim.Claim
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockGridPositioned
import br.com.gamemods.minecity.api.math.pos.BlockGridPositionedLocation
import kotlinx.coroutines.Deferred

/**
 * Manages claim loading, creation, destruction, modifications...
 */
public interface ClaimService {
    /**
     * Gets a loaded claim for the given position.
     */
    @SyncFriendly
    public operator fun get(worldId: WorldId, pos: BlockGridPositioned): Claim?

    /**
     * Gets a loaded claim for the given position.
     */
    @SyncFriendly
    public operator fun get(pos: BlockGridPositionedLocation): Claim? = get(pos.worldId, pos)
    ///////////////////////////////
    /**
     * Gets or load a claim for the given position.
     */
    public fun load(worldId: WorldId, pos: BlockGridPositioned): Deferred<Claim?>
    /**
     * Gets or load a claim for the given position.
     */
    public fun load(pos: BlockGridPositionedLocation): Deferred<Claim?> = load(pos.worldId, pos)
    ///////////////////////////////
    /**
     * Creates a new claim.
     *
     * @return The current claim state, can be different from the one received on [claim].
     */
    public fun create(claim: Claim): Deferred<Claim>
}
