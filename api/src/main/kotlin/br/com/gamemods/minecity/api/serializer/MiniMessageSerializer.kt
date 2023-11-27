package br.com.gamemods.minecity.api.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * [Component] configured to be serialized using [MiniMessageSerializer]
 */
public typealias MiniComponent = @Serializable(with = MiniMessageSerializer::class) Component

/**
 * Serializes [Component] as string using the Adventure's mini message format.
 */
public object MiniMessageSerializer: KSerializer<Component> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("br.com.gamemods.minecity.api.serializer.MiniMessage", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Component {
        return MiniMessage.miniMessage().deserialize(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Component) {
        encoder.encodeString(MiniMessage.miniMessage().serialize(value))
    }
}
