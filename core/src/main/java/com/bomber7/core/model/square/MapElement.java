package com.bomber7.core.model.square;

import com.bomber7.core.model.texture.ElementTexture;

import java.nio.file.Path;

/**
 * Abstract class representing a map element in the game.
 */
public abstract class MapElement extends ElementTexture {
    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public MapElement(Path textureFilePath, int textureId, boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }
}
