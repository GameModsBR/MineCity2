package br.com.gamemods.minecity.fabric.event

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext

@InternalMineCityApi
interface BlockCanPlaceCallback {

    fun canPlace(context: ItemPlacementContext, state: BlockState): Boolean

    companion object {
        @JvmField
        val EVENT: Event<BlockCanPlaceCallback> = EventFactory.createArrayBacked(BlockCanPlaceCallback::class.java) { listeners ->
            object : BlockCanPlaceCallback {
                override fun canPlace(context: ItemPlacementContext, state: BlockState): Boolean {
                    for (listener in listeners) {
                        if (!listener.canPlace(context, state))
                            return false
                    }

                    return true
                }
            }
        }
    }
}
