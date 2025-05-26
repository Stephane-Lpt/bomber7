package com.bomber7.core.model;

/**
 * Represents a game session.
 * Manages the number of players, map loading, and game state updates.
 */
public class Game {
    /**
     * The number of players gotten through controller.
     */
    private int nbPlayers;
    /**
     * The file path to the map used in the game.
     */
    private String mapFilepath;

    /**
     * Constructs a new Game instance with the specified number of players and map file.
     *
     * @param nbPlayers     the number of players in the game
     * @param mapFilepath   the file path to the map used in the game
     */
    Game(int nbPlayers, String mapFilepath) {
        this.nbPlayers = nbPlayers;
        // Load map from file
        // Initialize game state
        // Other functionalities to come ?
    }

    /**
     * Updates the game state.
     * Typically called at each frame or tick of the game loop.
     *
     * @return an integer status code representing the result of the update
     */
    public int tick() {
        // Update game state
        // Perhaps pulled from game engine ?
        return 0;
    }
}
