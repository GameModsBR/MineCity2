package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.service.permission.ClaimPermission
import br.com.gamemods.minecity.core.helpers.isInstanceOfNone
import br.com.gamemods.minecity.fabric.helpers.blockStateBy
import br.com.gamemods.minecity.fabric.helpers.hasPermissionIn
import br.com.gamemods.minecity.fabric.service.claim.FabricClaimService.Companion.get
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.kyori.adventure.text.Component
import net.minecraft.block.ButtonBlock
import net.minecraft.block.DaylightDetectorBlock
import net.minecraft.block.LeverBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.world.World

@InternalMineCityApi
class FabricButtonClaimPermission: ClaimPermission(
    id = ClaimPermissionId.BUTTONS,
    name = Component.text("Buttons"),
    description = Component.text("Allows the player to trigger buttons, daylight sensors, switches and more.")
) {
    override fun onRegister() {
        UseBlockCallback.EVENT.register(OnUseBlock())
    }

    private inner class OnUseBlock() : UseBlockCallback {
        override fun interact( player: PlayerEntity, world: World, hand: Hand, hitResult: BlockHitResult): ActionResult {
            if (world.isClient) {
                return ActionResult.PASS
            }

            if (hitResult.type != HitResult.Type.BLOCK) {
                return ActionResult.PASS
            }

            val hitPos = hitResult.blockPos
            val block = hitResult.blockStateBy(world).block

            if (block.isInstanceOfNone(ButtonBlock::class, LeverBlock::class, DaylightDetectorBlock::class)) {
                return ActionResult.PASS
            }

            val claim = MineCity.claims[world, hitPos] ?: return ActionResult.PASS

            return player.uuid.hasPermissionIn(claim, permissionId = ClaimPermissionId.BUTTONS)
        }
    }
}



