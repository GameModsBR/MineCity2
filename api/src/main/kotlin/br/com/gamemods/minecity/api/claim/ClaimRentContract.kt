package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.math.VirtualMoney
import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * Information about an active rent, used by claims that is rented to someone.
 *
 * @property rentedBy Who set this claim on rent
 * @property rentedTo Who got the claim
 * @property originalSettings Backup of how the claim settings were before the rent
 * @property since The start time of the rent
 * @property expiresAt When the current contract expires, the rent may be prolonged by [maxExpiredTolerance]
 * @property maxExpiredTolerance How long after the [expiresAt] that the rent must still be active, charging an extra [expiredCharges].
 * @property renewedAt The last time this rent was renewed on this contract
 * @property renewals How many times this rent was renewed on this contract
 * @property renewalPrice The price to pay for the next renewal
 * @property totalPaidAmount The full amount paid since the start of the rent.
 * @property expiredCharges The amount to be paid in case it's paid during the [maxExpiredTolerance]
 */
@Serializable
public data class ClaimRentContract(
    val rentedBy: UniqueId,
    val rentedTo: UniqueId,
    val originalSettings: ClaimSettings,
    val since: Instant,
    val expiresAt: Instant,
    val maxExpiredTolerance: Duration,
    val originalPrice: VirtualMoney,
    val totalPaidAmount: VirtualMoney,
    val renewalPrice: VirtualMoney = originalPrice,
    val expiredCharges: VirtualMoney? = null,
    val renewals: Int = 0,
    val renewedAt: Instant? = null,
)
