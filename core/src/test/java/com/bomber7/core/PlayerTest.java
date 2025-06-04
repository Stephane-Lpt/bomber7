package com.bomber7.core;

import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private LevelMap levelMap;

    @BeforeEach
    void setUp() {
        // Dummy map: 5x5 with empty squares
        this.levelMap = LevelMapFactory.createLevelMap("foy");

        // Create a concrete subclass of Player for testing
        player = new Player("TestPlayer", this.levelMap, 1, 1, 3, 1, "sprite.png") {
        };
        player.setTypeBomb(BombType.TIME);
        player.setNbBomb(2);
    }

    @Test
    void dropBomb_shouldSucceed_whenNbBombIsGreaterThan1() {
        boolean result = player.dropBomb();
        assertTrue(result, "Expected dropBomb to return true when nbBomb > 1");

        Square square = this.levelMap.getSquare(1, 1);
        assertTrue(square.getMapElement() instanceof Bomb, "Expected a Bomb to be placed on the square");
    }

    @Test
    void dropBomb_shouldFail_whenNbBombIsLessThanOrEqualTo1() {
        player.setNbBomb(1); // should fail
        boolean result = player.dropBomb();
        assertFalse(result, "Expected dropBomb to return false when nbBomb <= 1");

        Square square = this.levelMap.getSquare(1, 1);
        assertNull(square.getMapElement(), "Expected no Bomb to be placed when dropBomb fails");
    }
}
