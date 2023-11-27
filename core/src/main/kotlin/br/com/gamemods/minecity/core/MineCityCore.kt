package br.com.gamemods.minecity.core

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.MineCityPlatform
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.annotation.side.ServerSideOnly
import br.com.gamemods.minecity.api.service.namedplayer.NamedPlayerService
import br.com.gamemods.minecity.core.service.world.WorldService
import com.github.michaelbull.logging.InlineLogger

/**
 * Implements the main functionalities of MineCity in a platform independent way.
 *
 * @property log The default plugin logger, wrapped by an [InlineLogger]
 * @property platform The MineCity platform implementations
 * @property worlds Allows access to Minecraft worlds.
 */
@InternalMineCityApi
class MineCityCore(
    inline val log: InlineLogger,
    override val platform: MineCityPlatform,
    val worlds: WorldService,
    override val players: NamedPlayerService,
): MineCity {

    /**
     * Executed when Minecraft is initializing
     */
    fun onInitialize() {
        log.info { "MineCityCore is initializing..." }
    }

    /**
     * Executed when Minecraft is loading a server environment to host
     */
    @ServerSideOnly
    fun onServerStarting() {
        log.info { "MineCityCore: Server is starting" }
    }

    /**
     * Executed when Minecraft server is ready to do the first server side tick.
     */
    @ServerSideOnly
    fun onServerStarted() {
        log.info { "MineCityCore started" }
    }

    /**
     * Executed when Minecraft server started the shutdown procedures.
     */
    @ServerSideOnly
    fun onServerStopping() {
        log.info { "MineCityCore: Server is stopping..." }
    }

    /**
     * Executed when Minecraft server has been shutdown and is about to be destructed.
     */
    @ServerSideOnly
    fun onServerStopped() {
        log.info { "MineCityCore: Server has stopped..." }
    }
}
