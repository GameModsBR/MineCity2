package br.com.gamemods.minecity.api.math.pos

/**
 * Something that exists on the Minecraft block grid.
 */
public interface BlockGridPositioned {
    /**
     * The [BlockPosition] where this object is located.
     */
    public fun toBlockPos(): BlockPosition

    /**
     * The [ChunkPosition] where this object is located.
     */
    public fun toChunkPos(): ChunkPosition = toBlockPos().toChunkPos()
}
