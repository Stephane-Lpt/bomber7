package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.Constants;
import com.bomber7.utils.ResourceManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * The view that shows the game map in GameScreen.
 */
public class ViewMap extends Actor {

    /** The Grid ({@link <a href="https://www.youtube.com/watch?v=lILHEnz8fTk">YouTube video</a>}). */
    private final LevelMap mapGrid;

    /** The ressourceManager needed for sprites. */
    private final ResourceManager resourceManager;

    /** Used to draw sprite to screen. */
    private final PolygonSpriteBatch spriteBatch;

    /** Scale factor for the map texture. */
    public static final float scale = 0.7f;

    /** The size of the scaled texture origin. */
    private float scaledTextureOrigin;

    /** The width of the total map. */
    private float totalWidth;
    /** The height of the total map. */
    private float totalHeight;
    /** The size of the scaled texture. */
    private float scaledTextureSize;
    /** The X coordinate of the origin point for drawing the map. */
    private float originX;
    /** The Y coordinate of the origin point for drawing the map. */
    private float originY;

    /** List of ViewCharacter */
    private final List<ViewCharacter> viewCharacters;

    /**
     * Constructs a new ViewMap with the specified map grid and resource manager.
     * @param mapGrid the 2D array of Square objects representing the map
     * @param resourceManager the ResourceManager to manage textures and resources
     * @param viewCharacters list of viewCharacter
     */
    public ViewMap(LevelMap mapGrid, ResourceManager resourceManager, List<ViewCharacter> viewCharacters) {
        this.mapGrid = mapGrid;
        this.resourceManager = resourceManager;
        this.spriteBatch = new PolygonSpriteBatch();
        this.viewCharacters = viewCharacters;

        updateDimensions();
    }

    /**
     * Method called by libGDX to draw the map.
     * It draws each map square by iterating through each square in the grid.
     * @param batch the Batch used for drawing
     * @param parentAlpha the alpha value of the parent actor
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        spriteBatch.begin();

        for (int col = 0; col < mapGrid.getHeight(); col++) {
            for (int row = 0; row < mapGrid.getWidth(); row++) {
                Square square = mapGrid.getSquare(row, col);

                TextureRegion squareTextureRegion = resourceManager.getMapSkin().getAtlas().findRegion(square.getTextureName());
                drawTextureRegion(squareTextureRegion, row, col, square.computeRotation());

                TextureRegion mapElementTextureRegion =
                    square.hasMapElement()
                        ?
                        resourceManager.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName())
                        :
                        null;

                if (mapElementTextureRegion != null) {
                    drawTextureRegion(mapElementTextureRegion, row, col, square.getMapElement().computeRotation());
                }
            }
        }

        if (viewCharacters != null && !viewCharacters.isEmpty() && batch instanceof SpriteBatch) {
            for (ViewCharacter viewCharacter : viewCharacters) {
                viewCharacter.renderCharacter((SpriteBatch) batch);
            }
        }

        spriteBatch.end();
    }

    /**
     * Updates the dimensions / coordinates used to draw the map.
     */
    private void updateDimensions() {
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;

        scaledTextureSize = Constants.TEXTURE_SIZE * scale;
        scaledTextureOrigin = scaledTextureSize / 2f;
        totalWidth = Constants.TEXTURE_SIZE * mapGrid.getWidth() * scale;
        totalHeight = Constants.TEXTURE_SIZE * mapGrid.getHeight() * scale;

        originX = centerX - totalWidth / 2;
        originY = centerY - totalHeight / 2;
    }

    private void drawTextureRegion(TextureRegion textureRegion, int row, int col, float rotation) {
        spriteBatch.draw(
            textureRegion,
            originX + (row * scaledTextureSize),
            originY + (col * scaledTextureSize),
            scaledTextureOrigin,
            scaledTextureOrigin,
            scaledTextureSize,
            scaledTextureSize,
            1,
            1,
            rotation
        );
    }
}
