package com.bomber7.core.model.square;

import com.bomber7.core.model.texture.ElementTexture;

import java.nio.file.Path;

/**
 * Represents a square in the game, which can be a wall, a bomb, or any other future map element.
 */
public class Square extends ElementTexture {
    /**
     * A map element associated with this square (if any). e.g. Bomb, wall, etc.
     */
    private MapElement mapElement;

    /**
     * Constructs a new Square with the specified texture file path and texture ID.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public Square(Path textureFilePath, int textureId) {
        super(textureFilePath, textureId, false, false, false);
        this.mapElement = null; // Initialize mapElement to null if not provided
    }

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the sprite image for this square
     * @param textureId the texture ID associated with this square
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public Square(Path textureFilePath, int textureId, boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
        this.mapElement = null; // Initialize mapElement to null if not provided
    }

    /**
     * Constructs a new Square with the specified texture file path and map element.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @param mapElement the map element associated with this square
     */
    public Square(Path textureFilePath, int textureId, MapElement mapElement) {
        super(textureFilePath, textureId, false, false, false);
        this.mapElement = mapElement;
    }

    /**
     * Constructs a new Square with the specified texture file path and map element.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @param mapElement the map element associated with this square
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public Square(
        Path textureFilePath,
        int textureId,
        MapElement mapElement,
        boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
        this.mapElement = mapElement;
    }

    /**
     * Checks if this square has an associated map element.
     * @return true if there is a map element, false otherwise
     */
    public boolean hasMapElement() {
        return mapElement != null;
    }

    /**
     * Sets the map element associated with this square.
     *
     * @param mapElement the map element to associate with this square
     */
    public void setMapElement(MapElement mapElement) {
        this.mapElement = mapElement;
    }

    /**
     * Clears the map element associated with this square.
     * This method sets the map element to null.
     */
    public void clearMapElement() {
        this.mapElement = null;
    }


    /**
     * Returns the map element associated with this square.
     *
     * @return the map element, or null if there is no associated map element
     */
    public MapElement getMapElement() {
        return mapElement;
    }

    @Override
    public String toString() {
        final int MAX_TRONCATE = 4;
        return "S{"
                +
                "tId=" + this.getTextureId() + '\''
                +
                ", tFP='" + this.getTextureFilePath().toString().substring(0, MAX_TRONCATE) + '\''
                +
                ", mE=" + (mapElement != null ? mapElement.toString().substring(0, MAX_TRONCATE) : "null")
                +
                "} ";
    }
}
