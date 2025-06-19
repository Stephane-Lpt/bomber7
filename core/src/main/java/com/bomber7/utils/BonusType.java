package com.bomber7.utils;

import com.bomber7.core.model.square.Bonus;
import com.bomber7.core.model.square.BonusLife;
import com.bomber7.core.model.square.BonusSpeed;
import com.bomber7.core.model.square.BonusTriggerBomb;

import java.util.Map;
import java.util.Random;

/**
 * An enum representing the different bonuses present in the game.
 *
 */
public enum BonusType {
    /**
     * A trigger bomb.
     */
    TRIGGER_BOMB("trigger_bomb", 0.2f),
    /**
     * A supplementary life.
     */
    LIFE("bonus_heart", 0.4f),
    /**
     * Speed bonus.
     */
    SPEED("bonus_speed", 0.4f);

    /**
     * The asset name for the sprite of the bonus;
     */
    private final String assetName;

    /**
     * The drop rate of the bonus;
     */
    private final float dropRate;

    /**
     * Enum type constructor.
     * @param assetName the name of the asset
     * @param dropRate the drop rate of the bonus
     */
    BonusType(String assetName, float dropRate) {
        this.assetName = assetName;
        this.dropRate = dropRate;
    }

    /**
     * Returns the asset name of the bonus.
     * @return the libgdx asset name of the given bonus
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * Returns the drop rate of the bonus.
     * @return the drop rate of the given bonus
     */
    public float getDropRate() {
        return dropRate;
    }

    /**
     * Returns a randomly chosen bonus type based on bonuses' probability.
     * @return the bonus
     */
    public static BonusType getRandomBonusType() {
        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0.0;
        for (BonusType bonusType : BonusType.values()) {
            cumulativeProbability += bonusType.dropRate;
            if (randomValue <= cumulativeProbability) {
                return bonusType;
            }
        }
        return null; // Should never reach here if probabilities sum to 1.0
    }
}
