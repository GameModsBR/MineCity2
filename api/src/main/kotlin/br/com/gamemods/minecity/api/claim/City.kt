package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.id.CityId
import br.com.gamemods.minecity.api.serializer.MiniComponent
import kotlinx.serialization.Serializable

/**
 * A city in MineCity represents a group of claims, cities may also have mayors and elections.
 *
 * @property id ID of this city
 * @property name Visible name of this city
 * @property parent If this city is child of another
 * @property mayors A list of all mayors this city had, last on list is last on start time.
 */
@Serializable
public data class City(
    val name: MiniComponent,
    val id: CityId = CityId(),
    val parent: CityId? = null,
    val mayors: List<CityMayor> = emptyList(),
)
