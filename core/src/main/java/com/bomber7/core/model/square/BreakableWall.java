package com.bomber7.core.model.square;

/**
 * Represents a breakable wall in the game.
 */
public class BreakableWall extends Wall {

    /**
     * Constructs a BreakableWall with the specified texture file path.
     *
     * @param textureFilePath the file path to the sprite image for the wall
     * @param textureId id of the texture
     */
    public BreakableWall(String textureFilePath, int textureId) {
        super(textureFilePath, textureId);
    }

    @Override
    public String toString() {
        return "BreakableWall";
    }
}
