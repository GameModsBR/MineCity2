package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.serialization.Serializable
import java.util.*

/**
 * UUID identifier that identifies a claim registered on MineCity
 */
@Serializable
@JvmInline
public value class ClaimId(public val uuid: UniqueId): Comparable<ClaimId> {
    public constructor(): this(UUID.randomUUID())
    override fun compareTo(other: ClaimId): Int = uuid.compareTo(other.uuid)
    override fun toString(): String = uuid.toString()
}
