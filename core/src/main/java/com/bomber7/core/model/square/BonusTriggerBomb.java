package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.HumanPlayer;

public class BonusTriggerBomb extends Bonus {
    /**
     * Constructs a BonusTriggerBomb with the specified texture file path.
     *
     * @param textureFilePath the file path to the texture image for the bonus
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusTriggerBomb(
        String textureFilePath,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureFilePath, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusTriggerBomb without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureFilePath the file path to the sprite image for the bonus
     */
    public BonusTriggerBomb(String textureFilePath) {
        super(textureFilePath);
    }

    /**
     * Applies the bonus effect to the player by changing their bomb type to TRIGGER if they currently use TIME bombs.
     *
     * @param player the player to which the bonus effect will be applied
     */
    @Override
    public void applyBonusEffect(HumanPlayer player) {
        if (player.getBombType() == BombType.TIME) {
            player.setTypeBomb(BombType.TRIGGER);
        }
    }
}
