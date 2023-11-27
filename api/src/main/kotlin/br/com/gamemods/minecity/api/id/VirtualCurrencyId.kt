package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.annotation.PlatformDependent
import kotlinx.serialization.Serializable

/**
 * String identifier that identifies a currency in the current platform.
 */
@Serializable
@JvmInline
public value class VirtualCurrencyId @PlatformDependent constructor(private val id: String): Comparable<VirtualCurrencyId> {
    @PlatformDependent
    override fun toString(): String = id
    override fun compareTo(other: VirtualCurrencyId): Int = id.compareTo(other.id)
}
