package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimId
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.serializer.UniqueId
import br.com.gamemods.minecity.api.service.MineCityInternal
import kotlinx.serialization.Serializable

@Serializable
public data class Claim(
    val id: ClaimId,
    val shape: Set<ClaimShape>,
    val name: String,
    val parentId: ClaimId? = null,
    val owner: UniqueId? = null,
    val rent: ClaimRentContract? = null,
    val city: City? = null,
    val isAdmin: Boolean = false,
    val settings: ClaimSettings = ClaimSettings(),
) {
    @OptIn(InternalMineCityApi::class)
    public fun hasPermission(playerId: UniqueId, permissionId: ClaimPermissionId, silent: Boolean = false): Boolean {
        if (MineCity.players.isAdminMode(playerId)) {
            return true
        }
        if (rent != null && rent.rentedTo == playerId) {
            return true
        } else if (playerId == owner) {
            return true
        }

        val allow = settings.hasPermission(playerId, permissionId)
        if (!allow && !silent) {
            MineCity.players.sendMessage(playerId, MineCityInternal.permissionDeniedMessage(this, permissionId))
        }
        return allow
    }
}
