package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the lack of a shape, an absense on the universe.
 */
@Serializable
@SerialName("empty2d")
public data object Empty2D: Shape2D(), EmptyShape {
    override fun intersects(pos: ChunkPosition): Boolean = false
    override fun contains(x: Int, z: Int): Boolean = false
}
