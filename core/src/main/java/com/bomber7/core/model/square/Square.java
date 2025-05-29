package com.bomber7.core.model.square;

/**
 * Represents a square in the game, which can be a wall, a bomb, or any other future map element.
 */
public class Square {
    /**
     * The file path to the sprite image for this square.
     */
    private final String textureFilePath;
    private final int textureId;
    /**
     * A map element associated with this square (if any). e.g. Bomb, wall, etc.
     */
    private final MapElement mapElement;

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the sprite image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public Square(String textureFilePath, int textureId) {
        if (textureFilePath == null || textureFilePath.isEmpty() || textureFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path cannot be null or empty");
        }

        this.textureFilePath = textureFilePath;
        this.textureId = textureId;
        this.mapElement = null; // Initialize mapElement to null if not provided
    }

    /**
     * Constructs a new Square with the specified texture file path and map element.
     * @param textureFilePath the file path to the texture image for this square
     * @param mapElement the map element associated with this square
     */
    public Square(String textureFilePath, int textureId, MapElement mapElement) {
        if (textureFilePath == null || textureFilePath.isEmpty() || textureFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path cannot be null or empty");
        }

        this.textureFilePath = textureFilePath;
        this.textureId = textureId;
        this.mapElement = mapElement;
    }

    /**
     * Returns the file path to the texture image for this square.
     *
     * @return the texture file path
     */
    public String getTextureFilePath() {
        return textureFilePath;
    }

    /**
     * Checks if this square has an associated map element.
     * @return true if there is a map element, false otherwise
     */
    public boolean hasMapElement() {
        return mapElement != null;
    }

    /**
     * Returns the map element associated with this square.
     *
     * @return the map element, or null if there is no associated map element
     */
    public MapElement getMapElement() {
        return mapElement;
    }

    public int getTextureId() {
        return textureId;
    }

    @Override
    public String toString() {
        return "S{" +
                "tId=" + textureId + '\'' +
                ", tFP='" + textureFilePath.substring(0,4) + '\'' +
                ", mE=" + (mapElement != null ? mapElement.toString().substring(0, 4) : "null") +
                "} ";
    }
}
