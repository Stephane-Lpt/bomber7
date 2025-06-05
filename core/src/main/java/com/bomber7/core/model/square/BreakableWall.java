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
     * @param textureName the file path to the texture image for the wall
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BreakableWall(
        String textureName,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
        this.hasBonus = hasRandomBonus();
    }

    /*
     * Constructs a BreakableWall without specifying flip options.
     * This constructor uses default flip options (no flips).
     *
     * @param textureName the texture name for this square for the breakable wall
     */
    public BreakableWall(String textureName) {
        super(textureName);
    }

    public Bonus onDestruction() {
        if (this.hasBonus) {
            return Bonus.createRandomBonus();
        }
    }

    private boolean hasRandomBonus() {
        Random randomValue = new Random().nextDouble();
        if (randomValue > Constants.BONUS_RATE) {
            return false;
        } else {
            return true;
        }
    }

    /** 
     * Instantiates a random bonus based on their probabilities at the same location.
     */
    private static Bonus createRandomBonus() {
        Random randomValue = new Random().nextDouble();
        double cumulativeProbability = 1.0;
        if (randomValue <= cumulativeProbability) {
            // Instantiate the bonus based on the type
            switch (entry.getKey()) {
                case TRIGGER_BOMB:
                    return new BonusTriggerBomb();
                case LIFE:
                    return new BonusLife();
                case SPEED:
                    return new BonusSpeed();
                default:
                    throw new IllegalArgumentException("Unknown bonus type: " + entry.getKey());
            }
        }
    @Override
    public String toString() {
        return "BreakableWall{"
            +
                "textureName='"
            +
            getTextureName()
            + '\''
            +
                ", verticalFlip="
            +
            isVerticalFlip()
            +
                ", horizontalFlip="
            +
            isHorizontalFlip()
            +
                ", diagonalFlip="
            +
            isDiagonalFlip()
            +
                '}';
    }
}
