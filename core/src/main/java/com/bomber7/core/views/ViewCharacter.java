package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.bomber7.core.model.entities.Character;

/**
 * A view component for displaying a character in our game. It renders
 * animations based on the associated sprite sheet and observes the state
 * of a Character.
 *
 * The animations include movements in different directions, a standing
 * state and a death animation.
 *
 */
public class ViewCharacter {

    /**
     * The Character model associated with this view.
     */
    private Character character;
    /**
     * The texture containing the character's sprite sheet.
     */
    private Texture texture;

    /**
     * The constant size of a tile in the sprite sheet (in pixels).
     */
    protected static final int TILE_SIZE = 32;

    /**
     * The number of columns.
     */
    private static final int FRAME_COLS = 8;

    /**
     * The number of rows.
     */
    private static final int FRAME_ROWS = 7;

    /*
     * Animation for moving to the right.
     */
    private Animation<TextureRegion> moveRight;

    /*
     * Animation for moving to the left.
     */
    private Animation<TextureRegion> moveLeft;

    /**
     * Animation for moving upward.
     */
    private Animation<TextureRegion> moveUp;

    /**
     * Animation for moving downward.
     */
    private Animation<TextureRegion> moveDown;

    /**
     * Animation for the standing (idle) position.
     */
    private Animation<TextureRegion> stand;

    /**
     * Animation for the death state.
     */
    private Animation<TextureRegion> die;

    /**
     * Duration of the frame.
     */
    private static final float FRAME_DURATION = 0.1f;

    /**
     * Constructs a new ViewCharacter instance.
     *
     * @param character the character to be displayed
     */
    public ViewCharacter(Character character) {
        this.character = character;
        this.texture = new Texture(Gdx.files.internal(character.getSpriteFP()));
        createAnimations();
    }

    /**
     * Creates the various animations for the character using
     * the sprite sheet texture. Each animation is extracted from
     * specific rows in the texture grid.
     */
    private void createAnimations() {
        TextureRegion[][] region = TextureRegion.split(texture,
            texture.getWidth() / FRAME_COLS,
            texture.getHeight() / FRAME_ROWS);

        moveRight = createAnimation(region, 0, 3, false);
        moveLeft = createMirroredAnimation(region, moveRight, false);
        moveUp = createAnimation(region, 2, 3, false);
        moveDown = createAnimation(region, 1, 3, false);
        stand = createAnimation(region, 5, 1, false);
        die = createAnimation(region, 4, 6, false);
    }

    /**
     * Creates a generic animation from a row in the sprite sheet.
     * @param region the 2D array of texture regions.
     * @param row  the row index.
     * @param frameCount the number of frames in the row.
     * @param loop Whether the animation should loop continuously.
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
     * Creates a mirrored version of an animation by flipping the texture regions horizontally.
     * @param region The 2D array of texture regions (not used, may be removed).
     * @param baseAnimation The animation to be mirrored.
     * @param loop Whether the animation should loop.
     * @return The mirrored animation.
     */
    private Animation<TextureRegion> createMirroredAnimation(TextureRegion[][] region, Animation<TextureRegion> baseAnimation, boolean loop) {
        TextureRegion[] frames = baseAnimation.getKeyFrames();
        TextureRegion[] mirroredFrames = new TextureRegion[frames.length];
        for (int i = 0; i < frames.length; i++) {
            mirroredFrames[i] = new TextureRegion(frames[i]);
            mirroredFrames[i].flip(true, false);
        }
        return new Animation<>(FRAME_DURATION, mirroredFrames);
    }

    /**
     * Renders the character on the screen.
     * @param batch The SpriteBatch used for rendering.
     */
    public void renderCharacter(SpriteBatch batch) {
        Animation<TextureRegion> currentAnimation = getCurrentAnimation();
        TextureRegion currentFrame = currentAnimation.getKeyFrame(Gdx.graphics.getDeltaTime(), true);

        batch.draw(currentFrame, character.getPositionX(), character.getPositionY(), 1, 1);
    }

    /**
     * Determines the current animation based on the character's state.
     * @return The current animation to be displayed.
     */
    private Animation<TextureRegion> getCurrentAnimation() {
        switch (this.character.getState()) {
            case MOVE_RIGHT:
                return moveRight;
            case MOVE_LEFT:
                return moveLeft;
            case MOVE_UP:
                return moveUp;
            case MOVE_DOWN:
                return moveDown;
            case DIE:
                return die;
            case STAND:
            default:
                return stand;
        }
    }
}
