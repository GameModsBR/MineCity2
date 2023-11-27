package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.core.wrapper.server.ServerWorldWrapper
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.chunk.WorldChunk

/**
 * Wraps [WorldChunk] and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricServerWorldWrapper(override val native: ServerWorld): ServerWorldWrapper {
    companion object: Wrapper.WrapperClass<ServerWorld, FabricServerWorldWrapper>(::FabricServerWorldWrapper)

    override val id get() = native.registryKey.value.toString()

    override fun toString(): String {
        return "FabricServerWorldWrapper(id=$id)"
    }


}
