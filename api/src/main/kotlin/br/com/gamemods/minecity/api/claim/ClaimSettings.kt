package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.ClamFlagId
import br.com.gamemods.minecity.api.id.ClamPermissionId
import kotlinx.serialization.Serializable

/**
 * Flags, permission and trust levels of a claim.
 *
 * @property defaultFlags The initial flags, used when calculating the effective flags
 * @property defaultPermissions The initial permissions, used when calculating the effective permissions
 * @property trustLevels The trust levels details
 */
@Serializable
public data class ClaimSettings(
    val defaultFlags: Map<ClamFlagId, ClaimFlagValue> = emptyMap(),
    val defaultPermissions: Map<ClamPermissionId, Boolean?> = emptyMap(),
    val trustLevels: List<TrustLevel> = emptyList(),
)
