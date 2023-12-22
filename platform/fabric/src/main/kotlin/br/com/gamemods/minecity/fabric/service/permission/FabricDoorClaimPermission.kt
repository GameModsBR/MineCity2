package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.service.permission.ClaimPermission
import net.kyori.adventure.text.Component

@InternalMineCityApi
class FabricDoorClaimPermission: ClaimPermission(
    id = ClaimPermissionId.DOORS,
    name = Component.text("Doors"),
    description = Component.text("Allows the player to open and close doors in the claim.")
)
