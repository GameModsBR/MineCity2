package br.com.gamemods.minecity.fabric.service

import br.com.gamemods.minecity.api.annotation.PlatformDependent
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.core.service.world.WorldService
import br.com.gamemods.minecity.core.wrapper.server.WorldWrapper
import br.com.gamemods.minecity.fabric.MineCityFabric
import br.com.gamemods.minecity.fabric.wrapper.FabricServerWorldWrapper.Companion.wrapper
import kotlinx.coroutines.Deferred
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.World

@InternalMineCityApi
class FabricWorldService(val platform: MineCityFabric): WorldService {
    override fun get(worldId: WorldId): Deferred<WorldWrapper?> {
        return platform.runOnServer { server ->
            server.syncOnly {
                server.mcServer.native.getWorld(worldId.toKey())?.wrapper
            }
        }
    }

    companion object {
        /**
         * Finds the [RegistryKey] for this [WorldId]
         */
        @OptIn(PlatformDependent::class)
        fun WorldId.toKey(): RegistryKey<World> {
            val id = toString()
            val index = id.indexOf(':')
            check(index >= 0) {
                "Failed to parse the WorldId $id"
            }
            return RegistryKey.of(RegistryKeys.WORLD, Identifier.of(id.substring(0, index), id.substring(index+1)))
        }
    }
}
