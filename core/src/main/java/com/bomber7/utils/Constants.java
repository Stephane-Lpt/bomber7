package com.bomber7.utils;

import java.util.Map;

/**
 * Constant variables used in the app.
 */
public final class Constants {

    /**
     * The chance of a bonus being dropped.
     */
    public final static double BONUS_RATE = 0.25; // 25% chance to drop a bonus

    
    public static enum BONUS_TYPE {
        TRIGGER_BOMB,
        LIFE,
        SPEED
    }
    /**
     * A map of the probabilities of each bonus of being dropped.
     * All probabilities add up to one.
     */
    public static final Map<BONUS_TYPE, Double> BONUS_PROBABILITIES = Map.of(
        BONUS_TYPE.TRIGGER_BOMB, 0.2,
        BONUS_TYPE.LIFE, 0.4,
        BONUS_TYPE.SPEED, 0.4
    );
}
