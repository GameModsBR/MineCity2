package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.id.ClamFlagId
import br.com.gamemods.minecity.api.id.TrustLevelId
import br.com.gamemods.minecity.api.serializer.MiniComponent
import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.serialization.Serializable

/**
 * A snapshot of the state of a trust level.
 *
 * @property id The ID of the trust level
 * @property displayName The name of this trust level
 * @property flags Claim flags that this trust level sets or unsets.
 * @property permissions Claim permissions that this trust level sets or unsets.
 */
@Serializable
public data class TrustLevel(
    val id: TrustLevelId,
    val displayName: MiniComponent,
    val players: Set<UniqueId> = emptySet(),
    val flags: Map<ClamFlagId, ClaimFlagValue> = emptyMap(),
    val permissions: Map<ClaimPermissionId, Boolean?> = emptyMap()
)
