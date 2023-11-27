package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.serializer.UniqueId
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * Data about a mayor of a city.
 *
 * @property playerId The player ID of the mayor
 * @property votes How many votes did this mayor receive during election
 * @property totalVotes How may votes were cast to all candidates of an election
 * @property since Since when this player started to be a mayor
 * @property until When this player stops being mayor
 * @property electionDuration How long did the election last
 * @property candidates How many candidates were available, including this mayor
 * @property voters How many people could vote, may include this mayor
 */
@Serializable
public data class CityMayor(
    val playerId: UniqueId,
    val votes: Int = 0,
    val totalVotes: Int = 0,
    val candidates: Int = 0,
    val voters: Int = 0,
    val until: Instant? = null,
    val electionDuration: Duration = Duration.ZERO,
    val since: Instant = Clock.System.now(),
)
