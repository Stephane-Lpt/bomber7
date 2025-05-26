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
        float red = 0.15f;
        float green = 0.15f;
        float blue = 0.2f;
        float alpha = 1f;
        // Ensure the screen is cleared with a specific color before drawing.
        ScreenUtils.clear(red, green, blue, alpha);
        batch.begin();
        int widthImage = 140;
        int heightImage = 210;
        batch.draw(image, widthImage, heightImage);
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
