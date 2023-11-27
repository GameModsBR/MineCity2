package br.com.gamemods.minecity.api.math

import br.com.gamemods.minecity.api.id.VirtualCurrencyId
import br.com.gamemods.minecity.api.serializer.Decimal
import kotlinx.serialization.Serializable

/**
 * Represents a money amount out of a virtual currency from an economy plugin/mod.
 *
 * @property currency The currency id of this amount.
 * @property quantity The amount of [currency], can be fractional.
 */
@Serializable
public data class VirtualMoney(
    val currency: VirtualCurrencyId,
    val quantity: Decimal,
)
