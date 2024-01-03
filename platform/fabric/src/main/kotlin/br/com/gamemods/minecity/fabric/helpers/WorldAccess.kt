package br.com.gamemods.minecity.fabric.helpers

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World

/**
 * Gets a [BlockState] from a [BlockHitResult] by accessing a [World]
 *
 * You *should* have a reference to a world on which this [BlockHitResult] was queried.
 *
 * **Trivial**: This is a mere convenience extension function.
 * @param world The world being queried.
 * @author alikindsys
 */
fun BlockHitResult.blockStateBy(world: World) : BlockState {
    return world.getBlockState(this.blockPos)
}

/**
 * Gets a [BlockEntity] from a [BlockHitResult] by accessing a [World]
 *
 * You *should* have a reference to a world on which this [BlockHitResult] was queried.
 *
 * **Trivial**: This is a mere convenience extension function.
 * @param world The world being queried.
 * @author alikindsys
 */fun BlockHitResult.blockEntityBy(world: World) : BlockEntity? {
    return world.getBlockEntity(this.blockPos)
}