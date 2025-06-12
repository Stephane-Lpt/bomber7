package com.bomber7.utils;

public enum SpawnPoints {
    /**
     * Spawn point for player 1.
     */
    PLAYER_1(1, 1),
    /**
     * Spawn point for player 2.
     */
    PLAYER_2(1, 24),
    /**
     * Spawn point for player 3.
     */
    PLAYER_3(34, 24),
    /**
     * Spawn point for player 4.
     */
    PLAYER_4(34, 1);

    private final int x;
    private final int y;

    SpawnPoints(int x, int y) {
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
