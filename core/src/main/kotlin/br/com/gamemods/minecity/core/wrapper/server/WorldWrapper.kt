package br.com.gamemods.minecity.core.wrapper.server

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.core.wrapper.Wrapper

/**
 * Wraps a native World object and gives access to its features.
 *
 * @property id A string that identifies this world in this platform.
 */
@InternalMineCityApi
interface WorldWrapper: Wrapper {
    val id: String
}
