package com.bomber7.utils;

import java.util.Map;

/**
 * Constant variables used in the app.
 */
public final class Constants {
    private Constants() { }

    /**
     * The maximum length for a player name.
     */
    public static final int MAX_PLAYER_NAME_LENGTH = 12;
    /**
     * Maximum number of players allowed in the game.
     */
    public static final int MIN_PLAYERS = 1;
    /**
     * Maximum number of human players allowed in the game.
     */
    public static final int MAX_PLAYERS = 4;
    /**
     * Maximum number of rounds allowed in a game.
     */
    public static final int MIN_ROUNDS = 1;
    /**
     * Maximum number of rounds allowed in a game.
     */
    public static final int MAX_ROUNDS = 10;
    /**
     * The size in pixels of map textures.
     */
    public static final int TEXTURE_SIZE = 32;

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
