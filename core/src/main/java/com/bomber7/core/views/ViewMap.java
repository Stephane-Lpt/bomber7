package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.core.model.square.Wall;
import com.bomber7.utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

/**
 * The view that shows the game map in GameScreen.
 */
public class ViewMap extends Actor {

    /** The Grid ({@link <a href="https://www.youtube.com/watch?v=lILHEnz8fTk">YouTube video</a>}). */
    private final LevelMap mapGrid;

    /** The resourceManager needed for sprites. */
    private final ResourceManager resources;

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

    /**
     * Constructs a new ViewMap with the specified map grid and resource manager.
     * @param mapGrid the 2D array of Square objects representing the map
     * @param resources the ResourceManager to manage textures and resources
     */
    public ViewMap(LevelMap mapGrid, ResourceManager resources) {
        this.mapGrid = mapGrid;
        this.resources = resources;

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

        for (int col = 0; col < mapGrid.getHeight(); col++) {
            for (int row = 0; row < mapGrid.getWidth(); row++) {
                Square square = mapGrid.getSquare(row, col);

                TextureRegion squareTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getTextureName());
                drawTextureRegion(batch, squareTextureRegion, row, col, square.computeRotation());

                TextureRegion mapElementTextureRegion = null;

                if (square.hasMapElement()) {
                    if (square.getMapElement() instanceof Bomb) {
                        Gdx.app.debug("ViewMap", "drawing bomb at pos " + row + ", " + col);
                        mapElementTextureRegion = resources.getItemsSkin().getAtlas().findRegion(square.getMapElement().getTextureName());
                        if (square.getMapElement() instanceof TimeBomb) {
                            ((TimeBomb) square.getMapElement()).tick(mapGrid, Gdx.graphics.getDeltaTime());
                        }
                    } else if (square.getMapElement() instanceof Wall) {
                        mapElementTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName());
                    }
                }

                if (mapElementTextureRegion != null && square.getMapElement() != null) {
                    drawTextureRegion(batch, mapElementTextureRegion, row, col, square.getMapElement().computeRotation());
                }
            }
        }
    }

    /**
     * Updates the dimensions / coordinates used to draw the map.
     */
    private void updateDimensions() {
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;

        scaledTextureSize = Constants.TEXTURE_SIZE * Constants.SCALE;
        scaledTextureOrigin = scaledTextureSize / 2f;
        totalWidth = Constants.TEXTURE_SIZE * mapGrid.getWidth() * Constants.SCALE;
        totalHeight = Constants.TEXTURE_SIZE * mapGrid.getHeight() * Constants.SCALE;

        originX = centerX - totalWidth / 2;
        originY = centerY - totalHeight / 2;
    }

    private void drawTextureRegion(Batch batch, TextureRegion textureRegion, int row, int col, float rotation) {
        batch.draw(
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
