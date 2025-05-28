package com.bomber7.core.model.square;

/**
 * Abstract class representing a wall in the game.
 * Useful for defining common properties and behaviors of walls in the future.
 * Useful for the player (just need to check if the square is a wall no matter breakable or not).
 */
public abstract class Wall extends MapElement {

    /**
     * Constructs a Wall with the specified sprite file path and coordinates.
     *
     * @param spriteFilePath the file path to the sprite image for the wall
     * @param xCoord         the x-coordinate of the wall on the game grid
     * @param yCoord         the y-coordinate of the wall on the game grid
     */
    public Wall(String spriteFilePath, int xCoord, int yCoord) {
        super(spriteFilePath, xCoord, yCoord);
    }
}
