package com.bomber7.core.model.square;

import java.nio.file.Path;
import java.util.Random;

import com.bomber7.utils.Constants;

/**
 * Represents a breakable wall in the game.
 */
public class BreakableWall extends Wall {

    private boolean hasBonus;

    /**
     * Constructs a BreakableWall with the specified texture file path.
     *
     * @param textureFilePath the file path to the texture image for the wall
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BreakableWall(
        Path textureFilePath,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
        this.hasBonus = hasRandomBonus();
    }

    /*
     * Constructs a BreakableWall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureFilePath the file path to the sprite image for the wall
     * @param textureId id of the texture
     */
    public BreakableWall(Path textureFilePath, int textureId) {
        super(textureFilePath, textureId);
    }

    public Bonus onDestruction() {
        if (this.hasBonus) {
            return Bonus.createRandomBonus();
        }
    }

    private boolean hasRandomBonus() {
        Random randomGenerator = new Random();
        double randomValue = randomGenerator.nextDouble();
        if (randomValue > Constants.BONUS_RATE) {
            return false;
        } else {
            return true;
        }
    }

    private static Bonus createRandomBonus() {
        
        return pickedBonus
    }
}
