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
 * A readonly XYZ position and WorldId of an entity.
 */
@Serializable(with = EntityLocation.Serializer::class)
public interface EntityLocation: EntityPosition, BlockGridPositionedLocation {
    override fun toBlockPos(): BlockLocation = BlockLocation(worldId, x.toInt(), y.toInt(), z.toInt())

    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (worldId: WorldId, x: Double, y: Double, z: Double) -> EntityLocation

        @JvmName("get")
        public operator fun invoke(worldId: WorldId, x: Double, y: Double, z: Double): EntityLocation = constructor(worldId, x, y, z)
        @JvmName("get")
        public operator fun invoke(worldId: WorldId, x: Float, y: Float, z: Float): EntityLocation = this(worldId, x.toDouble(), y.toDouble(), z.toDouble())
        @JvmName("get")
        public operator fun invoke(worldId: WorldId, x: Int, y: Int, z: Int): EntityLocation = this(worldId, x.toDouble(), y.toDouble(), z.toDouble())
    }

    public object Serializer: KSerializer<EntityLocation> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.EntityLocation") {
            element<Double>("x")
            element<Double>("y")
            element<Double>("z")
            element<String>("worldId")
        }

        @OptIn(PlatformDependent::class)
        override fun deserialize(decoder: Decoder): EntityLocation {
            return decoder.decodeStructure(descriptor) {
                var x = 0.0
                var y = 0.0
                var z = 0.0
                var worldId = ""
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeDoubleElement(descriptor, 0)
                        1 -> y = decodeDoubleElement(descriptor, 1)
                        2 -> z = decodeDoubleElement(descriptor, 2)
                        3 -> worldId = decodeStringElement(descriptor, 3)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                EntityLocation(WorldId(worldId), x, y, z)
            }
        }

        @OptIn(PlatformDependent::class)
        override fun serialize(encoder: Encoder, value: EntityLocation) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeDoubleElement(descriptor, 0, value.x)
            composite.encodeDoubleElement(descriptor, 1, value.y)
            composite.encodeDoubleElement(descriptor, 2, value.z)
            composite.encodeStringElement(descriptor, 3, value.worldId.toString())
            composite.endStructure(descriptor)
        }
    }
}
