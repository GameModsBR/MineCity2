package br.com.gamemods.minecity.fabric.service

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.NamedPlayer
import br.com.gamemods.minecity.api.service.namedplayer.NamedPlayerService
import br.com.gamemods.minecity.fabric.MineCityFabric
import com.google.common.cache.CacheBuilder
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.datetime.Clock
import java.util.*
import java.util.concurrent.TimeUnit

@InternalMineCityApi
class FabricNamedPlayerService(private val platform: MineCityFabric): NamedPlayerService {
    private val uuid2named = CacheBuilder.newBuilder().expireAfterAccess(30L, TimeUnit.MINUTES).build<UUID, Deferred<NamedPlayer?>>()
    private val name2named = CacheBuilder.newBuilder().expireAfterAccess(30L, TimeUnit.MINUTES).build<String, Deferred<NamedPlayer?>>()

    override fun get(uuid: UUID): Deferred<NamedPlayer?> {
        @Suppress("DuplicatedCode")
        return uuid2named.get(uuid) {
            platform.runOnServer { server ->
                server.syncOnly {
                    server.mcServer.native.playerManager.getPlayer(uuid)?.let { player ->
                        NamedPlayer(player.uuid, player.entityName, Clock.System.now()).also { named ->
                            name2named.put(named.name, CompletableDeferred(named))
                        }
                    }
                }
            }
        }
    }

    override fun get(name: String): Deferred<NamedPlayer?> {
        @Suppress("DuplicatedCode")
        return name2named.get(name) {
            platform.runOnServer { server ->
                server.syncOnly {
                    server.mcServer.native.playerManager.getPlayer(name)?.let { player ->
                        NamedPlayer(player.uuid, player.entityName, Clock.System.now()).also { named ->
                            uuid2named.put(named.uuid, CompletableDeferred(named))
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun contains(uuid: UUID): Boolean {
        return uuid2named.getIfPresent(uuid)?.let { deferred ->
            if (deferred.isCompleted) {
                try {
                    deferred.getCompleted() != null
                } catch (e: Throwable) {
                    false
                }
            } else {
                false
            }
        } ?: false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun contains(name: String): Boolean {
        return name2named.getIfPresent(name)?.let { deferred ->
            if (deferred.isCompleted) {
                try {
                    deferred.getCompleted() != null
                } catch (e: Throwable) {
                    false
                }
            } else {
                false
            }
        } ?: false
    }
}
