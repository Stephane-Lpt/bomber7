package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

/**
 * Abstract class representing a bonus in the game.
 */
public abstract class Bonus extends MapElement {

    /**
     * The type of the bonus.
     */
    private BonusType bonusType;

    /**
     * Constructs a new Bonus with the specified bonus type.
     * @param bonusType the type of the bonus
     */
    public Bonus(BonusType bonusType) {
        super(bonusType.getAssetName(), false, false, false);
        this.bonusType = bonusType;
    }

    /**
     * Applies the bonus effect to the player or game state.
     * This method should be overridden by subclasses to implement specific bonus effects.
     * @param player the player to which the bonus effect will be applied
     */
    public abstract void applyBonusEffect(Player player);
}
