package com.bomber7.core;

import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.TriggerBomb;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


/**
 * Unit test class for TriggerBomb to verify its behavior.
 * This class tests the ability of the bomb to be manually
 * triggered via the trigger method.
 */
public class TriggerBombTest extends BombTest {

    /**
     * Instance of TriggerBomb used for the test cases.
     */
    private TriggerBomb triggerBomb;

    /**
     * Sets up the test environnement before each test case.
     * This method initializes a LevelMap using BombTest.setUp().
     */
    @BeforeEach
    @Override
    void setUp() {
        super.setUp(); // Call BombTest's setup to initialize the map

        // Instantiate a TriggerBomb at postion (2,2)
        triggerBomb = Mockito.spy(new TriggerBomb(2, 2, 2, super.testCharacter));
        Mockito.doNothing().when(triggerBomb).playExplosionSound();
    }

    /**
     * Tests the trigger method activates the explosion correctly.
     */
    @Test
    void testTriggerBombActivation() {
        triggerBomb.trigger(levelMap);
        // Check that the affected squares match the expectations
        assertFalse(levelMap.getSquare(2, 2).getMapElement() instanceof BreakableWall);
        assertFalse(levelMap.getSquare(3, 2).getMapElement() instanceof BreakableWall);
        assertFalse(levelMap.getSquare(2, 1).getMapElement() instanceof BreakableWall);
        assertNotNull(levelMap.getSquare(0, 2)); // Stopped by unbreakable walls
    }

    /**
     * Tests that the trigger method throws a NullPointerException
     * when invoked with a null map.
     */
    @Test
    void testTriggerBombWithNullLevelMap() {
        assertThrows(NullPointerException.class, () -> triggerBomb.trigger(null));
    }
}
