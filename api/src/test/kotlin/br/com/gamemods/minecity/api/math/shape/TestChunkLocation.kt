package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.ChunkLocation
import br.com.gamemods.minecity.api.math.pos.ChunkPosition

@Suppress("unused")
data class TestChunkLocation(override val worldId: WorldId, val pos: TestChunkPosition): ChunkLocation, ChunkPosition by pos {
    constructor(worldId: WorldId, x: Int, z: Int): this(worldId, TestChunkPosition(x, z))
}
