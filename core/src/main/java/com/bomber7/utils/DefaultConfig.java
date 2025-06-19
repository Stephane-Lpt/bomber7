package com.bomber7.utils;

import com.badlogic.gdx.Input;

import java.util.EnumMap;
import java.util.Map;

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
     * Default key bindings for Player 1 (ZQSD).
     */
    private static final Map<Controls, Integer> CONTROLS_PLAYER_1 = new EnumMap(Controls.class) {{
        put(Controls.UP, Input.Keys.Z);
        put(Controls.DOWN, Input.Keys.S);
        put(Controls.LEFT, Input.Keys.Q);
        put(Controls.RIGHT, Input.Keys.D);
        put(Controls.DROP_BOMB, Input.Keys.E);
        put(Controls.ACTIVATE_BOMB, Input.Keys.A);
    }};

    /**
     * Default key bindings for Player 2 (Arrows).
     */
    private static final Map<Controls, Integer> CONTROLS_PLAYER_2 = new EnumMap(Controls.class) {{
        put(Controls.UP, Input.Keys.UP);
        put(Controls.DOWN, Input.Keys.DOWN);
        put(Controls.LEFT, Input.Keys.LEFT);
        put(Controls.RIGHT, Input.Keys.RIGHT);
        put(Controls.DROP_BOMB, Input.Keys.ENTER);
        put(Controls.ACTIVATE_BOMB, Input.Keys.CONTROL_RIGHT);
    }};

    /**
     * Default key bindings for Player 3 (IJKL).
     */
    private static final Map<Controls, Integer> CONTROLS_PLAYER_3 = new EnumMap(Controls.class) {{
        put(Controls.UP, Input.Keys.I);
        put(Controls.DOWN, Input.Keys.K);
        put(Controls.LEFT, Input.Keys.J);
        put(Controls.RIGHT, Input.Keys.L);
        put(Controls.DROP_BOMB, Input.Keys.O);
        put(Controls.ACTIVATE_BOMB, Input.Keys.U);
    }};

    /**
     * Default key bindings for Player 4 (Numpad).
     */
    private static final Map<Controls, Integer> CONTROLS_PLAYER_4 = new EnumMap(Controls.class) {{
        put(Controls.UP, Input.Keys.NUMPAD_8);
        put(Controls.DOWN, Input.Keys.NUMPAD_5);
        put(Controls.LEFT, Input.Keys.NUMPAD_4);
        put(Controls.RIGHT, Input.Keys.NUMPAD_6);
        put(Controls.DROP_BOMB, Input.Keys.NUMPAD_9);
        put(Controls.ACTIVATE_BOMB, Input.Keys.NUMPAD_7);
    }};

    /**
     * List of maps.
     */
    private static final Map<Controls, Integer>[] ALL_CONTROLS = new Map[] {
        CONTROLS_PLAYER_1,
        CONTROLS_PLAYER_2,
        CONTROLS_PLAYER_3,
        CONTROLS_PLAYER_4
    };

    /**
     * Returns the default key binding map for a given player.
     * @param playerIndex the index of the player to get the default key binding map for
     * @return key binding map of player {@code playerIndex}
     */
    public static Map<Controls, Integer> getControlsForPlayer(int playerIndex) {
        if (playerIndex > Constants.MAX_PLAYERS) {
            throw new IllegalArgumentException("Unknown player index: " + playerIndex);
        }
        return new EnumMap<>(ALL_CONTROLS[playerIndex]);
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private DefaultConfig() { }
}
