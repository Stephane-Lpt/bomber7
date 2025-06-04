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
        assertTrue(square.getMapElement() instanceof TimeBomb, "Expected a TimeBomb on the square");
        assertEquals(0, player.getNbBomb(), "Expected nbBomb to decrease by 1 after dropping a bomb");
        assertEquals(player.getNbDroppedBomb(), 1, "Expected nbDroppedBomb to be 1 after dropping a bomb");
    }

    @Test
    void dropTriggerBombWhenNbBombIsGreaterThan1OK() {
        player.setTypeBomb(BombType.TRIGGER);
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1 and type is TRIGGER");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertTrue(square.getMapElement() instanceof TriggerBomb, "Expected a TriggerBomb on the square");
        assertEquals(0, player.getNbBomb(), "Expected nbBomb to decrease by 1 after dropping a bomb");
        assertEquals(player.getNbDroppedBomb(), 1, "Expected nbDroppedBomb to be 1 after dropping a bomb");

    }
}
