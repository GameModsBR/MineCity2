package br.com.gamemods.minecity.core.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.EntityPosition

/**
 * Wraps a native EntityPos object and gives access to its features
 */
@InternalMineCityApi
interface EntityPositionWrapper: Wrapper, EntityPosition
