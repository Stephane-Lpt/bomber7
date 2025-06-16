package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.entities.Character;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Dimensions;


/**
 * A view component for displaying a character in our game. It renders
 * animations based on the associated sprite sheet and observes the state
 * of a Character.
 *
 * The animations include movements in different directions, a standing
 * state and a death animation.
 *
 */
public class ViewCharacter extends Actor {

    /**
     * The Character model associated with this view.
     */
    private final Character character;
    /**
     * The texture containing the character's sprite sheet.
     */
    private final Texture texture;

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
     * Width of player's name text.
     */
    private float nameLabelWidth;

    /**
     * Duration of the frame.
     */
    private static final float FRAME_DURATION = 0.1f;

    /**
     * Constructs a new ViewCharacter instance.
     *
     * @param character the character to be displayed
     */
    public ViewCharacter(Character character, ResourceManager resources) {
        this.character = character;
        this.texture = resources.getCharacterSkin().getAtlas().findRegion(character.getGameCharacter().getDrawableName()).getTexture();

        GlyphLayout nameContainer = new GlyphLayout();
        nameContainer.setText(resources.getSkin().getFont("pixelify-sm"), character.getName());
        nameLabelWidth = nameContainer.width;

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
     * Method called by libGDX to draw the character.
     * @param batch the Batch used for drawing
     * @param parentAlpha the alpha value of the parent actor
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Animation<TextureRegion> currentAnimation = getCurrentAnimation();
        TextureRegion currentFrame = currentAnimation.getKeyFrame(Gdx.graphics.getDeltaTime(), true);

        // Drawing character
        batch.draw(
            currentFrame,
            character.getPositionX(),
            character.getPositionY(),
            Constants.TEXTURE_SIZE * Constants.SCALE,
            Constants.TEXTURE_SIZE * Constants.SCALE
        );

        // Drawing character name
        ResourceManager.getInstance().getSkin().getFont("pixelify-sm").draw(
            batch,
            character.getName(),
            character.getPositionX() - nameLabelWidth / 2 + (Constants.TEXTURE_SIZE * Constants.SCALE) / 2,
            character.getPositionY() + Dimensions.COMPONENT_SPACING
        );

        // TODO: create debug mode
        if (true) {
            String debugPixelPos = "X: " + character.getPositionX() + ", Y: " + character.getPositionY();
            String debugMapPos = "mX: " + character.getMapX() + ", mY: " + character.getMapY();
            String debugState = "state: " + character.getMovingStatus();


            ResourceManager.getInstance().getSkin().getFont("pixelify-sm").draw(
                batch,
                debugPixelPos + "\n" + debugMapPos + "\n" + debugState,
                character.getPositionX(),
                character.getPositionY() + Dimensions.LABEL_PADDING
            );
        }
    }

    /**
     * Determines the current animation based on the character's state.
     * @return The current animation to be displayed.
     */
    private Animation<TextureRegion> getCurrentAnimation() {
        switch (character.getMovingStatus()) {
            case MOVING_RIGHT:
                return moveRight;
            case MOVING_LEFT:
                return moveLeft;
            case MOVING_UP:
                return moveUp;
            case MOVING_DOWN:
                return moveDown;
            case STANDING_STILL:
                return stand;
            default:
                return die;
        }
    }
}
