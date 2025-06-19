package com.bomber7.utils;

import java.util.Map;

/**
 * Constant variables used in the app.
 */
public final class Constants {
    private Constants() { }

    /**
     * Scale factor for batch elements (map, characters, etc...).
     */
    public static final float SCALE = 0.85f;

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
     * Width of the hitbox of character.
     */
    public static final int HITBOX_WIDTH = Math.round(Constants.TEXTURE_SIZE * Constants.SCALE * 0.75f);
    /**
     * Height of the hitbox of character.
     */
    public static final int HITBOX_HEIGHT = Math.round(Constants.TEXTURE_SIZE * Constants.SCALE * 1.0f);

    /**
     * The default bomb timer.
     */
    public static final float BOMB_TIMER = 5.0f;

    /**
     * The default bomb timer.
     */
    public static final int DEFAULT_BOMB_POWER = 3;

    /**
     * Animation frame duration.
     */
    public static final float FRAME_DURATION = 0.1f;

    /**
     * Sound convert ratio to convert slider value to libgdx volume value.
     */
    public static final float VOLUME_CONVERT_RATIO = 100f;

    /**
     * The chance of a bonus being dropped.
     */
    public final static double BONUS_RATE = 0.25; // 25% chance to drop a bonus

    public static enum BONUS_TYPE {
        TRIGGER_BOMB,
        LIFE,
        SPEED,
        ADD_BOMB
    }
    /**
     * A map of the probabilities of each bonus of being dropped.
     * All probabilities add up to one.
     */
    public static final Map<BONUS_TYPE, Double> BONUS_PROBABILITIES = Map.of(
        BONUS_TYPE.TRIGGER_BOMB, 0.1,
        BONUS_TYPE.LIFE, 3.0,
        BONUS_TYPE.SPEED, 3.0,
        BONUS_TYPE.ADD_BOMB, 3.0
    );
}
