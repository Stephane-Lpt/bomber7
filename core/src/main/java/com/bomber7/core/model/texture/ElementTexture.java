package com.bomber7.core.model.texture;

import com.bomber7.core.model.square.MapElement;

import java.nio.file.Path;

public abstract class ElementTexture {
    /**
     * The file path to the texture image for this element.
     */
    private Path textureFilePath;
    /**
     * The texture id for this element
     */
    private int textureId;

    /**
     * Constructs a new Square with the specified sprite file path and texture ID.
     * @param textureFilePath the file path to the texture image for this square
     * @param textureId the texture ID associated with this square
     * @throws IllegalArgumentException if the sprite file path is null or empty or textureId < -1
     */
    public ElementTexture(Path textureFilePath, int textureId) {
        if (textureFilePath == null || textureFilePath.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Texture file path cannot be null or empty. Texture file path: " + textureFilePath + ", texture id: " + textureId);
        }

        this.textureFilePath = textureFilePath;
        this.textureId = textureId;
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
}
