package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.ClaimId
import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.serialization.Serializable

@Serializable
public data class Claim(
    val id: ClaimId = ClaimId(),
    val shape: Set<ClaimShape>,
    val parentId: ClaimId? = null,
    val owner: UniqueId? = null,
    val rent: ClaimRentContract? = null,
    val city: City? = null,
    val isAdmin: Boolean = false,
    val settings: ClaimSettings = ClaimSettings(),
)
