package com.bomber7.core.model.square;

/**
 * Abstract class representing a map element in the game.
 */
public abstract class MapElement {
    /**
     * The file path to the sprite image for this map element.
     * Each element has coordinates and also a sprite.
     */
    private String textureFilePath;
    private int textureId;

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public MapElement(String textureFilePath, int textureId) {
        if (textureFilePath == null || textureFilePath.isEmpty() || textureFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Texture file path cannot be null or empty. Texture file path: " + textureFilePath + ", texture id: " + textureId);
        }

        this.textureFilePath = textureFilePath;
        this.textureId = textureId;
    }

    /**
     * Returns the file path to the sprite image for this map element.
     *
     * @return the sprite file path
     */
    public String getSpriteFilePath() {
        return textureFilePath;
    }
}
