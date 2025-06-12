package com.bomber7.core.model.entities;

import com.bomber7.core.controller.PlayerConfig;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.utils.GameCharacter;

/**
 * Human player class.
*/
public class HumanPlayer extends Player {

    /** The configuration for the player controls. */
    private PlayerConfig config;

    /**
     * HumanPlayer Constructor.
     * @param config   The configuration for the player controls
     * @param map      The map.
     * @param name     The name of the player
     * @param mapX    The x-coordinate of the player
     * @param mapY    The y-coordinate of the player
     * @param gameCharacter The game character type
     */
    public HumanPlayer(PlayerConfig config, LevelMap map, String name, int mapX, int mapY, GameCharacter gameCharacter) {
        super(name, map, mapX, mapY, 1, 1, gameCharacter);
        this.config = config;
    }

    /**
     * Gets the configuration for the player controls.
     * @return The configuration object.
     */
    public PlayerConfig getConfig() {
        return this.config;
    }

}
