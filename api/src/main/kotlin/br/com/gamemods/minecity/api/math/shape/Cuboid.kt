package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Represents a rectangle (or square) storing the points with lowest and highest values.
 */
@Serializable
@SerialName("cuboid")
public data class Cuboid(
    val minX: Int,
    val minY: Int,
    val minZ: Int,
    val maxX: Int,
    val maxY: Int,
    val maxZ: Int,
): Shape3D() {
    @Transient
    private val xRange = minX..maxX
    @Transient
    private val yRange = minY..maxY
    @Transient
    private val zRange = minZ..maxZ

    /**
     * Discards [minY] and [maxY] to create a [Rectangle]
     */
    public fun toRectangle(): Rectangle = Rectangle(minX, minZ, maxX, maxZ)

    override fun to2D(y: Double): Shape2D {
        return if (y.toInt() in yRange) toRectangle()
        else Empty2D
    }

    override fun contains(x: Int, y: Int, z: Int): Boolean = x in xRange && y in yRange && z in zRange
    override fun intersects(pos: ChunkPosition): Boolean = Rectangle.chunkIntersectLogic(xRange, zRange, pos)
}
