package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.core.model.GameCandidate;
import com.bomber7.utils.Constants;
import com.bomber7.utils.ScreenType;

import java.util.Arrays;
import java.util.Objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/**
 * Main class of the Bomber7 game, extending LibGDX's {@link com.badlogic.gdx.Game} class.
 * <p>
 * Manages game lifecycle and resources.
 * </p>
 */
public class BomberGame extends Game {
    /**
     * Resource manager responsible for loading and managing game assets.
     */
    private ResourceManager resources;

    /**
     * GameCandidate that holds the players, maps and rounds configured {@link com.bomber7.core.screens.PlayerSelectionScreen} and
     * {@link com.bomber7.core.screens.MapSelectionScreen}.
     * When a game is started, the characters as well as the map is initialized used this object.
     */
    private GameCandidate gameCandidate;

    /**
     * Called once when the application is created.
     * Initializes the resource manager and sets the initial screen to the main menu.
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        ScreenManager.getInstance().initialize(this);
        ConfigManager.getInstance().initialize();
        resources = new ResourceManager();
        gameCandidate = new GameCandidate();

        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }

    /**
     * Called when the application is closed.
     * Disposes of game resources to free up memory.
     */
    @Override
    public void dispose() {
        resources.dispose();
        super.dispose();
    }

    /**
     * Returns the {@link ResourceManager} instance used by this game.
     *
     * @return the resource manager for game assets
     */
    public ResourceManager getBomberResources() {
        return resources;
    }

    /**
     * Returns the gameCandidate used to create the game.
     * @return the game candidate instance
     */
    public GameCandidate getCandidate() {
        return gameCandidate;
    }

    /**
     * Launches the game.
     * Precondition : the gameCandidate must be initialized with players and maps.
     */
    public void start() {
        // Printing gameCandidate for debug
        Gdx.app.debug("BomberGame", "GameCandidate: " + gameCandidate.toString());

        // Check if gameCandidate is properly initialized
        if (gameCandidate.getPlayerBlueprints() == null) {
            Gdx.app.error("BomberGame", "GameCandidate is missing player configurations.");
            throw new IllegalStateException("GameCandidate must have at least one player configured.");
        }

        if (gameCandidate.getMaps() == null || gameCandidate.getMaps().isEmpty()) {
            Gdx.app.error("BomberGame", "GameCandidate is missing map configurations.");
            throw new IllegalStateException("GameCandidate must have at least one map configured.");
        }

        if (gameCandidate.getRounds() < Constants.MIN_ROUNDS) {
            Gdx.app.error("BomberGame", "GameCandidate has an invalid number of rounds.");
            throw new IllegalStateException("GameCandidate must have a valid number of rounds.");
        }

        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);

    }

    /**
     * Pauses the game.
     */
    public void pause() {
        ScreenManager.getInstance().showScreen(ScreenType.PAUSE, true, true);
    }

    /**
     * Stops the game.
     */
    public void stop() {
        gameCandidate.reset();
        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }
}
