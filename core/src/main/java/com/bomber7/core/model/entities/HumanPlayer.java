package com.bomber7.core.model.entities;

import com.bomber7.core.controller.Config;

public class HumanPlayer extends Player {

    private Config config;
    private Bomb[] bombs;

    /**
     * HumanPlayer Constructor
     * @param config The configuration for the player controls
     * @param name The name of the player
     * @param x The x-coordinate of the player
     * @param y The y-coordinate of the player
     * @param spriteFP The file path to the player's sprite
     */
    public HumanPlayer(Config config, String name, int x, int y, String spriteFP) {
        super(name, x, y, spriteFP);
        this.config = config;
    }

    /**
     * Gets the configuration for the player controls.
     * @return The configuration object.
     */
    public Config getConfig() {
        return config;
    }

    public void dropBomb() {
        // Logic to drop a bomb
        // This method would typically interact with the game state to place a bomb at the player's current position
    }
}
