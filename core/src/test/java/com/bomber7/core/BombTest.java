package com.bomber7.core;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for the Bomb class to verify its behavior.
 * This test class uses this map to ensure the functionalities of our class:
 *
 *          U U B . .
 *          . U B . .
 *          . . B B U
 *          . . . . B
 *          . . U . .
 *
 *  U = UnbreakableWall, B = BreakableWall, . = Empty
 *
 */
public class BombTest {

    /**
     * Instance of LevelMap used as a shared resource for tests.
     */
    protected LevelMap levelMap;

    /**
     * Set up a larger LevelMap and shared resources
     * before each test case.
     */
    @BeforeEach
    void setUp() {
        this.levelMap = createLargeLevelMap();
    }

    /**
     * Verifies that a Bomb explodes correctly within the LevelMap.
     * A bomb should affect nearby BreakableWall objects but stops at
     * UnbreakableWalls objects.
     */
    @Test
    void testBombExplosionWithWalls() {
        // Create a Bomb at (x : 2, y : 2) with explosion power 3
        Bomb bomb = new TimeBomb(3, 2, 2);

        // Place the bomb in the map
        Square bombSquare = levelMap.getSquare(2, 2);
        bombSquare.setMapElement(bomb);

        // Activate the bomb
        bomb.activateBomb(levelMap);

        // Testing explosions on a map which contains both BreakableWalls and UnbreakablesWalls.

        // The bomb should be cleared after the explosion
        assertNull(levelMap.getSquare(2, 2).getMapElement());

        // BreakableWall at these positions should be destroyed.
        assertNull(levelMap.getSquare(2, 1).getMapElement());
        assertNull(levelMap.getSquare(3, 2).getMapElement());

        // UnbreakableWall at these positions should not be affected
        assertTrue(levelMap.getSquare(1, 1).getMapElement() instanceof UnbreakableWall);
        assertTrue(levelMap.getSquare(2, 4).getMapElement() instanceof UnbreakableWall);


        // Walls placed outside the explosion range
        assertNull(levelMap.getSquare(0, 2).getMapElement()); // Empty Square
        assertTrue(levelMap.getSquare(4, 3).getMapElement() instanceof BreakableWall); // Just behind another bloc
        assertTrue(levelMap.getSquare(2, 0).getMapElement() instanceof BreakableWall);
    }

    /**
     *  Create a larger LevelMap for complex test scenarios.
     *    U U B . .
     *    . U B . .
     *    . . B B U
     *    . . . . B
     *    . . U . .
     *
     *  U = UnbreakableWall, B = BreakableWall, . = Empty
     * @return a fully initialized LevelMap based on the predefined layout.
     */
    protected LevelMap createLargeLevelMap() {
        List<List<Square>> grid = new ArrayList<>();
        // Row 0
        List<Square> row0 = new ArrayList<>();
        row0.add(new Square("texture1",
            new UnbreakableWall("unbreakable")));
        row0.add(new Square("texture1",
            new UnbreakableWall("unbreakable")));
        row0.add(new Square("texture2",
            new BreakableWall("breakable")));
        row0.add(new Square("empty"));
        row0.add(new Square("empty"));
        grid.add(row0);

        // Row 1
        List<Square> row1 = new ArrayList<>();
        row1.add(new Square("empty"));
        row1.add(new Square("texture1",
            new UnbreakableWall("unbreakable")));
        row1.add(new Square("texture2",
            new BreakableWall("breakable")));
        row1.add(new Square("empty"));
        row1.add(new Square("empty"));
        grid.add(row1);

        // Row 2
        List<Square> row2 = new ArrayList<>();
        row2.add(new Square("empty"));
        row2.add(new Square("empty"));
        row2.add(new Square("texture2",
            new BreakableWall("breakable")));
        row2.add(new Square("texture2",
            new BreakableWall("breakable")));
        row2.add(new Square("texture1",
            new UnbreakableWall("unbreakable")));
        grid.add(row2);

        // Row 3
        List<Square> row3 = new ArrayList<>();
        row3.add(new Square("empty"));
        row3.add(new Square("empty"));
        row3.add(new Square("empty"));
        row3.add(new Square("empty"));
        row3.add(new Square("texture2",
            new BreakableWall("breakable")));
        grid.add(row3);

        // Row 4
        List<Square> row4 = new ArrayList<>();
        row4.add(new Square("empty"));
        row4.add(new Square("empty"));
        row4.add(new Square("texture1",
            new UnbreakableWall("unbreakable")));
        row4.add(new Square("empty"));
        row4.add(new Square("empty"));
        grid.add(row4);

        return new LevelMap(grid);
    }

    /**
     * Tests the expected behavior in situations where a Bomb
     * interaction could result in a NullPointerException.
     */
    @Test
    void testNullPointerException() {
        this.levelMap = null;
        Bomb bomb = new TimeBomb(2, -1, 0);
        assertThrows(NullPointerException.class, () -> bomb.activateBomb(this.levelMap));
    }
}
