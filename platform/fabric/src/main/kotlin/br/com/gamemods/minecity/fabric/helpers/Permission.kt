package br.com.gamemods.minecity.fabric.helpers

import br.com.gamemods.minecity.api.claim.Claim
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.serializer.UniqueId
import net.minecraft.util.ActionResult

/**
 * Checks if a [UniqueId] has a given [ClaimPermissionId] inside a [Claim].
 *
 * *Note*: This is platform-dependent code since each loader treats [ActionResult]s differently.
 * This code is **expected to work with Fabric API**'s event system.
 * @param claim The claim being checked. Usually the one the player is located at.
 * @param permissionId The [ClaimPermissionId] of the permission. E.g. [ClaimPermissionId.BUILD].
 * @return An [ActionResult.PASS] if the player **has** the permission. An [ActionResult.FAIL] if the player **doesn't** have the permission.
 * @see [ClaimPermissionId]
 * @author alikindsys
 */
fun UniqueId.hasPermissionIn(claim: Claim, permissionId: ClaimPermissionId): ActionResult {
    return if (claim.hasPermission(this, permissionId)) {
        ActionResult.PASS
    } else {
        ActionResult.FAIL
    }
}
