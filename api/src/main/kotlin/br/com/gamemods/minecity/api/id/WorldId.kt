package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.annotation.PlatformDependent
import kotlinx.serialization.Serializable

/**
 * String identifier that identifies a world in the current platform.
 */
@Serializable
@JvmInline
public value class WorldId @PlatformDependent constructor(private val string: String): Comparable<WorldId> {
    @PlatformDependent
    override fun toString(): String = string
    override fun compareTo(other: WorldId): Int = string.compareTo(other.string)
}
