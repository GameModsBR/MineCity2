package br.com.gamemods.minecity.api.service.permission

import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.id.NamedPlayer

/**
 * Manages the [NamedPlayer] objects.
 */
public interface PermissionService {
    /**
     * Gets [ClaimPermission] for the given [id]
     */
    public operator fun get(id: ClaimPermissionId): ClaimPermission

    /**
     * Registers a new [ClaimPermission], the [ClaimPermission.id] must be unique.
     */
    public operator fun plusAssign(permission: ClaimPermission)
}
