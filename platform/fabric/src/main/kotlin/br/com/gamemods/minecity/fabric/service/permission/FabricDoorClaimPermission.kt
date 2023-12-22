package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.service.permission.ClaimPermission
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.kyori.adventure.text.Component
import net.minecraft.block.DoorBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World

@InternalMineCityApi
class FabricDoorClaimPermission: ClaimPermission(
    id = ClaimPermissionId.DOORS,
    name = Component.text("Doors"),
    description = Component.text("Allows the player to open and close doors in the claim.")
) {
    override fun onRegister() {
        UseBlockCallback.EVENT.register(OnUseBlock())
    }

    private inner class OnUseBlock: UseBlockCallback {
        override fun interact(player: PlayerEntity, world: World, hand: Hand, hitResult: BlockHitResult): ActionResult {
            if (hand != Hand.MAIN_HAND || hitResult.type != HitResult.Type.BLOCK) {
                return ActionResult.PASS
            }

            val blockState = world.getBlockState(hitResult.blockPos)
            val block = blockState.block

            return if (block is DoorBlock) {
                ActionResult.FAIL
            } else {
                ActionResult.PASS
            }
        }
    }


    companion object {
        private val doors = setOf(
            "minecraft:acacia_door",
            "minecraft:birch_door",
            "minecraft:crimson_door",
            "minecraft:dark_oak_door",
            "minecraft:iron_door",
            "minecraft:jungle_door",
            "minecraft:oak_door",
            "minecraft:spruce_door",
            "minecraft:warped_door"
        )
    }
}
