package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.utils.Constants;


/**
 * A view component for displaying a bomb in our game.
 * It renders animations based on the associated sprite sheet (bomb.png).
 */
public class ViewBomb {

    /**
     * The Bomb model associated with this view.
     */
    private final Bomb bomb;

    /**
     * The texture containing the bomb's sprite sheet.
     */
    private final Texture texture;

    /**
     * The number of columns in the sprite sheet.
     */
    private final int FRAME_COLS = 8;

    /**
     * The numbers of rows.
     */
    private final int FRAME_ROWS = 8;

    /**
     * Animation for the active bomb state.
     */
    private Animation<TextureRegion> active;

    /**
     * Animation for the explosion state.
     */
    private Animation<TextureRegion> explosion;

    /**
     * Duration of the frame.
     */
    private static final float FRAME_DURATION = 0.1f;

    /**
     * Constructs a new ViewBomb instance.
     * @param bomb the bomb to be displayed
     * @param resources the resource manager for loading textures
     */
    public ViewBomb(Bomb bomb, ResourceManager resources) {
        this.bomb = bomb;
        this.texture = resources; // SVP
        createAnimations();
    }

    /**
     * Creates the various animations for the bomb using
     * the sprite sheet texture. Each animation is extracted from
     * specific rows in the texture grid.
     */
    private void createAnimations() {
        TextureRegion[][] region = TextureRegion.split(texture,
            texture.getWidth() / FRAME_COLS,
            texture.getHeight() / FRAME_ROWS);

        active = createAnimation(region, 0, 4, true); // Active state animation
        explosion = createAnimation(region, 1, 1, false); // Explosion state animation
    }

    /**
     * Creates a generic animation from a given row in the texture grid.
     * @param region the 2D array of TextureRegions
     * @param row the row index
     * @param frameCount the number of frames in the animation
     * @param loop whether the animation should loop continuously
     * @return an Animation instance
     */
    private Animation<TextureRegion> createAnimation(TextureRegion[][] region, int row, int frameCount, boolean loop) {
        TextureRegion[] frames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = region[row][i];
        }
        return new Animation<>(FRAME_DURATION, frames);
    }

    /**
     * Retrieves the current animation frame based on the bomb's state.
     * @return the current frame of the animation
     */
    public TextureRegion getCurrentAnimationFrame() {
        Animation<TextureRegion> currentAnimation = getCurrentAnimation();
        return currentAnimation.getKeyFrame(Gdx.graphics.getDeltaTime(), true);
    }

    /**
     * Determines the current animation based on the character's state.
     * @return The current animation to be displayed.
     */
    private Animation<TextureRegion> getCurrentAnimation() {
        switch (bomb.getState()) {
            case PLACED:
                return active;
            case EXPLODED:
                return explosion;
            default:
                return null;
        }
    }

}
