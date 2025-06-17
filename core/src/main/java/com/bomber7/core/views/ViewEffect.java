package com.bomber7.core.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Effect;

/**
 * An actor used in {@link ViewMap} to draw animated game effects.
 */
public class ViewEffect extends Actor {

    private float timeRemaining;
    private float animationTimer;
    private int currentFrameIndex;
    private Effect effect;

    public ViewEffect(Effect effect) {
        this.setX(effect.getX());
        this.setY(effect.getY());
        this.effect = effect;
        this.timeRemaining = effect.getType().getFramesCount() * Constants.FRAME_DURATION;
        this.animationTimer = 0f;
        this.currentFrameIndex = 0;
    }

    String getCurrentTextureName() {
        return effect.getType().getFrames()[currentFrameIndex].toLowerCase();
    }

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
