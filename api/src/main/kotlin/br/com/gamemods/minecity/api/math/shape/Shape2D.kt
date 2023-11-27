package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.BlockPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A 2D geometric shape for X and Z coordinates.
 *
 * Y coordinates are ignored when using a 3D positions.
 */
@Serializable
@SerialName("2d")
public sealed class Shape2D: Shape {
    /**
     * Returns itself.
     */
    @Deprecated("2D shapes always returns itself on to2D()", ReplaceWith(""))
    final override fun to2D(y: Double): Shape2D = this

    /**
     * Checks if the given 2D point is part of the area of this shape.
     */
    public abstract fun contains(x: Int, z: Int): Boolean

    /**
     * Checks if the given position is part of the area of this shape.
     */
    final override fun contains(pos: BlockPosition): Boolean = contains(pos.x, pos.z)
}
