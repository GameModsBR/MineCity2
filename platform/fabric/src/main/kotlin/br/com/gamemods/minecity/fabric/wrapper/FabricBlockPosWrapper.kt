package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import br.com.gamemods.minecity.core.wrapper.BlockPositionWrapper
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricChunkPosWrapper.Companion.wrapper
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkSectionPos

/**
 * Wraps [BlockPos] and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricBlockPosWrapper(override val native: BlockPos): BlockPositionWrapper {
    companion object : Wrapper.WrapperClass<BlockPos, FabricBlockPosWrapper>(::FabricBlockPosWrapper)
    constructor(x: Int, y: Int, z: Int): this(BlockPos(x, y, z))
    override val x: Int get() = native.x
    override val y: Int get() = native.y
    override val z: Int get() = native.z
    override fun toChunkPos(): ChunkPosition = ChunkSectionPos.from(native).toChunkPos().wrapper
    override fun toString(): String {
        return "FabricBlockPosWrapper(x=$x,y=$y,z=$z)"
    }
}
