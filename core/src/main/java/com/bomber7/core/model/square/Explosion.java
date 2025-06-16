package com.bomber7.core.model.square;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.utils.Constants;

public class Explosion extends MapElement {
    /**
     * The texture name prefix associated with the Explosion.
     */
    public static final String TEXTURE_PREFIX = "explosion";

    /**
     * The X-coordinate of the explosion.
     */
    private final int x;
    /**
     * The Y-coordinate of the explosion.
     */
    private final int y;

    /**
     * The countdown timer for the bomb.
     */
    private float timeRemaining;

    private static final String[] FRAMES = {
        Explosion.TEXTURE_PREFIX + "-1",
        Explosion.TEXTURE_PREFIX + "-2",
        Explosion.TEXTURE_PREFIX + "-3",
        Explosion.TEXTURE_PREFIX + "-4",
        Explosion.TEXTURE_PREFIX + "-5",
        Explosion.TEXTURE_PREFIX + "-4",
        Explosion.TEXTURE_PREFIX + "-3",
        Explosion.TEXTURE_PREFIX + "-2",
        Explosion.TEXTURE_PREFIX + "-1"
    };

    private float animationTimer = 0f;
    private int currentFrameIndex = 0;

    /**
     * Constructs an Explosion mapElement replacing an exploded bomb / wall.
     * @param x the X-coordinate
     * @param y the Y-coordinate
     */
    public Explosion(int x, int y) {
        super(TEXTURE_PREFIX + "-1");
        Gdx.app.debug("Explosion", "Constructor");

        this.timeRemaining = Constants.FRAME_DURATION * FRAMES.length;

        this.x = x;
        this.y = y;
    }

    /**
     * Decreases the countdown timer based on the time delta.
     * The Explosion mapElement deletes itself from the levelMap once the animation ends.²
     * @param map the map on which the bomb is located
     * @param delta the elapsed time (in seconds) to decrease
     */
    public void tick(LevelMap map, float delta) {
        if (map == null) {
            throw new NullPointerException("LevelMap cannot be null");
        }
        if (this.timeRemaining > 0) {
            this.timeRemaining -= delta;

            // Animate texture every 0.1s
            animationTimer += delta;
            if (animationTimer >= Constants.FRAME_DURATION) {
                animationTimer -= Constants.FRAME_DURATION;
                currentFrameIndex = (currentFrameIndex + 1) % FRAMES.length;
                this.setTextureName(FRAMES[currentFrameIndex]);
            }

            if (this.timeRemaining <= 0) {
                this.timeRemaining = 0;

                // Deleting explosion map element
                Square sq = map.getSquare(x, y);
                if (sq != null) {
                    Gdx.app.debug("Explosion", "Deleting itself");
                    sq.clearMapElement();
                }
            }
        }
    }
}
