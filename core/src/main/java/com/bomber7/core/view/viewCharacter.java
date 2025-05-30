import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A view component for displaying a character in our game, implementing the Observer pattern.
 */
public class viewCharacter implements Observer {

    private Character character;
    private Texture texture;
    protected static final int TILE_SIZE = 32;

    /**
     * Constructs a new CharacterView for the specified character
     * @param character the character to be displayed
     */
    public viewCharacter(Character character) {
        this.character = character;
        this.texture = new Texture(character.getTexturePath());
        // character.addObserver(this); add in the right place
    }

    /**
     * Updates the view based on changes in the model.
     * @param model game model that has changed
     */
    @Override
    public void refresh(Model model) {
        // animations
    }

    /**
     * Draws the character on the screen using the provided SpriteBatch
     * @param batch the spriteBatch to use for drawing
     */
    public void draw(SpriteBatch batch) {

        if (character.isAlive()) {
            float x = character.getPositionX() * TILE_SIZE;
            float y = character.getPositionY() * TILE_SIZE;

            batch.draw(this.texture, x, y);
        }
    }

    /**
     * Realeases all ressources used by this view.
     */
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
