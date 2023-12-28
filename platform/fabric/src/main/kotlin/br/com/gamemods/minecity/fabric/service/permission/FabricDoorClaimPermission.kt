package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.kyori.adventure.text.Component
import net.minecraft.block.DoorBlock
import net.minecraft.block.TrapdoorBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World

/**
 * Represents a permission that can be granted to a player in a claim.
 *
 * @property id The unique identifier for the permission.
 * @property name The name of the permission.
 * @property description The description of the permission.
 */
@InternalMineCityApi
class FabricDoorClaimPermission: FabricClaimPermission(
    id = ClaimPermissionId.DOORS,
    name = Component.text("Doors"),
    description = Component.text("Allows the player to open and close doors in the claim.")
) {
    override fun onRegister() {
        UseBlockCallback.EVENT.register(OnUseBlock())
    }

    private inner class OnUseBlock: UseBlockCallback {
        override fun interact(player: PlayerEntity, world: World, hand: Hand, hitResult: BlockHitResult): ActionResult {
            if (world.isClient) {
                return ActionResult.PASS
            }

            if (hand != Hand.MAIN_HAND || hitResult.type != HitResult.Type.BLOCK) {
                return ActionResult.PASS
            }

            val clickPos = hitResult.blockPos
            val blockState = world.getBlockState(clickPos)
            val block = blockState.block

            if (block !is DoorBlock && block !is TrapdoorBlock) {
                return ActionResult.PASS
            }

            return if (checkPermission(world, player, clickPos, ClaimPermissionId.DOORS)) {
                ActionResult.PASS
            } else {
                ActionResult.FAIL
            }
        }
    }
}
