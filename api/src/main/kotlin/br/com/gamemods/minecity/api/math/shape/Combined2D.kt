package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An additive combination of multiple 2D shapes
 */
@Serializable
@SerialName("combined2d")
public class Combined2D(override val components: Set<Shape2D>): Shape2D(), CombinedShape {
    override fun contains(x: Int, z: Int): Boolean = components.any { it.contains(x, z) }
    override fun intersects(pos: ChunkPosition): Boolean = components.any { it.intersects(pos) }
}
