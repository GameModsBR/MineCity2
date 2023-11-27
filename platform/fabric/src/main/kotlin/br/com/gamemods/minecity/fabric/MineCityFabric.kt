package br.com.gamemods.minecity.fabric

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.MineCityPlatform
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.annotation.side.ServerSideOnly
import br.com.gamemods.minecity.api.client.MineCityClient
import br.com.gamemods.minecity.api.math.pos.*
import br.com.gamemods.minecity.core.MineCityCore
import br.com.gamemods.minecity.core.dispatchers.Async
import br.com.gamemods.minecity.core.dispatchers.Sync
import br.com.gamemods.minecity.fabric.math.pos.FabricBlockLocation
import br.com.gamemods.minecity.fabric.math.pos.FabricChunkLocation
import br.com.gamemods.minecity.fabric.math.pos.FabricEntityLocation
import br.com.gamemods.minecity.fabric.server.MineCityFabricServer
import br.com.gamemods.minecity.fabric.service.FabricNamedPlayerService
import br.com.gamemods.minecity.fabric.service.FabricWorldService
import br.com.gamemods.minecity.fabric.wrapper.FabricBlockPosWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricChunkPosWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricEntityPosWrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricMinecraftServerWrapper.Companion.wrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricServerWorldWrapper.Companion.wrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricWorldChunkWrapper.Companion.wrapper
import com.github.michaelbull.logging.InlineLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.chunk.WorldChunk

/**
 * Common MineCity Fabric mod entrypoint, also implements [MineCityPlatform].
 */
@InternalMineCityApi
object MineCityFabric : ModInitializer, MineCityPlatform {
	override lateinit var core: MineCityCore
	override var client: MineCityClient? = null
	override var server: MineCityFabricServer? = null; private set
    private val logger = InlineLogger("minecity")

	private fun matchServer(server: MinecraftServer): MineCityFabricServer? {
		return MineCityFabric.server?.takeIf { it.mcServer.native === server }
	}

	@OptIn(ServerSideOnly::class)
	override fun onInitialize() {
		Dispatchers.Async = Dispatchers.Default
		Dispatchers.Sync = Dispatchers.Main
		BlockPosition.constructor = ::FabricBlockPosWrapper
		BlockLocation.constructor = ::FabricBlockLocation
		ChunkPosition.constructor = ::FabricChunkPosWrapper
		ChunkLocation.constructor = ::FabricChunkLocation
		EntityPosition.constructor = ::FabricEntityPosWrapper
		EntityLocation.constructor = ::FabricEntityLocation
		core = MineCityCore(
			log = logger,
			platform = this,
			worlds = FabricWorldService(this),
			players = FabricNamedPlayerService(this),
		)
		MineCity.instance = core
		core.onInitialize()
		ServerLifecycleEvents.SERVER_STARTING.register(this::handleServerStarting)
		ServerLifecycleEvents.SERVER_STARTED.register(this::handleServerStarted)
		ServerLifecycleEvents.SERVER_STOPPING.register(this::handleServerStopping)
		ServerLifecycleEvents.SERVER_STOPPED.register(this::handleServerStopped)
		ServerChunkEvents.CHUNK_LOAD.register(this::handleChunkLoad)
		ServerChunkEvents.CHUNK_UNLOAD.register(this::handleChunkUnload)
	}

	@ServerSideOnly
	private fun handleServerStarting(server: MinecraftServer) {
		Dispatchers.Sync = server.asCoroutineDispatcher()
		val serverPlatform = MineCityFabricServer(server.wrapper)
		MineCityFabric.server = serverPlatform
		serverPlatform.onServerStarting()
	}

	@ServerSideOnly
	private fun handleServerStarted(server: MinecraftServer) {
		matchServer(server)?.onServerStarted()
	}

	@ServerSideOnly
	private fun handleServerStopping(server: MinecraftServer) {
		Dispatchers.Sync = Dispatchers.Main
		matchServer(server)?.onServerStopping()
	}

	@ServerSideOnly
	private fun handleServerStopped(server: MinecraftServer) {
		matchServer(server)?.onServerStopped()
	}

	@ServerSideOnly
	private fun handleChunkLoad(world: ServerWorld, chunk: WorldChunk) {
		matchServer(world.server)?.onChunkLoad(world.wrapper, chunk.wrapper)
	}

	@ServerSideOnly
	private fun handleChunkUnload(world: ServerWorld, chunk: WorldChunk) {
		matchServer(world.server)?.onChunkUnload(world.wrapper, chunk.wrapper)
	}

	/**
	 * If [MineCityFabric.server] is not null runs [server], fails otherwise.
	 */
	inline fun <R> runOnServer(crossinline server: (MineCityFabricServer) -> R): R {
		val mcs = MineCityFabric.server
		if (mcs != null) {
			return server(mcs)
		}
		error("No server!")
	}
}
