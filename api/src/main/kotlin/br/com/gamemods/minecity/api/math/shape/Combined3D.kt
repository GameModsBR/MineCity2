package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An additive combination of multiple 3D shapes
 */
@Serializable
@SerialName("combined3d")
public class Combined3D(override val components: Set<Shape3D>): Shape3D(), CombinedShape {
    override fun contains(x: Int, y: Int, z: Int): Boolean = components.any { it.contains(x, y, z) }
    override fun intersects(pos: ChunkPosition): Boolean = components.any { it.intersects(pos) }
    override fun to2D(y: Double): Combined2D = Combined2D(components.map { it.to2D(y) }.toSet())
}
