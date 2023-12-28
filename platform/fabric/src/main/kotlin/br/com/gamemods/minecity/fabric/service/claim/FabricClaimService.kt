package br.com.gamemods.minecity.fabric.service.claim

import br.com.gamemods.minecity.api.annotation.PlatformDependent
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
import br.com.gamemods.minecity.api.service.claim.ClaimService
import br.com.gamemods.minecity.fabric.wrapper.FabricBlockPosWrapper.Companion.wrapper
import net.minecraft.registry.RegistryKey
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

@InternalMineCityApi
class FabricClaimService {
    companion object {
        operator fun ClaimService.get(world: World, pos: BlockPos) = this[world.mineCityWorldId, pos.wrapper]

        @OptIn(PlatformDependent::class)
        val RegistryKey<World>.mineCityWorldId get() = WorldId(value.toString())

        val World.mineCityWorldId get() = registryKey.mineCityWorldId
    }
}
