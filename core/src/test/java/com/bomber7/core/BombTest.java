package com.bomber7.core;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        Bomb bomb = new Bomb(3, 2, 2, Paths.get("assets/textures/bomb.png"), 101, false, false, false);

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
        row0.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row0.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row0.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row0.add(new Square(Paths.get("assets/empty.png"), 103));
        row0.add(new Square(Paths.get("assets/empty.png"), 103));
        grid.add(row0);

        // Row 1
        List<Square> row1 = new ArrayList<>();
        row1.add(new Square(Paths.get("assets/empty.png"), 103));
        row1.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row1.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row1.add(new Square(Paths.get("assets/empty.png"), 103));
        row1.add(new Square(Paths.get("assets/empty.png"), 103));
        grid.add(row1);

        // Row 2
        List<Square> row2 = new ArrayList<>();
        row2.add(new Square(Paths.get("assets/empty.png"), 103));
        row2.add(new Square(Paths.get("assets/empty.png"), 103));
        row2.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row2.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row2.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        grid.add(row2);

        // Row 3
        List<Square> row3 = new ArrayList<>();
        row3.add(new Square(Paths.get("assets/empty.png"), 103));
        row3.add(new Square(Paths.get("assets/empty.png"), 103));
        row3.add(new Square(Paths.get("assets/empty.png"), 103));
        row3.add(new Square(Paths.get("assets/empty.png"), 103));
        row3.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        grid.add(row3);

        // Row 4
        List<Square> row4 = new ArrayList<>();
        row4.add(new Square(Paths.get("assets/empty.png"), 103));
        row4.add(new Square(Paths.get("assets/empty.png"), 103));
        row4.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row4.add(new Square(Paths.get("assets/empty.png"), 103));
        row4.add(new Square(Paths.get("assets/empty.png"), 103));
        grid.add(row4);

        return new LevelMap(grid);
    }

    /**
     * Tests the expected behavior in situations where a Bomb
     * interaction could result in a NullPointerException
     */
    @Test
    void testNullPointerException() {
        this.levelMap = null;
        Bomb bomb = new Bomb(2,-1,0,Paths.get("path/to/texture"),1,false,false,false);
        assertThrows(NullPointerException.class, () -> bomb.activateBomb(this.levelMap));
    }
}
