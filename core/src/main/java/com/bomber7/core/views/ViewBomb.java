package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.bomber7.core.model.Bomb;

/**
 * A visual representation of a bomb in our game.
 * This class handles rendering different animations for the bomb based
 * on its state: countdown, explosion, post-explosion.
 */
public class ViewBomb {

    /**
     * The bomb model associated with this view.
     */
    private Bomb bomb;

    /**
     * The texture containing the bomb's sprite sheet.
     */
    private Texture texture;

    /**
     * The elapsed state time used for animation timing.
     */
    private float stateTime;

    /**
     * Size of each tile in the sprite sheet.
     */
    private static final int TILE_SIZE = 32;

    /**
     * Number of columns.
     */
    private static final int FRAME_COLS = 4;

    /**
     * Number of rows.
     */
    private static final int FRAME_ROWS = 3;

    /**
     * Animation for when the bomb is in the countdown phase.
     */
    private Animation<TextureRegion> countdownAnimation;

    /**
     * Animation for when the bomb is exploding.
     */
    private Animation<TextureRegion> explosionAnimation;

    /**
     * Animation for after the bomb explosion.
     */
    private Animation<TextureRegion> afterExpAnimation;

    /**
     * Constructs a new ViewBomb instance.
     *
     * @param bomb the bomb model to be displayed
     */
    public ViewBomb(Bomb bomb) {
        this.bomb = bomb;
        this.texture = new Texture(Gdx.files.internal(bomb.getSpriteFP()));
        this.stateTime = 0;
        createAnimations();
    }

    /**
     * Creates the animations for the bomb using its sprite sheet.
     */
    private void createAnimations() {
        // Split the texture into a grid of regions
        TextureRegion[][] regions = TextureRegion.split(texture, TILE_SIZE, TILE_SIZE);

        // Countdown animation - 4 frames from the first row of the sprite sheet
        TextureRegion[] countdownFrames = new TextureRegion[FRAME_COLS];
        for (int i = 0; i < FRAME_COLS; i++) {
            countdownFrames[i] = regions[0][i];
        }
        countdownAnimation = new Animation<>(0.2f, countdownFrames);

        // Explosion animation - single frame from the second row
        TextureRegion[] explosionFrames = new TextureRegion[1];
        explosionFrames[0] = regions[1][0];
        explosionAnimation = new Animation<>(0.1f, explosionFrames);

        // Inactive animation - for example, use the first frame from the third row
        TextureRegion[] afterExpFrames = new TextureRegion[1];
        afterExpFrames[0] = regions[2][0];
        afterExpAnimation = new Animation<>(0.2f, afterExpFrames);
    }

    /**
     * Renders the bomb based on its state.
     *
     * @param batch The SpriteBatch used for rendering
     */
    public void renderBomb(SpriteBatch batch) {

        // Just to synchronize the animation with real time
        stateTime += Gdx.graphics.getDeltaTime();

        // Determine the current frame based on the bomb's state
        TextureRegion currentFrame = null;
        switch (bomb.getState()) {
            case COUNTDOWN:
                currentFrame = countdownAnimation.getKeyFrame(stateTime, true);
                break;
            case EXPLODING:
                currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
                break;
            case INACTIVE:
                currentFrame = afterExpAnimation.getKeyFrame(stateTime, false);
                break;
            default:
                throw new IllegalStateException("Unexpected bomb state: " + bomb.getState());
        }
        batch.draw(currentFrame, bomb.getX(), bomb.getY(), TILE_SIZE, TILE_SIZE);
    }
}
