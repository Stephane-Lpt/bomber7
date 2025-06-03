package com.bomber7.core.model.square;

import java.nio.file.Path;

public class BonusSpeed extends Bonus {

    /**
     * Constructs a BonusSpeed with the specified texture file path.
     *
     * @param textureFilePath the file path to the texture image for the bonus
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusSpeed(
        Path textureFilePath,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }

    @Override
    public void applyBonusEffect(HumanPlayer player) {
        // Managing displaying the bonus effect
        int currentSpeed = player.getSpeed();
        currentSpeed ++;
        player.setSpeed(currentSpeed);
    }
    
}
