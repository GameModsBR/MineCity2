package br.com.gamemods.minecity.fabric.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import br.com.gamemods.minecity.core.wrapper.EntityPositionWrapper
import br.com.gamemods.minecity.core.wrapper.Wrapper
import br.com.gamemods.minecity.fabric.wrapper.FabricChunkPosWrapper.Companion.wrapper
import net.minecraft.util.math.ChunkSectionPos
import net.minecraft.util.math.Vec3d

/**
 * Wraps [Vec3d] as if it was an entity position and gives access to its features to MineCity core.
 */
@JvmInline
@InternalMineCityApi
value class FabricEntityPosWrapper(override val native: Vec3d): EntityPositionWrapper {
    companion object : Wrapper.WrapperClass<Vec3d, FabricEntityPosWrapper>(::FabricEntityPosWrapper)
    constructor(x: Double, y: Double, z: Double): this(Vec3d(x, y, z))
    override val x: Double get() = native.x
    override val y: Double get() = native.y
    override val z: Double get() = native.z
    override fun toChunkPos(): ChunkPosition = ChunkSectionPos.from(native).toChunkPos().wrapper
    override fun toString(): String {
        return "FabricEntityPosWrapper(x=$x,y=$y,z=$z)"
    }
}
