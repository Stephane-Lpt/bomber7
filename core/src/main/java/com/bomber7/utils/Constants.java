package com.bomber7.utils;

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
     * Minimum number of players allowed in the game.
     */
    public static final int MIN_PLAYERS = 2;
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
    public static final float BOMB_TIMER = 2.5f;

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
     * The chance of a bonus being dropped once a breakable wall is destroyed.
     */
    public static final double BONUS_RATE = 0.25;

    /**
     * Interval (in seconds) at which alive players are awarded points for staying alive.
     */
    public static final float ALIVE_SCORE_TIMER = 10f;

    /**
     * Minimum player score.
     */
    public static final int MIN_PLAYER_SCORE = 0;

    /** The default life of a player. */
    public static final int DEFAULT_LIFE = 1;

    /** The default speed of a player. */
    public static final int DEFAULT_SPEED = 1;

}
