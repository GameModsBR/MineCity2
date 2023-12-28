package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.id.ClamFlagId
import br.com.gamemods.minecity.api.serializer.UniqueId
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
    val defaultPermissions: Map<ClaimPermissionId, Boolean?> = emptyMap(),
    val trustLevels: List<TrustLevel> = emptyList(),
) {
    /**
     * Calculates the effective flags of this claim.
     *
     * @param playerId The player that is checking the flags
     * @return The effective flags of this claim
     */
    public fun hasPermission(playerId: UniqueId, permissionId: ClaimPermissionId): Boolean {
        return defaultPermissions[permissionId] ?: trustLevels.fold(false) { currentResult, level ->
            if (playerId !in level.players) {
                currentResult
            } else {
                level.permissions[permissionId] ?: currentResult
            }
        }
    }
}
