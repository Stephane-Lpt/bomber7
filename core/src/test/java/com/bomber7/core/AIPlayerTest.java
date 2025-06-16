package com.bomber7.core;

import com.bomber7.core.model.entities.AIPlayer;
import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.utils.GameCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AIPlayer.
 */
class AIPlayerTest {

    private AIPlayer aiPlayer;
    private LevelMap levelMap;
    private Player[] players;
    private GameCharacter gameCharacter = GameCharacter.STUDENT;

    /**
     * Sets up the AIPlayer and LevelMap before each test.
     */
    @BeforeEach
    void setUp() {
        levelMap = LevelMapFactory.createLevelMap("foy", 800, 600);
        aiPlayer = new AIPlayer(levelMap, 1, 1, gameCharacter);
        players = new Player[] {
            new Player("Player2", levelMap, 0, 34, 3, 1, gameCharacter) {},
            new Player("Player1", levelMap, 0, 24, 3, 1, gameCharacter) {}
        };
    }

    /**
     * Tests the AIPlayer's movement towards the nearest player.
     * The AI should move towards the nearest player, which is Player1 at (0,24).
     */
    @Test
    void testMoveTowardsNearestPlayer() {

        int initialMapX = aiPlayer.getMapX();
        assertEquals(1, initialMapX, "Initial map X position should be 1, not " + initialMapX);
        int initialMapY = aiPlayer.getMapY();
        assertEquals(1, initialMapY, "Initial map Y position should be 1, not " + initialMapY);

        assertTrue(aiPlayer.getMapX() == 1 && aiPlayer.getMapY() == 1,
            "Start position should be (1,1) before moving. Actual position: ("
            + aiPlayer.getMapX() + ", " + aiPlayer.getMapY() + ")");

        aiPlayer.moveAI(players);

        assertTrue(aiPlayer.getMapX() == 1 && aiPlayer.getMapY() == 2,
            "AI should move towards Player1 at (0,23). AI player should be at (1,2)."
            + " Actual position: (" + aiPlayer.getMapX() + ", " + aiPlayer.getMapY() + ")");

    }

    /**
     * Tests the AIPlayer's movement when there are no players.
     * The AI should move randomly when no players are present.
     */
    @Test
    void testMoveRandomlyWhenNoPlayers() {
        Player[] noPlayers = new Player[0];
        int initialX = aiPlayer.getMapX();
        int initialY = aiPlayer.getMapY();
        aiPlayer.moveAI(noPlayers);
        assertTrue(initialX != aiPlayer.getMapX() || initialY != aiPlayer.getMapY(),
            "AI should move randomly when no players are present");
    }

    /**
     * Tests the AIPlayer's ability to find the nearest player.
     * The AI should find Player2 as the nearest player.
     */
    @Test
    void testFindNearestPlayer() {
        Player nearestPlayer = players[1];
        Player foundPlayer = aiPlayer.findNearestPlayer(players);
        assertEquals(nearestPlayer, foundPlayer,
        "The nearest player should be Player1 at (0,24), and not Player2 at (0,34)");
    }

    @Test
    void testMoveTowardsNearestPlayerAndTick() {
        int initialMapX = aiPlayer.getMapX();
        assertEquals(1, initialMapX, "Initial map X position should be 1, not " + initialMapX);
        int initialMapY = aiPlayer.getMapY();
        assertEquals(1, initialMapY, "Initial map Y position should be 1, not " + initialMapY);

        assertTrue(aiPlayer.getMapX() == 1 && aiPlayer.getMapY() == 1,
            "Start position should be (1,1) before moving. Actual position: ("
                + aiPlayer.getMapX() + ", " + aiPlayer.getMapY() + ")");

        System.out.println("Player coord: " + aiPlayer.getMapX() + ", " + aiPlayer.getMapY());

        int xBeforeTryPlacingBomb = aiPlayer.getMapX();
        int yBeforeTryPlacingBomb = aiPlayer.getMapY();

        assertFalse(aiPlayer.hasSafeSpot());
        aiPlayer.tick(1.0f); // Simulate a tick with a delta time of 1 seconds
        System.out.println("Player coord: " + aiPlayer.getMapX() + ", " + aiPlayer.getMapY());
        assertFalse(levelMap.getSquare(xBeforeTryPlacingBomb, yBeforeTryPlacingBomb).getMapElement() instanceof TimeBomb);

        aiPlayer.moveAI(players);

        assertTrue(aiPlayer.hasSafeSpot());

        System.out.println("Player coord: " + aiPlayer.getMapX() + ", " + aiPlayer.getMapY());


        assertTrue(aiPlayer.getMapX() == 1 && aiPlayer.getMapY() == 2,
            "AI should move towards Player1 at (0,23). AI player should be at (1,2)."
                + " Actual position: (" + aiPlayer.getMapX() + ", " + aiPlayer.getMapY() + ")");

        xBeforeTryPlacingBomb = aiPlayer.getMapX();
        yBeforeTryPlacingBomb = aiPlayer.getMapY();

        aiPlayer.tick(1.0f); // Simulate a tick with a delta time of 1 seconds
        System.out.println("Player coord: " + aiPlayer.getMapX() + ", " + aiPlayer.getMapY());

        assertTrue(levelMap.getSquare(xBeforeTryPlacingBomb, yBeforeTryPlacingBomb).getMapElement() instanceof TimeBomb);

        assertEquals(2, aiPlayer.getMapX());
        assertEquals(1, aiPlayer.getMapY());

        System.out.println("Player coord: " + aiPlayer.getMapX() + ", " + aiPlayer.getMapY());
    }

}
