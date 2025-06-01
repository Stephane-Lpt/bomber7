package com.bomber7.core.model.square;

import java.nio.file.Path;

/**
 * Represents an unbreakable wall in the game.
 */
public class UnbreakableWall extends Wall {

    /**
     * Constructs an UnbreakableWall with the specified texture file path.
     *
     * @param textureFilePath the file path to the sprite image for the unbreakable wall
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
