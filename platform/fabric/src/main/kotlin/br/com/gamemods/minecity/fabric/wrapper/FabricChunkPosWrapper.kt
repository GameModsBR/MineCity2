package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.BlockPosition
import br.com.gamemods.minecity.core.wrapper.ChunkPositionWrapper
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricBlockPosWrapper.Companion.wrapper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos

/**
 * Wraps [ChunkPos] and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricChunkPosWrapper(override val native: ChunkPos): ChunkPositionWrapper {
    companion object : Wrapper.WrapperClass<ChunkPos, FabricChunkPosWrapper>(::FabricChunkPosWrapper)
    constructor(x: Int, z: Int): this(ChunkPos(x, z))
    override val x: Int get() = native.x
    override val z: Int get() = native.z

    override val minBlock: BlockPosition get() = BlockPos(native.startX, Int.MIN_VALUE, native.startZ).wrapper
    override val maxBlock: BlockPosition get() = BlockPos(native.endX, Int.MAX_VALUE, native.endZ).wrapper

    override val xBlockRange: IntRange get() = native.startX..native.endX
    override val zBlockRange: IntRange get() = native.startZ..native.endZ

    override fun toString(): String {
        return "FabricChunkPosWrapper(x=$x,z=$z)"
    }
}
