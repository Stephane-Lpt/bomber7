package com.bomber7.core.model.square;

import com.bomber7.core.model.map.LevelMap;

/**
 * The Timebomb class is a special type of Bomb that has a countdown
 * timer. When the timer reaches zero, the bomb automatically activates
 * and explodes.
 */
public class TimeBomb extends Bomb {

    /**
     * The countdown timer for the bomb.
     */
    private float timeRemaining;

    /**
     * The texture name associated with the TimeBomb.
     */
    public static final String TEXTURE_NAME = "time_bomb";

    /**
     * The default countdown timer value for the TimeBomb.
     */
    private static final float DEFAULT_TIMER = 0.5f;

    /**
     * Constructs a TimeBomb with the specified explosion power, sprite file path and timer.
     * @param p power of the bomb
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public TimeBomb(int p, int x, int y) {
        super(p, x, y, TimeBomb.TEXTURE_NAME);
        this.timeRemaining = TimeBomb.DEFAULT_TIMER;
    }

    /**
     * Decreases the countdown timer based on the time delta.
     * @param map the map on which the bomb is located
     * @param delta the elapsed time (in seconds) to decrease
     */
    public void tick(LevelMap map, float delta) {
        if (map == null) {
            throw new NullPointerException("LevelMap cannot be null");
        }
        if (this.timeRemaining > 0) {
            this.timeRemaining -= delta;
            if (this.timeRemaining <= 0) {
                this.timeRemaining = 0;
                activateBomb(map);
            }
        }
    }

    /**
     * Retrieves the current value of the countdown timer.
     * @return current value (in seconds)
     */
    public float getTimer() {
        return this.timeRemaining;
    }


}

