package com.bomber7.core.model.square;

/**
 * Abstract class representing a map element in the game.
 */
public abstract class MapElement {
    /**
     * The file path to the sprite image for this map element.
     * Each element has coordinates and also a sprite.
     */
    private String spriteFilePath;
    /**
     * The x-coordinate of the map element on the game grid.
     */
    private int xCoord;
    /**
     * The y-coordinate of the map element on the game grid.
     */
    private int yCoord;

    /**
     * Constructs a new MapElement with the specified attributes.
     *
     * @param spriteFilePath the file path to the sprite image for this map element
     * @param xCoord         the x-coordinate of the map element on the game grid
     * @param yCoord         the y-coordinate of the map element on the game grid
     */
    public MapElement(String spriteFilePath, int xCoord, int yCoord) {
        this.spriteFilePath = spriteFilePath;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
}
