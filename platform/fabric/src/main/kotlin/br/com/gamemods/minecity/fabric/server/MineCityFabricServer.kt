package br.com.gamemods.minecity.fabric.server

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.annotation.side.ServerSideOnly
import br.com.gamemods.minecity.api.server.MineCityServer
import br.com.gamemods.minecity.core.dispatchers.Sync
import br.com.gamemods.minecity.fabric.MineCityFabric
import br.com.gamemods.minecity.fabric.wrapper.FabricMinecraftServerWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricServerWorldWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricWorldChunkWrapper
import com.github.michaelbull.logging.InlineLogger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Handle server-side procedures.
 */
@InternalMineCityApi
class MineCityFabricServer(val mcServer: FabricMinecraftServerWrapper): MineCityServer {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Sync + CoroutineName("MineCityFabricServer ${mcServer.serverIp} (Sync)")
    private val log = InlineLogger()
    override val platform: MineCityFabric get() = MineCityFabric
    override val serverIp: String get() = mcServer.serverIp

    @ServerSideOnly
    fun onServerStarting() {
        log.info { "Server is starting..." }
        platform.core.onServerStarting()
    }

    @ServerSideOnly
    fun onServerStarted() {
        log.info { "Server started" }
        platform.core.onServerStarted()
    }

    @ServerSideOnly
    fun onServerStopping() {
        log.info { "Server is stopping..." }
        cancel("The server is stopping")
        platform.core.onServerStopping()
    }

    @ServerSideOnly
    fun onServerStopped() {
        log.info { "Server stopped" }
        platform.core.onServerStopped()
    }

    @ServerSideOnly
    fun onChunkLoad(serverWorld: FabricServerWorldWrapper, worldChunk: FabricWorldChunkWrapper) {
        log.trace { "Chunk loaded: ${worldChunk.pos} at ${serverWorld.id} " }
    }

    @ServerSideOnly
    fun onChunkUnload(serverWorld: FabricServerWorldWrapper, worldChunk: FabricWorldChunkWrapper) {
        log.trace { "Chunk unloaded: ${worldChunk.pos} at ${serverWorld.id}" }
    }

    override fun isSync() = mcServer.isSync()

    /**
     * Runs immediately when on main thread, schedules a sync task otherwise.
     */
    fun <R> syncOnly(action: () -> R): Deferred<R> {
        return if (!isSync()) {
            async {
                action()
            }
        } else {
            try {
                CompletableDeferred(action())
            } catch (e: Throwable) {
                CompletableDeferred<R>().apply {
                    completeExceptionally(e)
                }
            }
        }
    }
}
