package br.com.gamemods.minecity.core.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.ChunkPosition

/**
 * Wraps a native ChunkPos object and gives access to its features
 */
@InternalMineCityApi
interface ChunkPositionWrapper: Wrapper, ChunkPosition
