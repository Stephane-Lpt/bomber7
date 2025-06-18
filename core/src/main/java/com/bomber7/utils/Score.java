package com.bomber7.utils;

/**
 * A utility class representing the score points to credit or debit
 * to the player when specific game events occur.
 */
public final class Score {

    /**
     * Private constructor to prevent instanciation.
     */
    private Score() { }

    /**
     * Points awarded when the player wins.
     */
    public static final int WIN = 10;

    /**
     * Points awarded when the player places a bomb.
     */
    public static final int DROP_BOMB = 1;

    /**
     * Points deducted when the player dies.
     */
    public static final int DEAD = -2;

    /**
     * Points awarded to the player at each interval defined by {@code Constants.ALIVE_SCORE_TIMER}.
     */
    public static final int ALIVE = 1;

    /**
     * Points awarded when the player kills another player.
     */
    public static final int KILL = 5;

    /**
     * Points deducted when the player suicides.
     */
    public static final int SUICIDE = DEAD;
}
