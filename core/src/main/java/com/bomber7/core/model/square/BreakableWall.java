package com.bomber7.core.model.square;

/**
 * Represents a breakable wall in the game.
 */
public class BreakableWall extends Wall {

    /**
     * Constructs a BreakableWall with the specified texture file path.
     *
     * @param textureName the file path to the texture image for the wall
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BreakableWall(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BreakableWall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureName the texture name for this square for the breakable wall
     * @param textureId id of the texture
     */
    public BreakableWall(String textureName, int textureId) {
        super(textureName, textureId);
    }

    @Override
    public String toString() {
        return "BreakableWall{"
            +
                "textureName='"
            +
            getTextureName()
            + '\''
            +
                ", textureId="
            +
            getTextureId()
            +
                ", verticalFlip="
            +
            isVerticalFlip()
            +
                ", horizontalFlip="
            +
            isHorizontalFlip()
            +
                ", diagonalFlip="
            +
            isDiagonalFlip()
            +
                '}';
    }
}
