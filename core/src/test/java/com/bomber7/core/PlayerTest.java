package com.bomber7.core;

import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /**
     * Instance of Player used for testing (reset before each test).
     */
    private Player player;

    /**
     * Instance of LevelMap (map = foy) used for testing (reset before each test).
     */
    private LevelMap foyLevelMap;

    @BeforeEach
    void setUp() {
        this.foyLevelMap = LevelMapFactory.createLevelMap("foy");

        // Create a concrete subclass of Player for testing
        player = new Player("TestPlayer", this.foyLevelMap, 1, 1, 3, 1, "sprite.png") {
        };
        player.setTypeBomb(BombType.TIME);
        player.setNbBomb(1);
    }

    @Test
    void dropTimeBombWhenNbBombIsLessThanOrEqualTo1FAIL() {
        player.setNbBomb(0); // should fail
        boolean result = player.dropBomb();
        assertFalse(result, "Expected dropBomb to return false when nbBomb < 1");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertNull(square.getMapElement(), "Expected no Bomb to be placed when dropBomb fails");
    }

    @Test
    void dropTimeBombWhenNbBombIsGreaterThan1OK() {
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertInstanceOf(TimeBomb.class, square.getMapElement(), "Expected a TimeBomb on the square");
        assertEquals(0, player.getNbBomb(), "Expected nbBomb to decrease by 1 after dropping a bomb");
        assertEquals(
            0,
            player.getNbTriggeredBombDropped(),
            "Expected nbTriggeredBombDropped to be 0 "
                +
                "because dropping a TimeBomb instead of TriggerBomb");
    }

    @Test
    void dropTriggerBombWhenNbBombIsGreaterThan1OK() {
        player.setTypeBomb(BombType.TRIGGER);
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1 and type is TRIGGER");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertInstanceOf(TriggerBomb.class, square.getMapElement(), "Expected a TriggerBomb on the square");
        assertEquals(0, player.getNbBomb(), "Expected nbBomb to decrease by 1 after dropping a bomb");
        assertEquals(1, player.getNbTriggeredBombDropped(), "Expected nbDroppedBomb to be 1 after dropping a bomb");

    }

    @Test
    void activateAllTriggerBombsActivatesAndClearsList() {
        player.setTypeBomb(BombType.TRIGGER);
        player.setNbBomb(3);

        // Drop 2 trigger bombs
        player.moveUp(); // Going to (1,2) // TODO: PROBLEM - MoveUp go Down
        player.dropBomb(); // Drop at (1,2)
        player.moveDown(); // Going back to (1,1)
        player.moveRight();
        player.dropBomb(); // Drop at (2,1)
        System.out.println(player.getPower());

        assertEquals(2, player.getNbTriggeredBombDropped(), "Should have 2 TriggerBombs before activation");


        // Check squares before activation
        Square square1 = this.foyLevelMap.getSquare(1, 2);
        Square square2 = this.foyLevelMap.getSquare(2, 1);
        Square square3 = this.foyLevelMap.getSquare(3, 1);
        Square square4 = this.foyLevelMap.getSquare(1, 3);
        assertInstanceOf(TriggerBomb.class, square1.getMapElement(), "Expected TriggerBomb on (1,2)");
        assertInstanceOf(TriggerBomb.class, square2.getMapElement(), "Expected TriggerBomb on (2,1)");
        assertInstanceOf(BreakableWall.class, square3.getMapElement(), "Expected BreakableWall on (3,1)");
        assertInstanceOf(BreakableWall.class, square4.getMapElement(), "Expected BreakableWall on (1,3)");

        // Activate all trigger bombs
        player.activateAllTriggerBombs();

        square3 = this.foyLevelMap.getSquare(3, 1);

        // TriggerBombs should be activated
        assertEquals(0, player.getNbTriggeredBombDropped(), "Trigger bomb list should be empty after activation");
        assertNull(square1.getMapElement(), "Expected square (1,1) to be cleared after explosion");
        assertNull(square2.getMapElement(), "Expected square (2,1) to be cleared after explosion");
        assertNull(square3.getMapElement(), "Expected null because wall should be broken on (3,1)");
        assertNull(square4.getMapElement(), "Expected null because wall should be broken on (1,3)");
    }

}
