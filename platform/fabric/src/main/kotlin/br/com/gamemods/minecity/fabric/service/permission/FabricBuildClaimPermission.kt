package br.com.gamemods.minecity.fabric.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.fabric.MineCityFabric
import br.com.gamemods.minecity.fabric.common.mixin.BlockItemAccessor
import br.com.gamemods.minecity.fabric.event.BlockCanPlaceCallback
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.kyori.adventure.text.Component
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.BucketItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
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
class FabricBuildClaimPermission: FabricClaimPermission(
    id = ClaimPermissionId.BUILD,
    name = Component.text("Build"),
    description = Component.text("Allows the player to build in the claim.")
) {
    override fun onRegister() {
        PlayerBlockBreakEvents.BEFORE.register(OnBlockBreak())
        BlockCanPlaceCallback.EVENT.register(OnBlockCanPlace())
        UseBlockCallback.EVENT.register(OnUseBlock())
    }

    private inner class OnUseBlock : UseBlockCallback {
        override fun interact(player: PlayerEntity, world: World, hand: Hand, hitResult: BlockHitResult): ActionResult {
            if (world.isClient) {
                return ActionResult.PASS
            }

            if (hand != Hand.MAIN_HAND || hitResult.type != HitResult.Type.BLOCK) {
                return ActionResult.PASS
            }

            val bucket = player.mainHandStack.item as? BucketItem ?: return ActionResult.PASS
            val fluid = (bucket as BlockItemAccessor).fluid
            val affectedBlockPos = if (fluid == Fluids.EMPTY) {
                hitResult.blockPos
            } else {
                hitResult.blockPos.offset(hitResult.side)
            }
            MineCityFabric.core.log.info { "Bucket: $bucket, fluid: $fluid, affectedBlockPos: $affectedBlockPos, hitResult.blockPos: ${hitResult.blockPos}" }
            return if (checkPermission(world, player, affectedBlockPos, ClaimPermissionId.BUILD)) {
                ActionResult.PASS
            } else {
                ActionResult.FAIL
            }
        }
    }

    private inner class OnBlockBreak : PlayerBlockBreakEvents.Before {
        override fun beforeBlockBreak(world: World, player: PlayerEntity, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?): Boolean {
            return checkPermission(world, player, pos, ClaimPermissionId.BUILD)
        }
    }

    private inner class OnBlockCanPlace : BlockCanPlaceCallback {
        override fun canPlace(context: ItemPlacementContext, state: BlockState): Boolean {
            val player = context.player ?: return true // TODO: If the player is null, might be a piston, CC turtle, etc; Must check if the owner of the block has permission to build on the target claim
            return checkPermission(context.world, player, context.blockPos, ClaimPermissionId.BUILD)
        }
    }

}
