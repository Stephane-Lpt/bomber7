import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bomber7.core.model.Bomb;

public class viewBomb {

    private Bomb bomb;
    private Texture texture;
    float stateTime;

    private static final int TILE_SIZE = 32;
    private static final int FRAME_COLS = 4;
    private static final int FRAME_ROWS = 3;

    private Animation<TextureRegion> countdownAnimation;
    private Animation<TextureRegion> explosionAnimation;
    private Animation<TextureRegion> afterExpAnimation;


    public viewBomb(Bomb bomb) {
        this.bomb = bomb;
        this.texture = new Texture(Gdx.files.internal(bomb.getSpriteFP()));
        this.stateTime = 0;
        createAnimations();
    }


    private void createAnimations() {

        TextureRegion[][] regions = TextureRegion.split(texture,
            TILE_SIZE, TILE_SIZE);

        TextureRegion[] countdownFrames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            countdownFrames[i] = regions[0][i];
        }
        countdownAnimation = new Animation<TextureRegion>(0.2f, countdownFrames);

        TextureRegion[] explosionFrames = new TextureRegion[1];
        explosionFrames[1] = regions[2][6];
        explosionAnimation = new Animation<TextureRegion>(0.1f,explosionFrames);
    }


    public void renderBomb(SpriteBatch batch) {

        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame;

        switch (bomb.getState()) {
            case COUNTDOWN:
                currentFrame = countdownAnimation.getKeyFrame(stateTime, true);
                break;
            case EXPLODING:
                currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
                break;
            case INACTIVE:
            default:
                //
                break;
        }

        batch.draw(currentFrame, bomb.getX(), bomb.getY(), TILE_SIZE, TILE_SIZE);
    }
}
