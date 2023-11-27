package br.com.gamemods.minecity.api.math.shape

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An additive combination of multiple shapes
 *
 * @property components The shapes that part of the area of this shape, they don't need to connect.
 */
@Serializable
@SerialName("combined")
public sealed interface CombinedShape: Shape {
    public val components: Set<Shape>
}
