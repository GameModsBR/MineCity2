package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.serialization.Serializable
import java.util.*

/**
 * UUID identifier that identifies a custom trust level registered on MineCity
 */
@Serializable
@JvmInline
public value class TrustLevelId(public val uuid: UniqueId): Comparable<TrustLevelId> {
    public constructor(): this(UUID.randomUUID())
    override fun compareTo(other: TrustLevelId): Int = uuid.compareTo(other.uuid)
    override fun toString(): String = uuid.toString()
    public companion object {
        public val EVERYONE: TrustLevelId = TrustLevelId(UniqueId(0L, 0L))
    }
}
