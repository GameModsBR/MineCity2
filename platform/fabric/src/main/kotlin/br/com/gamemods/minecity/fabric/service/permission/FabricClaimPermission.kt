package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.service.permission.ClaimPermission
import br.com.gamemods.minecity.fabric.service.claim.FabricClaimService.Companion.get
import net.kyori.adventure.text.Component
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Represents a permission that can be granted to a player in a claim.
 *
 * @property id The unique identifier for the permission.
 * @property name The name of the permission.
 * @property description The description of the permission.
 */
@InternalMineCityApi
abstract class FabricClaimPermission(
    id: ClaimPermissionId,
    name: Component,
    description: Component
) : ClaimPermission(id, name, description) {

    /**
     * Checks if the player has the permission in the claim.
     *
     * @param world The world where the permission is being checked.
     * @param player The player that is being checked.
     * @param pos The position where the permission is being checked.
     * @param permissionId The permission that is being checked.
     * @return `true` if the player has the permission, `false` otherwise.
     */
    protected fun checkPermission(world: World, player: PlayerEntity, pos: BlockPos, permissionId: ClaimPermissionId): Boolean {
        if (world.isClient) {
            return true
        }

        val claim = MineCity.claims[world, pos] ?: return true
        return claim.hasPermission(player.identity().uuid(), permissionId)
    }
}
