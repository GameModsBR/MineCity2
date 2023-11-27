package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockLocation
import br.com.gamemods.minecity.api.math.pos.EntityLocation
import br.com.gamemods.minecity.api.math.pos.EntityPosition

@Suppress("unused")
data class TestEntityLocation(override val worldId: WorldId, val pos: TestEntityPosition): EntityLocation, EntityPosition by pos {
    constructor(worldId: WorldId, x: Double, y: Double, z: Double): this(worldId, TestEntityPosition(x, y, z))

    override fun toBlockPos(): BlockLocation {
        return super<EntityLocation>.toBlockPos()
    }
}
