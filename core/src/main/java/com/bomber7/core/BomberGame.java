package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.core.screens.MainMenuScreen;
import com.bomber7.core.screens.OptionsScreen;

/**
 * Main class of the Bomber7 game, extending LibGDX's Game class.
 */
public class BomberGame extends Game {
    public final int MAX_PLAYERS = 4;

    public BomberResources resources;

    /**
     * Called once when the application is created.
     * Initializes the sprite batch and loads the initial texture.
     */
    @Override
    public void create () {
        resources = new BomberResources();
        setScreen(new OptionsScreen(this));
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
}
