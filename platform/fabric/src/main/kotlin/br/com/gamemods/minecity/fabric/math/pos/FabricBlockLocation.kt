package br.com.gamemods.minecity.fabric.math.pos

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockLocation
import br.com.gamemods.minecity.core.wrapper.BlockPositionWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricBlockPosWrapper

@InternalMineCityApi
data class FabricBlockLocation(
    override val worldId: WorldId,
    val blockPos: FabricBlockPosWrapper
): BlockLocation, BlockPositionWrapper by blockPos {
    constructor(worldId: WorldId, x: Int, y: Int, z: Int): this(worldId, FabricBlockPosWrapper(x, y, z))

    override fun toBlockPos(): BlockLocation = this
}
