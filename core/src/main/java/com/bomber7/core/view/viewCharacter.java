import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bomber7.core.model.Character;

/**
 * A view component for displaying a character in our game, implementing the Observer pattern.
 */
public class viewCharacter implements Observer {

    private Character character;
    private Texture texture;
    protected static final int TILE_SIZE = 32;
    private static final int FRAME_COLS = 8;
    private static final int FRAME_ROWS = 7;

    Animation<TextureRegion> moveRight;
    Animation<TextureRegion> moveLeft;
    Animation<TextureRegion> moveUp;
    Animation<TextureRegion> moveDown;
    Animation<TextureRegion> stand;
    Animation<TextureRegion> die;

    /**
     * Constructs a new CharacterView for the specified character
     * @param character the character to be displayed
     */
    public viewCharacter(Character character) {
        this.character = character;
        this.texture = new Texture(Gdx.files.internal(character.getSpriteFP()));
    }

    /**
     * Creates the animations for the character based on the texture.
     */
    private void createAnimations () {

        // TODO : change the split size to match the character sprite sheet
        TextureRegion[][] tmp = TextureRegion.split(texture,
				texture.getWidth() / FRAME_COLS,
				texture.getHeight() / FRAME_ROWS);


        // Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

        TextureRegion[] moveRightFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            moveRightFrames[i] = tmp[0][i];
        }
		moveRight = new Animation<>(0.1f, moveRightFrames);

        TextureRegion[] moveLeftFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            moveLeftFrames[i] = new TextureRegion(moveRightFrames[i]);
            moveLeftFrames[i].flip(true, false);
        }
        moveLeft = new Animation<>(0.1f, moveLeftFrames);

        TextureRegion[] moveUpFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            moveUpFrames[i] = tmp[2][i];
        }
        moveUp = new Animation<>(0.1f, moveUpFrames);

        TextureRegion[] moveDownFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            moveDownFrames[i] = tmp[1][i];
        }
		moveDown = new Animation<>(0.1f, moveDownFrames);

        TextureRegion[] standFrames = new TextureRegion[1];
        standFrames[0] = tmp[5][0];
		stand = new Animation<>(0.1f, walkFrames);

        TextureRegion[] dieFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            dieFrames[i] = tmp[4][i];
        }
		die = new Animation<>(0.1f, dieFrames);
    };


    public void renderCharacter(SpriteBatch batch) {
        Animation<TextureRegion> currentAnimation = stand;
        if (this.character.getState() == Character.State.MOVE_RIGHT) {
            currentAnimation = moveRight;
        } else if (this.character.getState() == Character.State.MOVE_LEFT) {
            currentAnimation = moveLeft;
        } else if (this.character.getState() == Character.State.MOVE_UP) {
            currentAnimation = moveUp;
        } else if (this.character.getState() == Character.State.MOVE_DOWN) {
            currentAnimation = moveDown;
        } else if (this.character.getState() == Character.State.STAND) {
            currentAnimation = stand;
        } else if (this.character.getState() == Character.State.DIE) {
            currentAnimation = die;
        }

        batch.draw(currentAnimation.getKeyFrame(Gdx.graphics.getDeltaTime(), true), character.getPosition().x, character.getPosition().y, 1, 1);

    }
}
