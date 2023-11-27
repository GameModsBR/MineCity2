package br.com.gamemods.minecity.api.math.shape

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the lack of a shape, an absense on the universe.
 */
@Serializable
@SerialName("empty")
public sealed interface EmptyShape: Shape
