package br.com.gamemods.minecity.core.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.BlockPosition

/**
 * Wraps a native BlockPos object and gives access to its features
 */
@InternalMineCityApi
interface BlockPositionWrapper: Wrapper, BlockPosition
