package com.bomber7.core.model.square;

/**
 * Represents a square in the game, which can be a wall, a bomb, or any other future map element.
 */
public class Square {
    /**
     * The file path to the sprite image for this square.
     */
    private String spriteFilePath;
    /**
     * A map element associated with this square (if any). e.g. Bomb, wall, etc.
     */
    private MapElement mapElement;

    /**
     * Constructs a new Square with the specified sprite file path.
     * @param spriteFilePath the file path to the sprite image for this square
     */
    public Square(String spriteFilePath) {
        if (spriteFilePath == null || spriteFilePath.isEmpty() || spriteFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path cannot be null or empty");
        }
        this.spriteFilePath = spriteFilePath;
        this.mapElement = null; // Initialize mapElement to null if not provided
    }

    /**
     * Constructs a new Square with the specified sprite file path and map element.
     * @param spriteFilePath the file path to the sprite image for this square
     * @param mapElement the map element associated with this square
     */
    public Square(String spriteFilePath, MapElement mapElement) {
        if (spriteFilePath == null || spriteFilePath.isEmpty() || spriteFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path cannot be null or empty");
        }

        this.spriteFilePath = spriteFilePath;
        this.mapElement = mapElement;
    }

    /**
     * Returns the file path to the sprite image for this square.
     *
     * @return the sprite file path
     */
    public String getSpriteFilePath() {
        return spriteFilePath;
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
}
