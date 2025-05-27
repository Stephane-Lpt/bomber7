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
     * @param spriteFilePath
     */
    public Square(String spriteFilePath) {
        this.spriteFilePath = spriteFilePath;
    }

    /**
     * Constructs a new Square with the specified sprite file path and map element.
     * @param spriteFilePath the file path to the sprite image for this square
     * @param mapElement the map element associated with this square
     */
    public Square(String spriteFilePath, MapElement mapElement) {
        this.spriteFilePath = spriteFilePath;
        this.mapElement = mapElement;
    }

}
