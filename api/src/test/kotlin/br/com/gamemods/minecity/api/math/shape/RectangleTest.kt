package br.com.gamemods.minecity.api.math.shape

import br.com.gamemods.minecity.api.math.pos.ChunkPosition
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RectangleTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            TestEnv.setupPositions()
        }
    }

    @Test
    fun contains() {
        val rectangle = Rectangle(0, 0, 10, 10)
        assertTrue(rectangle.contains(0, 0))
        assertTrue(rectangle.contains(10, 10))
        assertTrue(rectangle.contains(5, 5))
        assertFalse(rectangle.contains(-1, 0))
        assertFalse(rectangle.contains(-10, 0))
        assertFalse(rectangle.contains(0, -1))
        assertFalse(rectangle.contains(0, -10))
        assertFalse(rectangle.contains(11, 0))
        assertFalse(rectangle.contains(0, 11))
    }

    @Test
    fun intersects() {
        val rectangle1 = Rectangle(5, 5, 10, 10)
        val rectangle2 = Rectangle(5, 5, 17, 10)
        val chunk1 = ChunkPosition(0, 0)
        val chunk2 = ChunkPosition(1, 0)
        assertTrue(rectangle1.intersects(chunk1))
        assertFalse(rectangle1.intersects(chunk2))
        assertTrue(rectangle2.intersects(chunk1))
        assertTrue(rectangle2.intersects(chunk2))
    }
}
