package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.core.wrapper.server.MinecraftServerWrapper
import net.minecraft.server.MinecraftServer

/**
 * Wraps [MinecraftServer] and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricMinecraftServerWrapper(override val native: MinecraftServer): MinecraftServerWrapper {
    companion object: Wrapper.WrapperClass<MinecraftServer, FabricMinecraftServerWrapper>(::FabricMinecraftServerWrapper)

    override val serverIp get() = native.serverIp

    override fun isSync() = native.isOnThread

    override fun toString(): String {
        return "FabricMinecraftServerWrapper(serverIp=$serverIp)"
    }

}
