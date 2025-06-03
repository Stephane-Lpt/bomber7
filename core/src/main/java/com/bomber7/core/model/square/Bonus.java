package com.bomber7.core.model.square;
import com.bomber7.core.model.entities.HumanPlayer;


import java.nio.file.Path;

public abstract class Bonus extends MapElement {

    /**
     * Constructs a new Bonus with the specified texture file path and texture ID.
     * @param textureFilePath the file path to the texture image for this bonus
     * @param textureId the texture ID associated with this bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public Bonus(
        Path textureFilePath,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a Bonus without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     * @param textureFilePath the file path to the sprite image for the bonus
     * @param textureId id of the texture
     */
    public Bonus(Path textureFilePath, int textureId) {
        super(textureFilePath, textureId, false, false, false);
    }

    /**
     * Applies the bonus effect to the player or game state.
     * This method should be overridden by subclasses to implement specific bonus effects.
     * 
     * @param player the player to which the bonus effect will be applied
     */
    public void applyBonusEffect(HumanPlayer player) {}
}