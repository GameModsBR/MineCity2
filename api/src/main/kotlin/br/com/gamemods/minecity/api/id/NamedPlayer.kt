package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * Holds a player UUID, name and when the name was updated.
 *
 * Must be instantiated and changed only by MineCity, all objects are stored in a pool and reused.
 *
 * @property uuid The player UUID
 * @property name The player name as of [time]
 * @property time The time which the [name] was updated
 */
@Serializable
public class NamedPlayer @InternalMineCityApi constructor(
    public val uuid: UniqueId,
    @set:InternalMineCityApi
    public var name: String,
    @set:InternalMineCityApi
    public var time: Instant,
): Comparable<NamedPlayer> {
    override fun compareTo(other: NamedPlayer): Int {
        val diff = name.compareTo(other.name)
        return if (diff != 0) diff
        else uuid.compareTo(other.uuid)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NamedPlayer

        return uuid == other.uuid
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    override fun toString(): String {
        return "NamedPlayer(uuid=$uuid, name='$name')"
    }

    public operator fun component0(): UniqueId = uuid
    public operator fun component1(): String = name
}
