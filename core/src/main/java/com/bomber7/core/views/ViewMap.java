package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.Constants;
import com.bomber7.utils.ResourceManager;

public class ViewMap extends Actor {

    /** The Grid (https://www.youtube.com/watch?v=lILHEnz8fTk) */
    private final LevelMap mapGrid;

    /** The ressourceManager needed for sprites */
    private final ResourceManager resourceManager;

    /** Used to draw sprite to screen */
    private final PolygonSpriteBatch spriteBatch;

    private final float scale = 0.7f;
    private float scaledTextureOrigin;
    private float totalWidth;
    private float totalHeight;
    private float scaledTextureSize;
    private float originX;
    private float originY;

    /**
     * Constructs a new ViewMap with the specified map grid and resource manager.
     * @param mapGrid the 2D array of Square objects representing the map
     * @param resourceManager the ResourceManager to manage textures and resources
     */
    public ViewMap(LevelMap mapGrid, ResourceManager resourceManager) {
        this.mapGrid = mapGrid;
        this.resourceManager = resourceManager;
        this.spriteBatch = new PolygonSpriteBatch();

        updateDimensions();
    }

    /**
     * Method called by libGDX to draw the map.
     * It draws each map square by iterating through each square in the grid.
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
                    square.hasMapElement() ?
                        resourceManager.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName()) :
                        null;

                if (mapElementTextureRegion != null) {
                    drawTextureRegion(mapElementTextureRegion, row, col, square.getMapElement().computeRotation());
                }
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
        originY = centerY + totalHeight / 2;
    }

    private void drawTextureRegion(TextureRegion textureRegion, int row, int col, float rotation) {
        spriteBatch.draw(
            textureRegion,
            originX + (row * scaledTextureSize),
            originY - (col * scaledTextureSize),
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
