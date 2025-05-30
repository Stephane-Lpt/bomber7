package com.bomber7.utils;

import com.badlogic.gdx.Game;
import com.bomber7.core.screens.BomberScreen;
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
        public BomberScreen getScreen(Game game) {
            return new MainMenuScreen(game);
        }
    },

    /**
     * The player setup screen where users can configure player settings.
     */
    PLAYER_SETUP {
        public BomberScreen getScreen(Game game) {
            return new PlayerSelectionScreen(game);
        }
    },

    /**
     * The map selection screen where users choose the game map.
     */
    MAP_SELECTION {
        public BomberScreen getScreen(Game game) {
            return new MapSelectionScreen(game);
        }
    },

//    /**
//     * The main game screen where gameplay takes place.
//     */
//    GAME {
//        public BomberScreen getScreen(Game game) {
//            return new GameScreen(game);
//        }
//    },

    /**
     * The pause screen that appears when the game is paused.
     */
    PAUSE {
        public BomberScreen getScreen(Game game) {
            return new PauseScreen(game);
        }
    },

    /**
     * The settings screen where users can adjust game options.
     */
    SETTINGS {
        public BomberScreen getScreen(Game game) {
            return new SettingsScreen(game);
        }
    },

    /**
     * The key binding screen where users can configure input controls.
     */
    KEY_BINDING {
        public BomberScreen getScreen(Game game) {
            return new KeyBindingScreen(game);
        }
    };

    public abstract BomberScreen getScreen(Game game);
}
