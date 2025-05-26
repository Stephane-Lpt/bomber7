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

    private static final float RED = 0.15f;
    private static final float GREEN = 0.15f;
    private static final float BLUE = 0.2f;
    private static final float ALPHA_OPAQUE = 1f;

    private static final int widthLogo = 140;
    private static final int heightLogo = 210;

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
        // Ensure the screen is cleared with a specific color before drawing.
        ScreenUtils.clear(RED, GREEN, BLUE, ALPHA_OPAQUE);
        batch.begin();
        batch.draw(image, widthLogo, heightLogo);
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
