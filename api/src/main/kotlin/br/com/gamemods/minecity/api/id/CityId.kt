package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.serialization.Serializable
import java.util.*

/**
 * UUID identifier that identifies a city registered on MineCity
 */
@Serializable
@JvmInline
public value class CityId(public val uuid: UniqueId): Comparable<CityId> {
    public constructor(): this(UUID.randomUUID())
    override fun compareTo(other: CityId): Int = uuid.compareTo(other.uuid)
    override fun toString(): String = uuid.toString()
}
