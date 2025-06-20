package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

/**
 * Represents a bonus that grants an additional life to the player.
 * This bonus increases the player's life count, allowing them to survive longer in the game.
 */
public class BonusLife extends Bonus {

    /**
     * Constructs a BonusLife.
     */
    public BonusLife() {
        super(BonusType.LIFE);
    }

    @Override
    public void applyBonusEffect(Player player) {
        player.addOneLife();
    }
}
