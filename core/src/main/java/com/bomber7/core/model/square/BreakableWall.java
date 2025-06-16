package com.bomber7.core.model.square;

import java.util.Map;
import java.util.Random;

import com.bomber7.utils.Constants;
import com.bomber7.utils.Constants.BONUS_TYPE;

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
        this.hasBonus = hasRandomBonus();
    }

    /**
     * Returns the texture file path for the breakable wall.
     *
     * @return the texture file path
     */
    public Bonus onDestruction() {
        //System.out.println(this.hasBonus);
        if (this.hasBonus) {
            return createRandomBonus();
        }
        System.out.println("onDestruction retroune null");
        return null;
    }

    /**
     * Checks if the breakable wall has a bonus.
     *
     * @return true if the wall has a bonus, false otherwise
     */
    private boolean hasRandomBonus() {
        double randomValue = new Random().nextDouble();
        return randomValue <= Constants.BONUS_RATE;
    }

    /**
     * Instantiates a random bonus based on their probabilities at the same location.
     */
    private static Bonus createRandomBonus() {
        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0.0;
        for (Map.Entry<BONUS_TYPE, Double> entry : Constants.BONUS_PROBABILITIES.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomValue <= cumulativeProbability) {
                // Instantiate the bonus based on the type
                switch (entry.getKey()) {
                    case TRIGGER_BOMB:
                        return new BonusTriggerBomb("bomb");
                    case LIFE:
                        return new BonusLife("life");
                    case SPEED:
                        return new BonusSpeed("speed");
                    default:
                        throw new IllegalArgumentException("Unknown bonus type: " + entry.getKey());
                }
            }
        }
        return null; // Should never reach here if probabilities sum to 1.0
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
