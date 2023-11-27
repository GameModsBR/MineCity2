package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the lack of a shape, an absense on the universe.
 */
@Serializable
@SerialName("empty3d")
public data object Empty3D: Shape3D(), EmptyShape {
    override fun to2D(y: Double): Empty2D = Empty2D
    override fun intersects(pos: ChunkPosition): Boolean = false
    override fun contains(x: Int, y: Int, z: Int): Boolean = false
}
