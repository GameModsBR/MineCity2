package br.com.gamemods.minecity.api.math.pos

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

/**
 * A readonly XYZ position of a bock.
 */
@Serializable(with = BlockPosition.Serializer::class)
public interface BlockPosition: BlockGridPositioned {
    public val x: Int
    public val y: Int
    public val z: Int

    override fun toBlockPos(): BlockPosition = this

    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (x: Int, y: Int, z: Int) -> BlockPosition

        @JvmName("get")
        public operator fun invoke(x: Int, y: Int, z: Int): BlockPosition = constructor(x, y, z)
    }

    public object Serializer: KSerializer<BlockPosition> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.BlockPosition") {
            element<Int>("x")
            element<Int>("y")
            element<Int>("z")
        }

        override fun deserialize(decoder: Decoder): BlockPosition {
            return decoder.decodeStructure(descriptor) {
                var x = 0
                var y = 0
                var z = 0
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeIntElement(descriptor, 0)
                        1 -> y = decodeIntElement(descriptor, 1)
                        2 -> z = decodeIntElement(descriptor, 2)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                BlockPosition(x, y, z)
            }
        }

        override fun serialize(encoder: Encoder, value: BlockPosition) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeIntElement(descriptor, 0, value.x)
            composite.encodeIntElement(descriptor, 1, value.y)
            composite.encodeIntElement(descriptor, 2, value.z)
            composite.endStructure(descriptor)
        }
    }
}
