package br.com.gamemods.minecity.api.client

import br.com.gamemods.minecity.api.MineCityPlatform

/**
 * Client side instance that handles GUI and client features
 *
 * @property platform The platform where MineCity is running
 */
public interface MineCityClient {
    public val platform: MineCityPlatform
}
