package com.bomber7.utils;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

/**
 * List of spawnpoint for each player.
 */
public class SpawnPoint {

    /**
     * The maximum number of players supported in the game.
     */
    private static final Array<GridPoint2> SPAWN_POINTS = new Array<>(new GridPoint2[] {
        new GridPoint2(1, 23),   // Spawnpoint of player 1
        new GridPoint2(33, 1),   // Spawnpoint of player 2
        new GridPoint2(33, 23),  // Spawnpoint of player 3
        new GridPoint2(1, 1)     // Spawnpoint of player 4
    });

    /**
     * Returns the spawn point for the given player index.
     * @param index player index
     * @return a GridPoint2 instance representing the spawn point
     * @throws IllegalArgumentException if index is out of bounds
     */
    public static GridPoint2 getForPlayer(int index) {
        if (index < 0 || index >= SPAWN_POINTS.size) {
            throw new IllegalArgumentException("Invalid player index: " + index + "(Min: 0, Max: " + Constants.MAX_PLAYERS + ")");
        }
        return new GridPoint2(SPAWN_POINTS.get(index));
    }
}
