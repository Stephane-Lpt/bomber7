package com.bomber7.core.views;

import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.ResourceManager;

public class ViewMap {

    /** The Grid (https://www.youtube.com/watch?v=lILHEnz8fTk) */
    private LevelMap mapGrid;

    /** The ressourceManager needed for sprites */
    private ResourceManager resourceManager;

    /** Used to draw sprite to screen */
    private SpriteBatch spriteBatch;

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
        Skin skin = resourceManager.getSkin();

        for (int y = 0; y < mapGrid.getHeight(); y++) {
            for (int x = 0; x < mapGrid.getWidth(); x++) {
                Square square = mapGrid.getSquare(x, y);
                String textureName = square.getTextureFilePath().getFileName().toString().replaceFirst("[.][^.]+$", ""); // Obtenez le nom de la texture sans extension
                TextureRegion textureRegion = skin.getRegion(textureName);

                if (textureRegion != null) {
                    // Sauvegardez l'état actuel du TextureRegion
                    boolean flipX = textureRegion.isFlipX();
                    boolean flipY = textureRegion.isFlipY();

                    // Appliquez les retournements nécessaires
                    textureRegion.flip(square.isHorizontalFlip(), square.isVerticalFlip());

                    // Dessinez le TextureRegion
                    spriteBatch.draw(
                        textureRegion,
                        x * textureRegion.getRegionWidth(),
                        y * textureRegion.getRegionHeight(),
                        textureRegion.getRegionWidth() / 2,
                        textureRegion.getRegionHeight() / 2,
                        textureRegion.getRegionWidth(),
                        textureRegion.getRegionHeight(),
                        1, // scaleX
                        1, // scaleY
                        0 // rotation
                    );

                    // Rétablissez l'état original du TextureRegion
                    textureRegion.flip(flipX, flipY);
                }
            }
        }

        spriteBatch.end();
    }

}
