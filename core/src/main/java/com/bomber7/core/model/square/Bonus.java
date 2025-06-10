package com.bomber7.core.model.square;
import com.bomber7.core.model.entities.HumanPlayer;


import java.nio.file.Path;

public abstract class Bonus extends MapElement {

    /**
     * Constructs a new Bonus with the specified texture file path and texture ID.
     * @param textureName the name of the texture image for this bonus
     * @param textureId the texture ID associated with this bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public Bonus(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a Bonus without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     * @param textureName the file path to the sprite image for the bonus
     */
    public Bonus(String textureName) {
        super(textureName);
    }

    /**
     * Applies the bonus effect to the player or game state.
     * This method should be overridden by subclasses to implement specific bonus effects.
     * 
     * @param player the player to which the bonus effect will be applied
     */
    public void applyBonusEffect(HumanPlayer player) {}

}