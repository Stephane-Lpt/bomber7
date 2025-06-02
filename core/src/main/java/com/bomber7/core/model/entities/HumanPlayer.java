package com.bomber7.core.model.entities;

import com.bomber7.core.controller.Config;

public class HumanPlayer extends Player {

    /** The configuration for the player controls. */
    private Config config;

    /**
     * HumanPlayer Constructor.
     * @param config   The configuration for the player controls
     * @param name     The name of the player
     * @param x        The x-coordinate of the player
     * @param y        The y-coordinate of the player
     * @param spriteFP The file path to the player's sprite
     */
    public HumanPlayer(Config config, LevelMap map, String name, int x, int y, String spriteFP) {
        super(name, map, x, y, 1, 1, spriteFP);
        this.config = config;
    }

    /**
     * Gets the configuration for the player controls.
     * @return The configuration object.
     */
    public Config getConfig() {
        Config tmpConfig = this.config;
        return tmpConfig;
    }

    // /**
    //  * Drops a bomb at the player's current position.
    //  */
    // public void dropBomb() {
    // // Logic to drop a bomb
    // }
}
