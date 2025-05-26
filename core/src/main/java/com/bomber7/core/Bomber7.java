package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Main class of the Bomber7 game, extending LibGDX's Game class.
 * Handles the initialization, rendering, and cleanup of game resources.
 */
public class Bomber7 extends Game {
    /**
     * SpriteBatch is used for drawing 2D images and sprites.
     */
    private SpriteBatch batch;
    /**
     * Texture represents a 2D image that can be drawn on the screen.
     */
    private Texture image;

    /**
     * Called once when the application is created.
     * Initializes the sprite batch and loads the initial texture.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    /**
     * Called continuously to render the game's frame.
     * Clears the screen and draws the loaded texture.
     */
    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    /**
     * Called when the application is closed.
     * Disposes of game resources to free up memory.
     */
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
