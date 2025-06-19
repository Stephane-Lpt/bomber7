package com.bomber7.core;

import com.bomber7.core.model.entities.Character;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.UnbreakableWall;
import com.bomber7.utils.GameCharacter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
     * Instance of Character used in our bomb test.
     */
    protected Character testCharacter;

    /** Game character. */
    private GameCharacter gameCharacter = GameCharacter.STUDENT;

    /**
     * Test class for Character.
     */
    private static class ConcreteCharacter extends Character {
        ConcreteCharacter(String name, LevelMap map, int x, int y, int life, int speed, GameCharacter gameCharacter) {
            super(name, map, x, y, life, speed, gameCharacter);
        }
    }

    /**
     * Set up a larger LevelMap and shared resources
     * before each test case.
     */
    @BeforeEach
    void setUp() {
        this.levelMap = createLargeLevelMap();
        this.testCharacter = new ConcreteCharacter("test", levelMap, 1, 2, 1, 1, gameCharacter);
    }

    /**
     * Verifies that a Bomb explodes correctly within the LevelMap.
     * A bomb should affect nearby BreakableWall objects but stops at
     * UnbreakableWalls objects.
     */
    @Test
    void testBombExplosionWithWalls() {
        TimeBomb bomb = Mockito.spy(new TimeBomb(3, 2, 2, testCharacter));

        // Quand playSong est appelée, ne rien faire (override)
        Mockito.doNothing().when(bomb).playSong();

        // Place the bomb in the map
        Square bombSquare = levelMap.getSquare(2, 2);
        bombSquare.setMapElement(bomb);

        // Place the characters
        Character charInRange = new ConcreteCharacter("CharInRange", levelMap, 1, 2, 1, 1, gameCharacter);
        levelMap.addCharacter(charInRange);

        Character charOutOfRange = new ConcreteCharacter("CharOutOfRange", levelMap, 3, 4, 1, 1, gameCharacter);
        levelMap.addCharacter(charOutOfRange);

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

        // Characters
        assertFalse(charInRange.isAlive(), "The character within range should be dead");
        assertTrue(charOutOfRange.isAlive(), "The character outside range should not be affected");
    }

    /**
     * Verified that a bomb explode other Bombs in range.
     * @return
     */
    @Test
    void testBombExplosionWithOtherBombs() {

        Bomb bomb = Mockito.spy(new TimeBomb(3, 4, 1, testCharacter));

        // Quand playSong est appelée, ne rien faire (override)
        Mockito.doNothing().when(bomb).playSong();

        // Place the bomb in the map
        Square bombSquare = levelMap.getSquare(4, 1);
        bombSquare.setMapElement(bomb);

        // Create another Bomb at (x : 2, y : 1) with explosion power 1
        Bomb otherBomb = Mockito.spy(new TimeBomb(1, 3, 1, this.testCharacter));
        Mockito.doNothing().when(otherBomb).playSong();
        Square otherBombSquare = levelMap.getSquare(3, 1);
        otherBombSquare.setMapElement(otherBomb);

        // Activate the first bomb
        bomb.activateBomb(levelMap);

        // The first bomb should be cleared after the explosion
        assertNull(levelMap.getSquare(4, 1).getMapElement());

        // The second bomb should also be cleared after being exploded by the first one
        assertNull(levelMap.getSquare(3, 1).getMapElement());

        // check unbreakable walls in 4,2 still exists
        assertTrue(levelMap.getSquare(4, 2).getMapElement() instanceof UnbreakableWall,
            "Unbreakable wall at (4, 2) should still exist after the explosion");

        // Check that the breakable wall at (3, 2) is destroyed
        assertNull(levelMap.getSquare(3, 2).getMapElement(),
            "Breakable wall at (3, 2) should be destroyed after the explosion");
        // Check that the breakable wall at (2, 2) is destroyed
        assertNull(levelMap.getSquare(2, 1).getMapElement(),
            "Breakable wall at (2, 1) should be destroyed after the explosion");

        // Check that the breakable wall at (2, 2) is still exists
        assertTrue(levelMap.getSquare(2, 2).getMapElement() instanceof BreakableWall,
            "Breakable wall at (2, 2) should still exist after the explosion");

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

        return new LevelMap("enseeiht", grid, 800, 600);
    }

    /**
     * Tests the expected behavior in situations where a Bomb
     * interaction could result in a NullPointerException.
     */
    @Test
    void testNullPointerException() {
        this.levelMap = null;
        Bomb bomb = new TimeBomb(2, -1, 0, this.testCharacter);
        assertThrows(NullPointerException.class, () -> bomb.activateBomb(this.levelMap));
    }
}
