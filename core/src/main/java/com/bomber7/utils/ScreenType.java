package com.bomber7.utils;

import com.badlogic.gdx.Game;
import com.bomber7.core.screens.BomberScreen;
import com.bomber7.core.screens.GameScreen;
import com.bomber7.core.screens.MainMenuScreen;
import com.bomber7.core.screens.PlayerSelectionScreen;
import com.bomber7.core.screens.MapSelectionScreen;
import com.bomber7.core.screens.PauseScreen;
import com.bomber7.core.screens.SettingsScreen;
import com.bomber7.core.screens.KeyBindingScreen;

/**
 * Represents the various screens or states of the game user interface.
 * Each enum constant corresponds to a specific screen displayed during gameplay or setup.
 */
public enum ScreenType {

    /**
     * The main menu screen, typically the first screen shown to the user.
     */
    MAIN_MENU {
        /**
         * Creates and returns the main menu screen instance.
         *
         * @param game the current game instance
         * @return a new {@link MainMenuScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new MainMenuScreen(game);
        }
    },

    /**
     * The player setup screen where users can configure player settings.
     */
    PLAYER_SETUP {
        /**
         * Creates and returns the player setup screen instance.
         *
         * @param game the current game instance
         * @return a new {@link PlayerSelectionScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new PlayerSelectionScreen(game);
        }
    },

    /**
     * The map selection screen where users choose the game map.
     */
    MAP_SELECTION {
        /**
         * Creates and returns the map selection screen instance.
         *
         * @param game the current game instance
         * @return a new {@link MapSelectionScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new MapSelectionScreen(game);
        }
    },

    /**
     * The main game screen where gameplay takes place.
     */
    GAME {
        /**
         * Creates and returns the main gameplay screen instance.
         *
         * @param game the current game instance
         * @return a new {@link GameScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new GameScreen(game);
        }
    },

    /**
     * The pause screen that appears when the game is paused.
     */
    PAUSE {
        /**
         * Creates and returns the pause screen instance.
         *
         * @param game the current game instance
         * @return a new {@link PauseScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new PauseScreen(game);
        }
    },

    /**
     * The settings screen where users can adjust game options.
     */
    SETTINGS {
        /**
         * Creates and returns the settings screen instance.
         *
         * @param game the current game instance
         * @return a new {@link SettingsScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new SettingsScreen(game);
        }
    },

    /**
     * The key binding screen where users can configure input controls.
     */
    KEY_BINDING {
        /**
         * Creates and returns the key binding configuration screen instance.
         *
         * @param game the current game instance
         * @return a new {@link KeyBindingScreen} instance
         */
        public BomberScreen getScreen(Game game) {
            return new KeyBindingScreen(game);
        }
    };

    /**
     * Creates and returns the screen instance associated with this screen type.
     *
     * @param game the current game instance
     * @return the corresponding {@link BomberScreen} for this screen type
     */
    public abstract BomberScreen getScreen(Game game);
}
