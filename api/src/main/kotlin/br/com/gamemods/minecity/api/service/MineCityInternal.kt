package br.com.gamemods.minecity.api.service

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.claim.Claim
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

/**
 * Internal MineCity states and services, should not be used by API users.
 */
@InternalMineCityApi
public object MineCityInternal {
    /**
     * Access to the MineCity API implementation, must be modified only by MineCity itself, can be accessed freely.
     */
    public lateinit var implementation: MineCity

    /**
     * Creates a message to be sent to the player when a permission is denied.
     */
    public fun permissionDeniedMessage(claim: Claim, permissionId: ClaimPermissionId): Component {
        return Component.text()
            .content("MineCity> ").color(NamedTextColor.DARK_RED)
            .append(
                Component.text().color(NamedTextColor.RED)
                    .append(Component.text("You don't have "))
                    .append(MineCity.permission[permissionId].name.color(NamedTextColor.YELLOW))
                    .append(Component.text(" permission in "))
                    .append(Component.text(claim.name, NamedTextColor.YELLOW))
            ).build()
    }
}
