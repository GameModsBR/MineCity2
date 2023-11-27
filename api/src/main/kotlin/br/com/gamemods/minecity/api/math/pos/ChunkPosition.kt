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
 * A readonly XZ position of a chunk on the chunk grid.
 */
@Serializable(with = ChunkPosition.Serializer::class)
public interface ChunkPosition {
    public val x: Int
    public val z: Int

    public val minBlock: BlockPosition
    public val maxBlock: BlockPosition
    public val xBlockRange: IntRange
    public val zBlockRange: IntRange


    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (x: Int, z: Int) -> ChunkPosition

        @JvmName("get")
        public operator fun invoke(x: Int, z: Int): ChunkPosition = constructor(x, z)
    }

    public object Serializer: KSerializer<ChunkPosition> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.ChunkPosition") {
            element<Int>("x")
            element<Int>("z")
        }

        override fun deserialize(decoder: Decoder): ChunkPosition {
            return decoder.decodeStructure(descriptor) {
                var x = 0
                var z = 0
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeIntElement(descriptor, 0)
                        1 -> z = decodeIntElement(descriptor, 1)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                ChunkPosition(x, z)
            }
        }

        override fun serialize(encoder: Encoder, value: ChunkPosition) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeIntElement(descriptor, 0, value.x)
            composite.encodeIntElement(descriptor, 1, value.z)
            composite.endStructure(descriptor)
        }
    }
}
