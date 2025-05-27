package com.bomber7.core.model.square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreakableWallTest {

    @Test
    void testBreakableWallCreation() {
        BreakableWall wall = new BreakableWall("wood.png", 3, 6);

        assertEquals("wood.png", wall.getSpriteFilePath());
        assertEquals(3, wall.getXCoord());
        assertEquals(6, wall.getYCoord());
    }
}
