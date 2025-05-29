

public class viewCharacter.java implements Observer {

    private Character character;
    private Texture texture;
    protected static final int TILE_SIZE = 32;

    public viewCharacter(Character character) {
        this.character = character;
        this.texture = new Texture(character.getTexturePath());
        // character.addObserver(this); add in the right place
    }

    @Override
    public void refresh(Model model) {
        // animation logic 
    }
    
    public void draw(SpriteBatch batch) {
        
        if (character.isAlive()) {
            float x = character.getPositionX() * TILE_SIZE;
            float y = character.getPositionY() * TILE_SIZE;

            batch.draw(this.texture, x, y);
        }
    }

    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}
