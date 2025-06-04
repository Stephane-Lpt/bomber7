package com.bomber7.core.model.texture;

import com.bomber7.core.model.square.Square;

import java.nio.file.Path;

/**
 * Abstract class representing a texture for an element in the game.
 */
public abstract class ElementTexture {

    /**
     * FLIP_H - Flip Mask horizontally.
     */
    public static final int FLIP_H = 0x80000000;
    /**
     * FLIP_V - Flip Mask vertically.
     */
    public static final int FLIP_V = 0x40000000;
    /**
     * FLIP_D - Flip Mask diagonally.
     */
    public static final int FLIP_D = 0x20000000;
    /**
     * ID_MASK - Mask to extract the texture ID from the combined flags.
     * This mask is used to isolate the right texture ID from the flip flags.
     */
    public static final int ID_MASK = ~(FLIP_H | FLIP_V | FLIP_D);

    /**
     * The texture image for this element.
     */
    private final String textureName;
    /**
     * The texture id for this element.
     */
    private final int textureId;

    /**
     * Whether to flip the texture vertically.
     */
    private final boolean verticalFlip;
    /**
     * Whether to flip the texture horizontally.
     */
    private final boolean horizontalFlip;
    /**
     * Whether to flip the texture diagonally.
     */
    private final boolean diagonalFlip;

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureName the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId less than -1
     */
    public ElementTexture(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        if (textureName == null || textureName.trim().isEmpty()) {
            throw new IllegalArgumentException("Texture file path cannot be null or empty. Texture name: "
                + textureName
                + ", texture id: "
                + textureId
            );
        }

        if (textureId < -1) {
            throw new IllegalArgumentException("Texture id cannot be less than -1. Texture id: " + textureId);
        }

        this.textureName = textureName;
        this.textureId = textureId;
        this.verticalFlip = verticalFlip;
        this.horizontalFlip = horizontalFlip;
        this.diagonalFlip = diagonalFlip;
    }

    /**
     * Returns the file path to the texture image for this element.
     *
     * @return the texture file path
     */
    public String getTextureName() {
        return this.textureName;
    }

    /**
     * Returns the texture id for this element.
     * @return the texture id
     */
    public int getTextureId() {
        return textureId;
    }

    /**
     * Checks if the texture should be flipped vertically.
     * @return true if the texture should be flipped vertically, false otherwise
     */
    public boolean isVerticalFlip() {
        return this.verticalFlip;
    }

    /**
     * Checks if the texture should be flipped horizontally.
     * @return true if the texture should be flipped horizontally, false otherwise
     */
    public boolean isHorizontalFlip() {
        return this.horizontalFlip;
    }

    /**
     * Checks if the texture should be flipped diagonally.
     * @return true if the texture should be flipped diagonally, false otherwise
     */
    public boolean isDiagonalFlip() {
        return this.diagonalFlip;
    }

    /**
     * Calculate the rotation base on this elements flip flags.
     * @return the angle of rotation in degrees
     */
    public float computeRotation() {
        if (!isHorizontalFlip() && !isVerticalFlip()) return 0f;
        if (!isHorizontalFlip() && isVerticalFlip())  return 90f;
        if (isHorizontalFlip() && !isVerticalFlip())  return -90f;
        if (isHorizontalFlip() && isVerticalFlip())   return 180f;
        return 0f;
    }
}
