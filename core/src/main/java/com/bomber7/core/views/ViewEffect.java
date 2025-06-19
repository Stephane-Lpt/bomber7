package com.bomber7.core.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Effect;

/**
 * An actor used in {@link ViewMap} to draw animated game effects.
 */
public class ViewEffect extends Actor {

    /**
     * The time duration of each frame in seconds.
     */
    private float timeRemaining;

    /**
     * The time remaining for the effect to be displayed.
     */
    private float animationTimer;

    /**
     * The index of the current frame in the animation.
     */
    private int currentFrameIndex;

    /**
     * The effect to be displayed.
     */
    private Effect effect;

    /**
     * Constructs a ViewEffect with the specified effect.
     *
     * @param effect the effect to be displayed
     */
    public ViewEffect(Effect effect) {
        this.setX(effect.getX());
        this.setY(effect.getY());
        this.effect = effect;
        this.timeRemaining = effect.getType().getFramesCount() * Constants.FRAME_DURATION;
        this.animationTimer = 0f;
        this.currentFrameIndex = 0;
    }

    /**
     * Returns the effect associated with this ViewEffect.
     *
     * @return the effect
     */
    String getCurrentTextureName() {
        return effect.getType().getFrames()[currentFrameIndex].toLowerCase();
    }

    /**
     * Returns the effect associated with this ViewEffect.
     *
     * @param delta the time delta since the last update
     * @return the effect
     */
    public boolean update(float delta) {
        if (timeRemaining > 0) {
            timeRemaining -= delta;

            animationTimer += delta;
            if (animationTimer >= Constants.FRAME_DURATION) {
                animationTimer -= Constants.FRAME_DURATION;
                currentFrameIndex = (currentFrameIndex + 1) % effect.getType().getFramesCount();
            }

            return timeRemaining > 0;
        }
        return false;
    }
}
