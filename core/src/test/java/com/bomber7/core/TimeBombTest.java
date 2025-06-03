package com.bomber7.core;

import com.bomber7.core.model.square.TimeBomb;

import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test class for TimeBomb to verify its behavior.
 * This class tests the countdown timer of TimeBomb and ensures
 * that it activates correctly when the timer reaches zero.
 */
public class TimeBombTest extends BombTest {

    /**
     * Instance of TimeBomb.
     */
    private TimeBomb timeBomb;

    /**
     * This method initializes a LevelMap using BombTest.setUp().
     */
    @BeforeEach
    @Override
    void setUp() {
        super.setUp(); // initialize the map

        // Instantiate a TimeBomb at position (2, 3) with a timer of 5 sec
        timeBomb = new TimeBomb(2, 2, 3,
            Paths.get("textures/timebomb.png"),
            2,
            5.0f);
    }

    /**
     * Verifies that the countdown timer decreases correctly and the bomb
     * activates when the timer reaches zero.
     */
    @Test
    void testTimeBombCountdownAndActivation() {
        // Simulate a tick with 3 seconds passed
        timeBomb.tick(levelMap, 3.0f);

        // Ensure the timer is updated correctly
        assertEquals(2.0f, timeBomb.getTimer(), 0.01f);

        // Another tick
        timeBomb.tick(levelMap, 2.0f);

        // Ensure the TimeBomb has activated and the square is cleared
        assertNull(levelMap.getSquare(2, 3).getMapElement());
    }

    /**
     * Tests that the timer doesn't become negative after activation.
     */
    @Test
    void testTimeBombTimerNonNegative() {
        // Simulate a tick with 6 seconds passed (+ than the timer)
        timeBomb.tick(levelMap, 6.0f);

        // Ensure the timer is 0
        assertEquals(0.0f, timeBomb.getTimer(), 0.01f);
    }

    /**
     * Tests that the tick method throws a NullPointerException
     * when invoked with a null map.
     */
    @Test
    void testTimeBombWithNullLevelMap() {
        assertThrows(NullPointerException.class, () -> timeBomb.tick(null, 1.0f));
    }
}
