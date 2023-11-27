package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.BlockLocation
import br.com.gamemods.minecity.api.math.pos.BlockPosition

data class TestBlockLocation(override val worldId: WorldId, val pos: TestBlockPosition): BlockLocation, BlockPosition by pos {
    constructor(worldId: WorldId, x: Int, y: Int, z: Int): this(worldId, TestBlockPosition(x, y, z))

    override fun toBlockPos(): BlockLocation = this
}
