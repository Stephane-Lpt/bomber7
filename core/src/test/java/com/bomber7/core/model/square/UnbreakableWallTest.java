package com.bomber7.core.model.square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnbreakableWallTest {

    @Test
    void testUnbreakableWallCreation() {
        UnbreakableWall wall = new UnbreakableWall("stone.png", 5, 7);

        assertEquals("stone.png", wall.getSpriteFilePath());
        assertEquals(5, wall.getXCoord());
        assertEquals(7, wall.getYCoord());
    }
}
