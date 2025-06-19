package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;

public class BonusTriggerBomb extends Bonus {
    /**
     * Constructs a BonusTriggerBomb.
     *
     * @param textureName  the name of the texture image for this bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusTriggerBomb(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusTriggerBomb without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureName the name of the texture image for this bonus
     */
    public BonusTriggerBomb(String textureName) {
        super(textureName);
    }

    /**
     * Applies the bonus effect to the player by changing their bomb type to TRIGGER if they currently use TIME bombs.
     *
     * @param player the player to which the bonus effect will be applied
     */
    @Override
    public void applyBonusEffect(Player player) {
        if (player.getBombType() == BombType.TIME) {
            player.setTypeBomb(BombType.TRIGGER);
        }
    }
}
