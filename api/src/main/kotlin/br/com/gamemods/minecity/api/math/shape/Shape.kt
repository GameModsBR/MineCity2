package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.BlockGridPositioned
import br.com.gamemods.minecity.api.math.pos.BlockPosition
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.Serializable

/**
 * A geometric shape with unknown amount of dimensions
 */
@Serializable
public sealed interface Shape {
    /**
     * Removes the Y coordinate, getting the equivalent [Shape2D].
     *
     * Returns itself if this is already a [Shape2D].
     *
     * @param y The y coordinate to calculate the 2D slice on a 3D environment.
     */
    public fun to2D(y: Double): Shape2D

    /**
     * Checks if the given chunk coordinate intersects with this shape.
     */
    public fun intersects(pos: ChunkPosition): Boolean

    /**
     * Checks if the given 2D point is part of the area of this shape.
     */
    public operator fun contains(pos: BlockPosition): Boolean

    /**
     * Checks if the given 2D point is part of the area of this shape.
     */
    public operator fun contains(pos: BlockGridPositioned): Boolean = contains(pos.toBlockPos())
}
