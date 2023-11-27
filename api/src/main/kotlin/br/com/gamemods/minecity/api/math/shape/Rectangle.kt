package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Represents a rectangle (or square) storing the points with lowest and highest values.
 */
@Serializable
@SerialName("rectangle")
public data class Rectangle(
    val minX: Int,
    val minZ: Int,
    val maxX: Int,
    val maxZ: Int,
): Shape2D() {
    @Transient
    public val xRange: IntRange = minX..maxX
    @Transient
    public val zRange: IntRange = minZ..maxZ
    override fun contains(x: Int, z: Int): Boolean = x in xRange && z in zRange
    override fun intersects(pos: ChunkPosition): Boolean = chunkIntersectLogic(xRange, zRange, pos)

    /**
     * Creates a [Cuboid] using the data from this rectangle added with [minY] and [maxY].
     */
    public fun to3D(minY: Int = Int.MIN_VALUE, maxY: Int = Int.MAX_VALUE): Cuboid = Cuboid(minX, minY, minZ, maxX, maxY, maxZ)

    internal companion object {
        internal fun chunkIntersectLogic(xRange: IntRange, zRange: IntRange, pos: ChunkPosition): Boolean {
            val minX = xRange.first
            val maxX = xRange.last
            val minZ = zRange.first
            val maxZ = zRange.last

            val chunkMinX = pos.minBlock.x
            val chunkMinZ = pos.minBlock.z
            val chunkMaxX = pos.maxBlock.x
            val chunkMaxZ = pos.maxBlock.z

            // Create ranges for chunk x and z coordinates
            val chunkXRange = chunkMinX..chunkMaxX
            val chunkZRange = chunkMinZ..chunkMaxZ

            // Check if any of the rectangle's corners are inside the chunk
            if ((minX in chunkXRange && minZ in chunkZRange) || // Bottom left corner
                    (minX in chunkXRange && maxZ in chunkZRange) || // Top left corner
                    (maxX in chunkXRange && minZ in chunkZRange) || // Bottom right corner
                    (maxX in chunkXRange && maxZ in chunkZRange)    // Top right corner
            ) {
                return true
            }

            // Check if the chunk is completely inside the rectangle
            return minX <= chunkMinX && maxX >= chunkMaxX && minZ <= chunkMinZ && maxZ >= chunkMaxZ
        }
    }
}
