package br.com.gamemods.minecity.fabric.client

import br.com.gamemods.minecity.api.MineCityPlatform
import br.com.gamemods.minecity.api.annotation.UsedByReflection
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.annotation.side.ClientSideOnly
import br.com.gamemods.minecity.api.client.MineCityClient
import br.com.gamemods.minecity.fabric.MineCityFabric
import net.fabricmc.api.ClientModInitializer

@InternalMineCityApi
@UsedByReflection("fabric.mod.json, client entrypoint")
object MineCityFabricClient : ClientModInitializer, MineCityClient {
	override val platform: MineCityPlatform get() = MineCityFabric

	@ClientSideOnly
	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		MineCityFabric.client = this
	}
}
