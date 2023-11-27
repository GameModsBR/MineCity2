package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.core.wrapper.server.WorldChunkWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricChunkPosWrapper.Companion.wrapper
import net.minecraft.world.chunk.WorldChunk

/**
 * Wraps [WorldChunk] and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricWorldChunkWrapper(override val native: WorldChunk): WorldChunkWrapper {
    companion object: Wrapper.WrapperClass<WorldChunk, FabricWorldChunkWrapper>(::FabricWorldChunkWrapper)

    override val pos: ChunkPosition get() = native.pos.wrapper
    override fun toString(): String {
        val pos = pos
        return "FabricWorldChunkWrapper(x=${pos.x},z=${pos.z})"
    }
}
