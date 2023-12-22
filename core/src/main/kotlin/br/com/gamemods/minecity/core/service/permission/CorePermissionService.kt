package br.com.gamemods.minecity.core.service.permission

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.ClaimPermissionId
import br.com.gamemods.minecity.api.service.permission.ClaimPermission
import br.com.gamemods.minecity.api.service.permission.PermissionService

@InternalMineCityApi
class CorePermissionService: PermissionService {
    private val permissions = mutableMapOf<ClaimPermissionId, ClaimPermission>()

    override fun get(id: ClaimPermissionId): ClaimPermission = permissions[id] ?: error("Unknown permission $id")

    override fun plusAssign(permission: ClaimPermission) {
        permissions.compute(permission.id) { _, old ->
            if(old == null) permission
            else error("Permission ${permission.id} already registered")
        }
        ClaimPermission.onRegister(permission)
    }
}
