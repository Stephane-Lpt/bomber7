package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;

public class BonusLife extends Bonus {

    /**
     * Constructs a BonusLife.
     *
     * @param textureName  the name of the texture image for this bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusLife(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusLife without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureName the name of the texture image for this bonus
     */
    public BonusLife(String textureName) {
        super(textureName);
    }

    @Override
    public void applyBonusEffect(Player player) {
        player.addOneLife();
    }
}
