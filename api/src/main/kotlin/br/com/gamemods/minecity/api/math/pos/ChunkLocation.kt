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
 * A readonly XZ position and WorldID of a chunk on the chunk grid.
 */
@Serializable(with = ChunkLocation.Serializer::class)
public interface ChunkLocation: ChunkPosition, Location {
    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (worldId: WorldId, x: Int, z: Int) -> ChunkLocation

        @JvmName("get")
        public operator fun invoke(worldId: WorldId, x: Int, z: Int): ChunkLocation = constructor(worldId, x, z)
    }

    public object Serializer: KSerializer<ChunkLocation> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.ChunkLocation") {
            element<Int>("x")
            element<Int>("z")
            element<String>("worldId")
        }

        @OptIn(PlatformDependent::class)
        override fun deserialize(decoder: Decoder): ChunkLocation {
            return decoder.decodeStructure(descriptor) {
                var x = 0
                var z = 0
                var worldId = ""
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeIntElement(descriptor, 0)
                        1 -> z = decodeIntElement(descriptor, 1)
                        2 -> worldId = decodeStringElement(descriptor, 2)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                ChunkLocation(WorldId(worldId), x, z)
            }
        }

        @OptIn(PlatformDependent::class)
        override fun serialize(encoder: Encoder, value: ChunkLocation) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeIntElement(descriptor, 0, value.x)
            composite.encodeIntElement(descriptor, 1, value.z)
            composite.encodeStringElement(descriptor, 2, value.worldId.toString())
            composite.endStructure(descriptor)
        }
    }
}
