package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.BlockLocation
import br.com.gamemods.minecity.api.math.pos.BlockPosition
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import br.com.gamemods.minecity.api.math.pos.EntityPosition

object TestEnv {
    @OptIn(InternalMineCityApi::class)
    fun setupPositions() {
        BlockPosition.constructor = ::TestBlockPosition
        BlockLocation.constructor = ::TestBlockLocation
        ChunkPosition.constructor = ::TestChunkPosition
        EntityPosition.constructor = ::TestEntityPosition
    }
}
