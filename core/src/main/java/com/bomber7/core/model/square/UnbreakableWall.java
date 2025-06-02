package com.bomber7.core.model.square;

import java.nio.file.Path;

/**
 * Represents an unbreakable wall in the game.
 */
public class UnbreakableWall extends Wall {

    /**
     * Constructs an UnbreakableWall with the specified texture file path.
     *
     * @param textureFilePath the file path to the texture image for the unbreakable wall
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public UnbreakableWall(
        Path textureFilePath,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs an UnbreakbleWall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureFilePath the file path to the texture image for the unbreakable wall
     * @param textureId id of the texture
     */
    public UnbreakableWall(Path textureFilePath, int textureId) {
        super(textureFilePath, textureId);
    }

    @Override
    public String toString() {
        return "UnbreakableWall";
    }
}
