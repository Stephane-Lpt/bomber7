package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.HumanPlayer;

public class BonusAddBomb extends Bonus {

    /**
     * Constructs a BonusAddBomb with the specified texture file path.
     *
     * @param textureName the file path to the texture image for the bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusAddBomb(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusAddBomb without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureName the file path to the sprite image for the bonus
     */
    public BonusAddBomb(String textureName) {
        super(textureName);
    }

    @Override
    public void applyBonusEffect(HumanPlayer player) {
        player.setNbBomb(player.getNbBomb() + 1);
    }

}
