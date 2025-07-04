package com.bomber7.core.model.square;

import java.util.Random;

import com.bomber7.utils.BonusType;
import com.bomber7.utils.Constants;

/**
 * Represents a breakable wall in the game.
 */
public class BreakableWall extends Wall {

    /**
     * Indicates whether this breakable wall has a bonus.
     * The bonus is randomly assigned when the wall is created.
     */
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

    /**
     * Constructs a BreakableWall without specifying flip options.
     * This constructor uses default flip options (no flips).
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
        if (this.hasBonus) {
            BonusType randomBonusType = BonusType.getRandomBonusType();
            return createBonus(randomBonusType);
        }
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
     * Creates and returns a bonus created based on the given bonusType.
     * @param bonusType the type of the bonus to create
     * @return the created bonus object
     */
    private Bonus createBonus(BonusType bonusType) {
        switch (bonusType) {
            case TRIGGER_BOMB:
                return new BonusTriggerBomb();
            case LIFE:
                return new BonusLife();
            case SPEED:
                return new BonusSpeed();
            case ADD_BOMB:
                return new BonusAddBomb();
            default:
                throw new IllegalArgumentException("Unknown bonus type: " + bonusType.toString());
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
