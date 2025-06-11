package com.bomber7.core.map;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import com.bomber7.utils.ProjectPaths;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LevelMapTest {

    /**
     * Path to the JSON file containing the tileset export file.
     */
    private final Path jsonPath = ProjectPaths.getTileset();

    /**
     * Ensures LevelMapFactory loads the map accordingly.
     * Getters to compare actual squares with the ones added to the map.
     */
    @Test
    void testGetSquareValidCoordinatesReturnsCorrectSquare() {
        // Create squares with map elements
        Square square1 = new Square(
            "texture1",
            new UnbreakableWall("unbreakable"));
        Square square2 = new Square(
            "texture2",
            new BreakableWall("breakable"));

        // Create a 2x2 checkerboard grid
        List<List<Square>> checkerboard = new ArrayList<>();
        List<Square> row1 = new ArrayList<>();
        row1.add(square1); // (0,0)
        row1.add(square2); // (1,0)

        List<Square> row2 = new ArrayList<>();
        row2.add(square2); // (0,1)
        row2.add(square1); // (1,1)

        checkerboard.add(row1);
        checkerboard.add(row2);

        LevelMap levelMap = new LevelMap(checkerboard, 800, 600);

        // Assert that the correct square is returned for each coordinate
        assertSame(square1, levelMap.getSquare(0, 0));
        assertSame(square2, levelMap.getSquare(1, 0));
        assertSame(square2, levelMap.getSquare(0, 1));
        assertSame(square1, levelMap.getSquare(1, 1));

        // Assert that map elements exist and are of correct type
        assertTrue(levelMap.getSquare(0, 0).hasMapElement());
        assertTrue(levelMap.getSquare(0, 0).getMapElement().toString().contains("UnbreakableWall"));
        assertTrue(levelMap.getSquare(1, 0).getMapElement().toString().contains("BreakableWall"));
    }

    @Test
    void testGetSquareInvalidCoordinatesThrowsException() {
        // Create a 1x1 map with one square
        Square square = new Square("texture",
            new BreakableWall("break")
        );
        List<List<Square>> checkerboard = new ArrayList<>();
        List<Square> row = new ArrayList<>();
        row.add(square); // (0,0)
        checkerboard.add(row);

        LevelMap levelMap = new LevelMap(checkerboard, 800, 600);

        // Assert that accessing out-of-bounds coordinates throws IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> levelMap.getSquare(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> levelMap.getSquare(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> levelMap.getSquare(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> levelMap.getSquare(0, -1));
    }

    @Test   
    void testGetAbsoluteCoordinates(){
        // Create a 2x2 checkerboard grid
        List<List<Square>> checkerboard = new ArrayList<>();
        List<Square> row1 = new ArrayList<>();
        row1.add(new Square("texture1", null)); // (0,0)
        row1.add(new Square("texture2", null)); // (1,0)

        List<Square> row2 = new ArrayList<>();
        row2.add(new Square("texture3", null)); // (0,1)
        row2.add(new Square("texture4", null)); // (1,1)

        checkerboard.add(row1);
        checkerboard.add(row2);

        LevelMap levelMap = new LevelMap(checkerboard, 800, 600);

        // Assert that the absolute coordinates are correct
        assertEquals(Pair.of(377, 277), levelMap.getAbsoluteCoordinates(0, 0));
        assertEquals(Pair.of(400, 277), levelMap.getAbsoluteCoordinates(1, 0));
        assertEquals(Pair.of(377, 300), levelMap.getAbsoluteCoordinates(0, 1));
        assertEquals(Pair.of(400, 300), levelMap.getAbsoluteCoordinates(1, 1));
    }

    @Test
    void testGetSquareCoordinates() {
        // Create a 2x2 checkerboard grid
        List<List<Square>> checkerboard = new ArrayList<>();
        List<Square> row1 = new ArrayList<>();
        row1.add(new Square("texture1", null)); // (0,0)
        row1.add(new Square("texture2", null)); // (1,0)

        List<Square> row2 = new ArrayList<>();
        row2.add(new Square("texture3", null)); // (0,1)
        row2.add(new Square("texture4", null)); // (1,1)

        checkerboard.add(row1);
        checkerboard.add(row2);

        LevelMap levelMap = new LevelMap(checkerboard, 800, 600);

        // Assert that the square coordinates are correct
        assertEquals(Pair.of(0, 0), levelMap.getSquareCoordinates(380, 280));
        assertEquals(Pair.of(1, 0), levelMap.getSquareCoordinates(405, 277));
        assertEquals(Pair.of(0, 1), levelMap.getSquareCoordinates(380, 306));
        assertEquals(Pair.of(1, 1), levelMap.getSquareCoordinates(410, 310));
    }

}
