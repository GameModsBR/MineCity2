package br.com.gamemods.minecity.fabric.math.pos

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockLocation
import br.com.gamemods.minecity.api.math.pos.EntityLocation
import br.com.gamemods.minecity.core.wrapper.EntityPositionWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricEntityPosWrapper

@InternalMineCityApi
data class FabricEntityLocation(
    override val worldId: WorldId,
    val entityPos: FabricEntityPosWrapper
): EntityLocation, EntityPositionWrapper by entityPos {
    constructor(worldId: WorldId, x: Double, y: Double, z: Double): this(worldId, FabricEntityPosWrapper(x, y, z))
    override fun toBlockPos(): BlockLocation = super<EntityLocation>.toBlockPos()
}
