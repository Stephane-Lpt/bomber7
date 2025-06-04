package com.bomber7.core;

import com.bomber7.core.model.square.TriggerBomb;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;


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
        triggerBomb = new TriggerBomb(2, 2, 2,
            "bomb",
            1);
    }

    /**
     * Tests the trigger method activates the explosion correctly.
     */
    @Test
    void testTriggerBombActivation() {
        triggerBomb.trigger(levelMap);
        // Check that the affected squares match the expectations
        assertNull(levelMap.getSquare(2, 2).getMapElement());
        assertNull(levelMap.getSquare(3, 2).getMapElement());
        assertNull(levelMap.getSquare(2, 1).getMapElement());
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
