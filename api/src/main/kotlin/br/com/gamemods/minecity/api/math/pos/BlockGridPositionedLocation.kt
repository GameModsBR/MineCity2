package br.com.gamemods.minecity.api.math.pos

/**
 * Something that exists on the Minecraft block grid and is possible to determine the WorldID
 */
public interface BlockGridPositionedLocation: BlockGridPositioned, Location {
    override fun toBlockPos(): BlockLocation
}
