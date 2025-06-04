package com.bomber7.core;

import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.BombType;
import com.bomber7.core.model.square.Square;
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
        player.setNbBomb(2);
    }

    @Test
    void dropBombWhenNbBombIsGreaterThan1OK() {
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertTrue(square.getMapElement() instanceof Bomb, "Expected a Bomb to be placed on the square");
    }

    @Test
    void dropBombWhenNbBombIsLessThanOrEqualTo1FAIL() {
        player.setNbBomb(1); // should fail
        boolean result = player.dropBomb();
        assertFalse(result, "Expected dropBomb to return false when nbBomb <= 1");

        Square square = this.foyLevelMap.getSquare(1, 1);
        assertNull(square.getMapElement(), "Expected no Bomb to be placed when dropBomb fails");
    }
}
