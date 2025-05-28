package com.bomber7.core.model.square;

/**
 * Represents a breakable wall in the game.
 */
public class BreakableWall extends Wall {

    /**
     * Constructs a BreakableWall with the specified sprite file path and coordinates.
     *
     * @param spriteFilePath the file path to the sprite image for the wall
     * @param xCoord         the x-coordinate of the wall on the game grid
     * @param yCoord         the y-coordinate of the wall on the game grid
     */
    public BreakableWall(String spriteFilePath, int xCoord, int yCoord) {
        super(spriteFilePath, xCoord, yCoord);
    }

    @Override
    public String toString() {
        return "BreakableWall";
    }
}
