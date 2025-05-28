package com.bomber7.core.model.square;

import com.bomber7.core.model.map.LevelMap;

/**
 * Abstract class representing a map element in the game.
 */
public abstract class MapElement {
    /**
     * The file path to the sprite image for this map element.
     * Each element has coordinates and also a sprite.
     */
    private String spriteFilePath;


    /**
     * Constructs a new MapElement with the specified sprite file path.
     *
     * @param spriteFilePath the file path to the sprite image for this map element
     * @throws IllegalArgumentException if the sprite file path is null or empty
     */
    public MapElement(String spriteFilePath) {
        if (spriteFilePath == null || spriteFilePath.isEmpty() || spriteFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path cannot be null or empty");
        }

        this.spriteFilePath = spriteFilePath;
    }

    /**
     * Returns the file path to the sprite image for this map element.
     *
     * @return the sprite file path
     */
    public String getSpriteFilePath() {
        return spriteFilePath;
    }
}
