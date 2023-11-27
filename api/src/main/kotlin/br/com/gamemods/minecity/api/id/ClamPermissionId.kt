package br.com.gamemods.minecity.api.id

import kotlinx.serialization.Serializable

/**
 * String identifier that identifies the ID of a permission that can be configured on MineCity claims.
 */
@Serializable
@JvmInline
public value class ClamPermissionId(private val id: String) {
    override fun toString(): String = id
}
