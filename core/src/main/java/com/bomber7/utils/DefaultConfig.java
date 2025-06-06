package com.bomber7.utils;

/**
 * A utility class that holds the default configuration values for the game.
 * <p>
 * These values are used to initialize a {@link Config} instance with sensible defaults
 * when no saved configuration is available, or when resetting to factory settings.
 * </p>
 */
public final class DefaultConfig {

    /**
     * The default value for the global volume setting (range: 0–100).
     */
    public static final int GLOBAL_VOLUME = 100;

    /**
     * The default value for the music volume setting (range: 0–100).
     */
    public static final int MUSIC_VOLUME = 100;

    /**
     * The default language setting for the game.
     */
    public static final Language LANGUAGE = Language.ENGLISH;

    /**
     * Private constructor to prevent instantiation.
     */
    private DefaultConfig() { }
}
