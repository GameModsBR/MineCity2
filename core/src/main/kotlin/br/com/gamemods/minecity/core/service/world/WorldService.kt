package br.com.gamemods.minecity.core.service.world

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.core.wrapper.server.WorldWrapper
import kotlinx.coroutines.Deferred

/**
 * Allows access to Minecraft worlds.
 */
@InternalMineCityApi
interface WorldService {
    /**
     * Gets the world by its id.
     *
     * Runs immediately when sync.
     */
    operator fun get(worldId: WorldId): Deferred<WorldWrapper?>
}
