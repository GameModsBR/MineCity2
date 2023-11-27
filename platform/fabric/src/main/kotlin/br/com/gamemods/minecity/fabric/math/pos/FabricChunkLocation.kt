package br.com.gamemods.minecity.fabric.math.pos

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.math.pos.ChunkLocation
import br.com.gamemods.minecity.core.wrapper.ChunkPositionWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricChunkPosWrapper

@InternalMineCityApi
data class FabricChunkLocation(
    override val worldId: WorldId,
    val chunkPos: FabricChunkPosWrapper
): ChunkLocation, ChunkPositionWrapper by chunkPos {
    constructor(worldId: WorldId, x: Int, z: Int): this(worldId, FabricChunkPosWrapper(x, z))
}
