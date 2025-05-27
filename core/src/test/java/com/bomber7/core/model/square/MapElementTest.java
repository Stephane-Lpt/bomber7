package com.bomber7.core.model.square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapElementTest {

    @Test
    void testMapElementInitialization() {
        BreakableWall wall = new BreakableWall("breakable.png", 1, 2);
        assertEquals("breakable.png", wall.getSpriteFilePath());
        assertEquals(1, wall.getXCoord());
        assertEquals(2, wall.getYCoord());
    }

    @Test
    void testNullSpritePathThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BreakableWall(null, 0, 0));
    }

    @Test
    void testBlankSpritePathThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BreakableWall("   ", 0, 0));
    }

    @Test
    void testNegativeXThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BreakableWall("   ", -5, 0));
    }

    @Test
    void testNegativeYThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new BreakableWall("   ", 0, -5));
    }

}
