package com.bomber7.core;

import com.bomber7.core.model.square.Bonus;
import com.bomber7.core.model.square.BonusLife;
import com.bomber7.core.model.square.BonusSpeed;
import com.bomber7.core.model.square.BonusTriggerBomb;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Constants.BONUS_TYPE;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for bonus dropping functionality in BreakableWall.
 * Tests verify that bonuses are dropped according to the configured probabilities.
 */
public class BonusTest {

    /**
     * Tests that BreakableWall.onDestruction() returns a valid bonus type
     * when a bonus is dropped.
     */
    @Test
    void testBreakableWallDropsValidBonusTypes() {
        // Create multiple breakable walls and test their destruction
        for (int i = 0; i < 50; i++) {
            BreakableWall wall = new BreakableWall("test_wall_texture");
            Bonus droppedBonus = wall.onDestruction();
            
            // If a bonus is dropped, it should be one of the valid types
            if (droppedBonus != null) {
                assertTrue(droppedBonus instanceof BonusTriggerBomb || 
                          droppedBonus instanceof BonusLife || 
                          droppedBonus instanceof BonusSpeed,
                          "Dropped bonus should be of a valid type");
            }
        }
    }

    /**
     * Tests that bonus dropping respects the configured BONUS_RATE.
     * This test runs multiple times to verify statistical behavior.
     */
    @RepeatedTest(10)
    void testBonusDropRate() {
        int totalWalls = 1000;
        int bonusesDropped = 0;
        
        // Create and destroy many walls to test the drop rate
        for (int i = 0; i < totalWalls; i++) {
            BreakableWall wall = new BreakableWall("test_wall_texture");
            Bonus droppedBonus = wall.onDestruction();
            
            if (droppedBonus != null) {
                bonusesDropped++;
            }

        }
        
        // Calculate the actual drop rate
        double actualDropRate = (double) bonusesDropped / totalWalls;
        double expectedDropRate = Constants.BONUS_RATE;
        
        // Allow for some statistical variance (10% tolerance)
        double tolerance = 0.1;
        assertTrue(Math.abs(actualDropRate - expectedDropRate) <= tolerance,
                   String.format("Actual drop rate (%.3f) should be close to expected rate (%.3f) within tolerance (%.1f) bonusesDropped: %d, totalWalls: %d", 
                                actualDropRate, expectedDropRate, tolerance, bonusesDropped, totalWalls));
    }

    /**
     * Tests that the distribution of bonus types follows the configured probabilities.
     * This test verifies that each bonus type appears with roughly the expected frequency.
     */
    @Test
    void testBonusTypeDistribution() {
        int totalTests = 2000;
        Map<Class<? extends Bonus>, Integer> bonusCount = new HashMap<>();
        bonusCount.put(BonusTriggerBomb.class, 0);
        bonusCount.put(BonusLife.class, 0);
        bonusCount.put(BonusSpeed.class, 0);
        
        // Collect bonus drops from many walls
        for (int i = 0; i < totalTests; i++) {
            BreakableWall wall = new BreakableWall("test_wall_texture");
            Bonus droppedBonus = wall.onDestruction();
            
            if (droppedBonus != null) {
                bonusCount.put(droppedBonus.getClass(), 
                              bonusCount.get(droppedBonus.getClass()) + 1);
            }
        }
        
        // Calculate total bonuses dropped
        int totalBonuses = bonusCount.values().stream().mapToInt(Integer::intValue).sum();
        
        // Verify each bonus type appears with roughly the expected frequency
        for (Map.Entry<Constants.BONUS_TYPE, Double> entry : Constants.BONUS_PROBABILITIES.entrySet()) {
            Class<? extends Bonus> bonusClass = getBonusClassFromType(entry.getKey());
            double expectedFrequency = entry.getValue();
            double actualFrequency = (double) bonusCount.get(bonusClass) / totalBonuses;
            
            // Allow for statistical variance (10% tolerance for distribution)
            double tolerance = 0.10;
            assertTrue(Math.abs(actualFrequency - expectedFrequency) <= tolerance,
                       String.format("Bonus type %s: actual frequency (%.3f) should be close to expected (%.3f) within tolerance (%.1f)",
                                    entry.getKey(), actualFrequency, expectedFrequency, tolerance));
        }
    }

    /**
     * Helper method to convert BONUS_TYPE enum to corresponding Bonus class.
     */
    private Class<? extends Bonus> getBonusClassFromType(Constants.BONUS_TYPE bonusType) {
        switch (bonusType) {
            case TRIGGER_BOMB:
                return BonusTriggerBomb.class;
            case LIFE:
                return BonusLife.class;
            case SPEED:
                return BonusSpeed.class;
            default:
                throw new IllegalArgumentException("Unknown bonus type: " + bonusType);
        }
    }
}