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
 * A readonly XYZ position of an entity.
 */
@Serializable(with = EntityPosition.Serializer::class)
public interface EntityPosition: BlockGridPositioned {
    public val x: Double
    public val y: Double
    public val z: Double

    override fun toBlockPos(): BlockPosition = BlockPosition(x.toInt(), y.toInt(), z.toInt())

    public companion object {
        @set:InternalMineCityApi
        public lateinit var constructor: (x: Double, y: Double, z: Double) -> EntityPosition

        @JvmName("get")
        public operator fun invoke(x: Double, y: Double, z: Double): EntityPosition = constructor(x, y, z)
        @JvmName("get")
        public operator fun invoke(x: Float, y: Float, z: Float): EntityPosition = this(x.toDouble(), y.toDouble(), z.toDouble())
        @JvmName("get")
        public operator fun invoke(x: Int, y: Int, z: Int): EntityPosition = this(x.toDouble(), y.toDouble(), z.toDouble())
    }

    public object Serializer: KSerializer<EntityPosition> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("br.com.gamemods.minecity.api.math.EntityPosition") {
            element<Double>("x")
            element<Double>("y")
            element<Double>("z")
        }

        override fun deserialize(decoder: Decoder): EntityPosition {
            return decoder.decodeStructure(descriptor) {
                var x = 0.0
                var y = 0.0
                var z = 0.0
                while (true) {
                    when (val index = decodeElementIndex(descriptor)) {
                        0 -> x = decodeDoubleElement(descriptor, 0)
                        1 -> y = decodeDoubleElement(descriptor, 1)
                        2 -> z = decodeDoubleElement(descriptor, 2)
                        CompositeDecoder.DECODE_DONE -> break
                        else -> error("Unexpected index: $index")
                    }
                }
                EntityPosition(x, y, z)
            }
        }

        override fun serialize(encoder: Encoder, value: EntityPosition) {
            val composite = encoder.beginStructure(descriptor)
            composite.encodeDoubleElement(descriptor, 0, value.x)
            composite.encodeDoubleElement(descriptor, 1, value.y)
            composite.encodeDoubleElement(descriptor, 2, value.z)
            composite.endStructure(descriptor)
        }
    }
}
