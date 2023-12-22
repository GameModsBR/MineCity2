package br.com.gamemods.minecity.api.service

import br.com.gamemods.minecity.api.MineCity
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi

/**
 * Internal MineCity states and services, should not be used by API users.
 */
@InternalMineCityApi
public object MineCityInternal {
    /**
     * Access to the MineCity API implementation, must be modified only by MineCity itself, can be accessed freely.
     */
    public lateinit var implementation: MineCity
}
