package com.bomber7.core.model.square;

/**
 * Represents an unbreakable wall in the game.
 */
public class UnbreakableWall extends Wall {

    /**
     * Constructs an UnbreakableWall with the specified texture file path.
     *
     * @param textureName the file path to the texture image for the unbreakable wall
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public UnbreakableWall(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs an UnbreakbleWall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureName the texture name for this square for the wall for the unbreakable wall
     * @param textureId id of the texture
     */
    public UnbreakableWall(String textureName, int textureId) {
        super(textureName, textureId);
    }

    @Override
    public String toString() {
        return "UnbreakableWall{"
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
