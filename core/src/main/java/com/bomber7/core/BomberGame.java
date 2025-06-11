package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.utils.ScreenType;
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
     * Called once when the application is created.
     * Initializes the resource manager and sets the initial screen to the main menu.
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        ScreenManager.getInstance().initialize(this);
        ConfigManager.getInstance().initialize();
        resources = new ResourceManager();

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
     * Launches the game.
     */
    public void start() {
        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
    }
}
