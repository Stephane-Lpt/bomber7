package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;

public class BonusSpeed extends Bonus {

    /**
     * Constructs a BonusSpeed.
     *
     * @param textureName  the name of the texture image for this bonus
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusSpeed(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusSpeed without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureName the name of the texture image for this bonus
     */
    public BonusSpeed(String textureName) {
        super(textureName);
    }

    @Override
    public void applyBonusEffect(Player player) {
        // Managing displaying the bonus effect
        int currentSpeed = player.getSpeed();
        currentSpeed++;
        player.setSpeed(currentSpeed);
    }
}
