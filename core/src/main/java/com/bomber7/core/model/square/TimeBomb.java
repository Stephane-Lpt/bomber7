package com.bomber7.core.model.square;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.utils.Constants;
import com.bomber7.core.model.entities.Character;

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
     * The texture name prefix associated with the TimeBomb.
     */
    public static final String TEXTURE_PREFIX = "time-bomb";

    /**
     * Timer to determine when to switch beetween textures.
     */
    private float animationTimer = 0f;
    /**
     * Used to toggle between two bomb textures for blinking.
     */
    private boolean textureToggle = false;

    /**
     * Constructs a TimeBomb with the specified explosion power, sprite file path and timer.
     * @param p power of the bomb
     * @param x the X-coordinate
     * @param y the Y-coordinate
     * @param c the character that planted this bomb
     */
    public TimeBomb(int p, int x, int y, Character c) {
        super(p, x, y, TimeBomb.TEXTURE_PREFIX + "-1", c);
        this.timeRemaining = Constants.BOMB_TIMER;
    }

    /**
     * Decreases the countdown timer based on the time delta.
     * @param map the map on which the bomb is located
     * @param delta the elapsed time (in seconds) to decrease
     */
    public void tick(LevelMap map, float delta) {
        final float animationSwapDelta = 0.5f;

        if (map == null) {
            throw new NullPointerException("LevelMap cannot be null");
        }

        if (this.timeRemaining > 0) {
            this.timeRemaining -= delta;

            // Switching between textures every 0.5 seconds
            animationTimer += delta;
            if (animationTimer >= animationSwapDelta) {
                textureToggle = !textureToggle;
                this.setTextureName(textureToggle ? TEXTURE_PREFIX + "-2" : TEXTURE_PREFIX + "-1");
                animationTimer = 0f;
            }

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

