package br.com.gamemods.minecity.api.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

/**
 * [BigDecimal] configured to be serialized with [BigDecimalSerializer]
 */
public typealias Decimal = @Serializable(with = BigDecimalSerializer::class) BigDecimal

/**
 * Serializes [BigDecimal] as string using [BigDecimal.toPlainString]
 */
public object BigDecimalSerializer: KSerializer<BigDecimal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("java.math.BigDecimal", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): BigDecimal = BigDecimal(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: BigDecimal): Unit = encoder.encodeString(value.toPlainString())
}
