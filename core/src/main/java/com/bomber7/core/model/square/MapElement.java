package com.bomber7.core.model.square;

import com.bomber7.core.model.texture.ElementTexture;

/**
 * Abstract class representing a map element in the game.
 */
public abstract class MapElement extends ElementTexture {
    /**
     * Constructs a new Square with the specified texture file path and texture ID.
     * @param textureName the texture name for this square for the mapElement
     * @param textureId the texture ID associated with this square
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId less than -1
     */
    public MapElement(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a MapElement without specifying flip options (false by default). Useful for ids that dont need id_masks.
     * @param textureName the texture name for this square for the map element
     * @param textureId id of the texture
     */
    public MapElement(String textureName, int textureId) {
        super(textureName, textureId, false, false, false);
    }
}
