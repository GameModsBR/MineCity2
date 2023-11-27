package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.BlockPosition
import br.com.gamemods.minecity.api.math.pos.ChunkPosition

data class TestChunkPosition(override val x: Int, override val z: Int): ChunkPosition {
    override val minBlock: BlockPosition get() = TestBlockPosition(x shl 4, Int.MIN_VALUE, z shl 4)
    override val maxBlock: BlockPosition get() = TestBlockPosition((x shl 4) + 15, Int.MAX_VALUE, (z shl 4) + 15)
    override val xBlockRange: IntRange get() = (x shl 4)..((x shl 4) + 15)
    override val zBlockRange: IntRange get() = (z shl 4)..((z shl 4) + 15)
}
