package br.com.gamemods.minecity.api.claim

import br.com.gamemods.minecity.api.serializer.MiniComponent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed class ClaimFlagValue {
    public abstract val value: Any

    @Serializable
    @SerialName("component")
    public data class MiniComponentValue(override val value: MiniComponent): ClaimFlagValue()

    @Serializable
    @SerialName("empty")
    public data object Empty: ClaimFlagValue() {
        override val value: Unit = Unit
    }

    @Serializable
    @SerialName("string")
    public data class StringValue(override val value: String): ClaimFlagValue()

    @Serializable
    @SerialName("boolean")
    public data class BooleanValue(override val value: Boolean): ClaimFlagValue()

    @Serializable
    @SerialName("int")
    public data class IntValue(override val value: Int): ClaimFlagValue()
}
