package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.ClaimId
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.shape.CombinedShape
import kotlinx.serialization.Serializable

/**
 * Represents the shape of a claim in a given world.
 *
 * @property shape The world where this shape resides.
 * @property worldId The shape of this claim in this world.
 * @property subClaimIds IDs of child claims that also has a shape in this world.
 */
@Serializable
public data class ClaimShape(
    val worldId: WorldId,
    val shape: CombinedShape,
    val subClaimIds: Set<ClaimId>,
)
