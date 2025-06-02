package com.bomber7.core.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        for (int i = 0; i < mapGrid.length; i++) {
            for (int j = 0; j < mapGrid[i].length; j++) {
                Square square = mapGrid[j][i];
                String spriteFilePath = square.getSpriteFilePath();
                Texture texture = resourceManager.getTexture(spriteFilePath);
                spriteBatch.draw(texture, j * texture.getWidth(), i * texture.getHeight());
            }
        }
        spriteBatch.end();
    }

}
