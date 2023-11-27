package br.com.gamemods.minecity.api.math.pos

import br.com.gamemods.minecity.api.id.WorldId

/**
 * An object with a location aligned to [worldId].
 *
 * @property worldId The ID of the world where this location exists.
 */
public sealed interface Location {
    public val worldId: WorldId
}
