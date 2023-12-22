package br.com.gamemods.minecity.api.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import net.kyori.adventure.text.Component

/**
 * Represents a permission that can be granted to a player in a claim.
 *
 * @property id The unique identifier for the permission.
 * @property name The name of the permission.
 * @property description The description of the permission.
 */
public abstract class ClaimPermission(
    public val id: ClaimPermissionId,
    public val name: Component,
    public val description: Component,
) {
    protected abstract fun onRegister()

    public companion object {
        @InternalMineCityApi
        public fun onRegister(claimPermission: ClaimPermission) {
            claimPermission.onRegister()
        }
    }
}
