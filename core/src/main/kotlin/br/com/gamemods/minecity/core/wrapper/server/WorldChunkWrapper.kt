package br.com.gamemods.minecity.core.wrapper.server

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import br.com.gamemods.minecity.core.wrapper.Wrapper

/**
 * Wraps a native WorldChunk object and gives access to its features.
 *
 * @property pos The X and Z position of this chunk.
 */
@InternalMineCityApi
interface WorldChunkWrapper: Wrapper {
    val pos: ChunkPosition
}
