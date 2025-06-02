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
 * Test class for Bomb functionality, including scenarios with BreakableWall and UnbreakableWall.
 */
public class BombTest {

    /**
     * LevelMap used for the tests.
     */
    private LevelMap levelMap;

    /**
     * Set up a larger LevelMap and shared resources before each test.
     */
    @BeforeEach
    void setUp() {
        this.levelMap = createLargeLevelMap();
    }

    /**
     * Test that a bomb explodes correctly and affects nearby BreakableWalls but stops at UnbreakableWalls.
     */
    @Test
    void testBombExplosionWithWalls() {
        // Create a Bomb at position (2, 2) with explosion power 3
        Bomb bomb = new Bomb(
            2, 2, // x, y position
            3, // power
            Paths.get("assets/textures/bomb.png"),
            101, // texture ID
            false, false, false
        );

        // Place the bomb in the LevelMap
        Square bombSquare = levelMap.getSquare(2, 2);
        bombSquare.setMapElement(bomb);

        // Activate the bomb
        bomb.activateBomb(levelMap);

        // Assertions for affected squares
        // BreakableWall at (2, 3) should be destroyed
        assertFalse(levelMap.getSquare(2, 3).hasMapElement());

        // UnbreakableWall at (2, 4) should not be affected
        assertTrue(levelMap.getSquare(2, 4).hasMapElement());
        assertTrue(levelMap.getSquare(2, 4).getMapElement() instanceof UnbreakableWall);

        // BreakableWall at (3, 2) should be destroyed
        assertFalse(levelMap.getSquare(3, 2).hasMapElement());

        // BreakableWall at (4, 2) should not be destroyed, since it is outside the explosion range
        assertTrue(levelMap.getSquare(4, 2).hasMapElement());
        assertTrue(levelMap.getSquare(4, 2).getMapElement() instanceof BreakableWall);
    }

    /**
     * Helper method to create a large LevelMap for testing explosions.
     * The map contains both BreakableWalls and UnbreakableWalls in various configurations.
     * Example layout (5x5):
     *
     *    U U B . .
     *    . U B . .
     *    . . B B U
     *    . . . . B
     *    . . U . .
     *
     *  U = UnbreakableWall, B = BreakableWall, . = Empty
     */
    private LevelMap createLargeLevelMap() {
        List<List<Square>> grid = new ArrayList<>();

        // Row 0
        List<Square> row0 = new ArrayList<>();
        row0.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row0.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row0.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row0.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row0.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        grid.add(row0);

        // Row 1
        List<Square> row1 = new ArrayList<>();
        row1.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row1.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row1.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row1.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row1.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        grid.add(row1);

        // Row 2
        List<Square> row2 = new ArrayList<>();
        row2.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row2.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row2.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row2.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        row2.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        grid.add(row2);

        // Row 3
        List<Square> row3 = new ArrayList<>();
        row3.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row3.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row3.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row3.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row3.add(new Square(Paths.get("assets/texture2.png"), 102, new BreakableWall(Paths.get("assets/breakable.png"), 2)));
        grid.add(row3);

        // Row 4
        List<Square> row4 = new ArrayList<>();
        row4.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row4.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row4.add(new Square(Paths.get("assets/texture1.png"), 101, new UnbreakableWall(Paths.get("assets/unbreakable.png"), 1)));
        row4.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        row4.add(new Square(Paths.get("assets/empty.png"), 103)); // Empty square
        grid.add(row4);

        return new LevelMap(grid);
    }
}
