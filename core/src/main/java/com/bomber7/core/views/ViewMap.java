package com.bomber7.core.views;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.ResourceManager;

public class ViewMap {

    /** The Grid (https://www.youtube.com/watch?v=lILHEnz8fTk) */
    private final LevelMap mapGrid;

    /** The ressourceManager needed for sprites */
    private final ResourceManager resourceManager;

    /** Used to draw sprite to screen */
    private final SpriteBatch spriteBatch;

    /**
     * Constructs a new ViewMap with the specified map grid and resource manager.
     * @param mapGrid the 2D array of Square objects representing the map
     * @param resourceManager the ResourceManager to manage textures and resources
     */
    public ViewMap(LevelMap mapGrid, ResourceManager resourceManager) {
        this.mapGrid = mapGrid;
        this.resourceManager = resourceManager;
        this.spriteBatch = new SpriteBatch();
    }

    /**
     * Updates the textures of the map by iterating through each square in the grid.
     */
    public void updateMapTextures() {
        spriteBatch.begin();

        for (int y = 0; y < mapGrid.getHeight(); y++) {
            for (int x = 0; x < mapGrid.getWidth(); x++) {
                Square square = mapGrid.getSquare(x, y);

                String squareTexture = square.getTextureName();
                TextureRegion squareTextureRegion = resourceManager.getMapSkin().getAtlas().findRegion(squareTexture);

                TextureRegion mapElementTextureRegion = null;
                if (square.hasMapElement()) {
                    String mapElementTexture = square.getMapElement().getTextureName();
                    mapElementTextureRegion = resourceManager.getMapSkin().getAtlas().findRegion(mapElementTexture);
                }

                if (squareTextureRegion != null) {
                    // Sauvegardez l'état actuel du squareTextureRegion
                    boolean flipX = squareTextureRegion.isFlipX();
                    boolean flipY = squareTextureRegion.isFlipY();

                    // Appliquez les retournements nécessaires
                    squareTextureRegion.flip(square.isHorizontalFlip(), square.isVerticalFlip());

                    // Dessinez le squareTextureRegion
                    spriteBatch.draw(
                        squareTextureRegion,
                        x * squareTextureRegion.getRegionWidth(),
                        y * squareTextureRegion.getRegionHeight(),
                        squareTextureRegion.getRegionWidth() / 2,
                        squareTextureRegion.getRegionHeight() / 2,
                        squareTextureRegion.getRegionWidth(),
                        squareTextureRegion.getRegionHeight(),
                        1, // scaleX
                        1, // scaleY
                        0 // rotation
                    );

                    // Rétablissez l'état original du squareTextureRegion
                    squareTextureRegion.flip(flipX, flipY);
                }

                if (mapElementTextureRegion != null) {
                    // Sauvegardez l'état actuel du squareTextureRegion
                    boolean flipX = mapElementTextureRegion.isFlipX();
                    boolean flipY = mapElementTextureRegion.isFlipY();

                    // Appliquez les retournements nécessaires
                    mapElementTextureRegion.flip(square.isHorizontalFlip(), square.isVerticalFlip());

                    // Dessinez le mapElementTextureRegion
                    spriteBatch.draw(
                        mapElementTextureRegion,
                        x * mapElementTextureRegion.getRegionWidth(),
                        y * mapElementTextureRegion.getRegionHeight(),
                        mapElementTextureRegion.getRegionWidth() / 2,
                        mapElementTextureRegion.getRegionHeight() / 2,
                        mapElementTextureRegion.getRegionWidth(),
                        mapElementTextureRegion.getRegionHeight(),
                        1, // scaleX
                        1, // scaleY
                        0 // rotation
                    );

                    // Rétablissez l'état original du mapElementTextureRegion
                    mapElementTextureRegion.flip(flipX, flipY);
                }
            }
        }

        spriteBatch.end();
    }

}
