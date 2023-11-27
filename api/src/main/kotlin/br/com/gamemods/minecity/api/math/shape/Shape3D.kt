package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.BlockPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A 3D geometric shape for X, Y, and Z coordinates.
 */
@Serializable
@SerialName("3d")
public sealed class Shape3D: Shape {
    /**
     * Checks if the given 3D point is part of the area of this shape.
     */
    public abstract fun contains(x: Int, y: Int, z: Int): Boolean

    /**
     * Checks if the given position is part of the area of this shape.
     */
    override fun contains(pos: BlockPosition): Boolean = contains(pos.x, pos.y, pos.z)
}
