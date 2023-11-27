package br.com.gamemods.minecity.api.id

import kotlinx.serialization.Serializable

/**
 * String identifier that identifies the ID of a flag that can be applied on MineCity claims.
 */
@Serializable
@JvmInline
public value class ClamFlagId(private val id: String) {
    override fun toString(): String = id
}
