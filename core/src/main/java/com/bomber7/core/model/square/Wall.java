package com.bomber7.core.model.square;

import java.nio.file.Path;

/**
 * Abstract class representing a wall in the game.
 * Useful for defining common properties and behaviors of walls in the future.
 * Useful for the player (just need to check if the square is a wall no matter breakable or not).
 */
public abstract class Wall extends MapElement {

    /**
     * Constructs a Wall with the specified texture file path.
     *
     * @param textureName the file path to the texture image for the wall
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public Wall(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a Wall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureName the texture name for this square for the wall
     * @param textureId id of the texture
     */
    public Wall(String textureName, int textureId) {
        super(textureName, textureId, false, false, false);
    }
}
