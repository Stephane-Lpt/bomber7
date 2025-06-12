package com.bomber7.core;

import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.BombType;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.core.model.square.TriggerBomb;
import com.bomber7.core.model.square.UnbreakableWall;
import com.bomber7.utils.GameCharacter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerTest {

    /**
     * Instance of Player used for testing (reset before each test).
     */
    private Player player;
    /**
     * Instance of LevelMap (map = foy) used for testing (reset before each test).
     */
    private LevelMap foyLevelMap;
    /** Game character. */
    private GameCharacter gameCharacter = GameCharacter.STUDENT;

    @BeforeEach
    void setUp() {
        this.foyLevelMap = LevelMapFactory.createLevelMap("foy", 800, 600);

        // Create a concrete subclass of Player for testing
        player = new Player("TestPlayer", this.foyLevelMap, 1, 23, 3, 20, gameCharacter) {
        };
        player.setTypeBomb(BombType.TIME);
        player.setNbBomb(1);
    }

    @Test
    void dropTimeBombWhenNbBombIsLessThanOrEqualTo1FAIL() {
        player.setNbBomb(0); // should fail
        boolean result = player.dropBomb();
        assertFalse(result, "Expected dropBomb to return false when nbBomb < 1");

        Square square = this.foyLevelMap.getSquare(1, 23);
        assertNull(square.getMapElement(), "Expected no Bomb to be placed when dropBomb fails");
    }

    @Test
    void dropTimeBombWhenNbBombIsGreaterThan1OK() {
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1");

        Square square = this.foyLevelMap.getSquare(1, 23);
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

        Square square = this.foyLevelMap.getSquare(1, 23);
        assertInstanceOf(TriggerBomb.class, square.getMapElement(), "Expected a TriggerBomb on the square");
        assertEquals(0, player.getNbBomb(), "Expected nbBomb to decrease by 1 after dropping a bomb");
        assertEquals(1, player.getNbTriggeredBombDropped(), "Expected nbDroppedBomb to be 1 after dropping a bomb");

    }

    @Test
    void activateAllTriggerBombsActivatesAndClearsList() {
        player.setTypeBomb(BombType.TRIGGER);
        player.setNbBomb(3);

        // Drop 2 trigger bombs
        assertEquals(23, player.getMapY(), "Player should be at (1,23) after moving down");
        assertEquals(1, player.getMapX(), "Player should be at (1,23) after moving down");
        player.dropBomb(); // Drop at (1,23)
        player.moveDown(); // Going back to (1,22)
        assertEquals(22, player.getMapY(), "Player should be at (1,22) after moving down");
        assertEquals(1, player.getMapX(), "Player should be at (1,22) after moving down");
        player.dropBomb(); // Drop at (1,22)
        System.out.println(player.getPower());

        assertEquals(2, player.getNbTriggeredBombDropped(), "Should have 2 TriggerBombs before activation");


        // Check squares before activation
        Square square1 = this.foyLevelMap.getSquare(1, 22);
        Square square2 = this.foyLevelMap.getSquare(1, 23);
        Square square3 = this.foyLevelMap.getSquare(2, 24);
        Square square4 = this.foyLevelMap.getSquare(1, 21);
        assertInstanceOf(TriggerBomb.class, square2.getMapElement(), "Expected TriggerBomb on (1,23)");
        assertInstanceOf(TriggerBomb.class, square1.getMapElement(), "Expected TriggerBomb on (1,22)");
        assertInstanceOf(UnbreakableWall.class, square3.getMapElement(), "Expected UnbreakableWall on (2,24)");
        assertInstanceOf(BreakableWall.class, square4.getMapElement(), "Expected BreakableWall on (1,21)");

        // Activate all trigger bombs
        player.activateAllTriggerBombs();

        // TriggerBombs should be activated
        assertEquals(0, player.getNbTriggeredBombDropped(), "Trigger bomb list should be empty after activation");
        assertNull(square1.getMapElement(), "Expected square (1,24) to be cleared after explosion");
        assertNull(square2.getMapElement(), "Expected square (1,23) to be cleared after explosion");
        assertInstanceOf(UnbreakableWall.class, square3.getMapElement(), "Expected UnbreakableWall on (2,24)");
        assertNull(square4.getMapElement(), "Expected null because wall should be broken on (1,22)");
    }

}
