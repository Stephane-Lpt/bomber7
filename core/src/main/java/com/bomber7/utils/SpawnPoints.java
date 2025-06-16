package com.bomber7.utils;

public enum SpawnPoints {
    /**
     * Spawn point for player 1.
     */
    PLAYER_1(1, 23),
    /**
     * Spawn point for player 2.
     */
    PLAYER_2(33, 1),
    /**
     * Spawn point for player 3.
     */
    PLAYER_3(33, 23),
    /**
     * Spawn point for player 4.
     */
    PLAYER_4(1, 1);

    /**
     * X square coordinate of the spawnpoint.
     */
    private final int x;
    /**
     * Y square coordinate of the spawnpoint.
     */
    private final int y;

    /**
     *
     * @param x
     * @param y
     */
    private SpawnPoints(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
