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

    public static final int HITBOX_WIDTH = Math.round(Constants.TEXTURE_SIZE * Constants.SCALE * 0.75f);
    public static final int HITBOX_HEIGHT = Math.round(Constants.TEXTURE_SIZE * Constants.SCALE * 1.0f);

}
