package br.com.gamemods.minecity.core.service.claim

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.claim.Claim
import br.com.gamemods.minecity.api.id.ClaimId
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockGridPositioned
import br.com.gamemods.minecity.api.service.claim.ClaimService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

@InternalMineCityApi
class CoreClaimService: ClaimService {
    @Suppress("CanBeVal", "UnnecessaryVariable")
    override fun get(worldId: WorldId, pos: BlockGridPositioned): Claim? {
        //TODO Implement
        var result: Claim? = Claim(
            id = ClaimId(),
            shape = emptySet(),
            name = "TestClaim",
        )
        //result = null
        return result
    }

    override fun load(worldId: WorldId, pos: BlockGridPositioned): Deferred<Claim?> {
        //TODO Implement
        return CompletableDeferred(null)
    }

    override fun create(claim: Claim): Deferred<Claim> {
        //TODO Implement
        return CompletableDeferred(claim)
    }
}
