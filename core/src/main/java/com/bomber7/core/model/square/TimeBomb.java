package com.bomber7.core.model.square;

import com.bomber7.core.model.map.LevelMap;
import java.nio.file.Path;

/**
 * The Timebomb class is a special type of Bomb that has a countdown
 * timer. When the timer reaches zero, the bomb automatically activates
 * and explodes.
 */
public class TimeBomb extends Bomb {

    /**
     * The countdown timer for the bomb.
     */
    private float timer;

    /**
     * Constructs a TimeBomb with the specified explosion power, sprite file path and timer.
     * @param p power of the bomb
     * @param x the X-coordinate
     * @param y the Y-coordinate
     * @param textureFilePath the path to the texture file
     * @param textureId the id of the texture
     * @param t initial value of the countdown timer
     */
    public TimeBomb(int p, int x, int y, Path textureFilePath, int textureId, float t) {
        super(p, x, y, textureFilePath, textureId);
        this.timer = t;
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
        if (timer > 0) {
            timer -= delta;
            if (timer <= 0) {
                timer = 0;
                activateBomb(map);
            }
        }
    }

    /**
     * Retrieves the current value of the countdown timer.
     * @return current value (in seconds)
     */
    public float getTimer() {
        return this.timer;
    }


}

