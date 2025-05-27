package com.bomber7.core.model.square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SquareTest {

    @Test
    void testConstructorWithOnlySprite() {
        Square square = new Square("wall.png");
        assertEquals("wall.png", square.getSpriteFilePath());
        assertNull(square.getMapElement());
    }

    @Test
    void testConstructorWithSpriteAndMapElement() {
        MapElement element = new BreakableWall("wall.png", 2, 3);
        Square square = new Square("tile.png", element);

        assertEquals("tile.png", square.getSpriteFilePath());
        assertEquals(element, square.getMapElement());
    }

    @Test
    void testBlankSpritePathThrowsException() {
        MapElement element = new BreakableWall("wall.png", 2, 3);
        assertThrows(IllegalArgumentException.class, () -> new Square("    ", element));
    }

    @Test
    void testConstructorWithNullSpriteThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Square(null));
    }

    @Test
    void testConstructorWithNullSpriteAndElementThrowsException() {
        MapElement element = new BreakableWall("wall.png", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> new Square(null, element));
    }
}
