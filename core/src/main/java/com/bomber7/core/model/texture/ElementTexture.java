package com.bomber7.core.model.texture;

import com.bomber7.core.model.square.MapElement;

import java.nio.file.Path;

public abstract class ElementTexture {

    public static final int FLIP_H = 0x80000000;
    public static final int FLIP_V = 0x40000000;
    public static final int FLIP_D = 0x20000000;
    public static final int ID_MASK = ~(FLIP_H | FLIP_V | FLIP_D);

    /**
     * The file path to the texture image for this element.
     */
    private Path textureFilePath;
    /**
     * The texture id for this element
     */
    private int textureId;

    private boolean verticalFlip;
    private boolean horizontalFlip;
    private boolean diagonalFlip;

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public ElementTexture(Path textureFilePath, int textureId, boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip) {
        if (textureFilePath == null || textureFilePath.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Texture file path cannot be null or empty. Texture file path: " + textureFilePath + ", texture id: " + textureId);
        }

        if(textureId < -1){
            throw new IllegalArgumentException("Texture id cannot be less than -1. Texture id: " + textureId);
        }

        this.textureFilePath = textureFilePath;
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
    public Path getTextureFilePath() {
        return this.textureFilePath;
    }

    /**
     * Returns the texture id for this element
     * @return the texture id
     */
    public int getTextureId() {
        return textureId;
    }

    public boolean isVerticalFlip() {
        return this.verticalFlip;
    }

    public boolean isHorizontalFlip() {
        return this.horizontalFlip;
    }

    public boolean isDiagonalFlip() {
        return this.diagonalFlip;
    }
}
