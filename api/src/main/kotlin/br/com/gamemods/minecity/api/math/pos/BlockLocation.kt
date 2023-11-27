package br.com.gamemods.minecity.api.math.pos

import br.com.gamemods.minecity.api.annotation.PlatformDependent
import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.id.WorldId
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
 * A readonly XYZ position and WorldId of a block.
 */
@Serializable(with = BlockLocation.Serializer::class)
public interface BlockLocation: BlockPosition, BlockGridPositionedLocation {
    override fun toBlockPos(): BlockLocation = this

    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (worldId: WorldId, x: Int, y: Int, z: Int) -> BlockLocation

        @JvmName("get")
        public operator fun invoke(worldId: WorldId, x: Int, y: Int, z: Int): BlockLocation = constructor(worldId, x, y, z)
    }

    public object Serializer: KSerializer<BlockLocation> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.BlockLocation") {
            element<Int>("x")
            element<Int>("y")
            element<Int>("z")
            element<String>("world")
        }

        @OptIn(PlatformDependent::class)
        override fun deserialize(decoder: Decoder): BlockLocation {
            return decoder.decodeStructure(descriptor) {
                var x = 0
                var y = 0
                var z = 0
                var world = ""
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeIntElement(descriptor, 0)
                        1 -> y = decodeIntElement(descriptor, 1)
                        2 -> z = decodeIntElement(descriptor, 2)
                        3 -> world = decodeStringElement(descriptor, 3)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                BlockLocation(WorldId(world), x, y, z)
            }
        }

        @OptIn(PlatformDependent::class)
        override fun serialize(encoder: Encoder, value: BlockLocation) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeIntElement(descriptor, 0, value.x)
            composite.encodeIntElement(descriptor, 1, value.y)
            composite.encodeIntElement(descriptor, 2, value.z)
            composite.encodeStringElement(descriptor, 3, value.worldId.toString())
            composite.endStructure(descriptor)
        }
    }
}
