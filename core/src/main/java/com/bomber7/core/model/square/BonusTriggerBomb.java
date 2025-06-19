package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

public class BonusTriggerBomb extends Bonus {
    /**
     * Constructs a BonusTriggerBomb.
     */
    public BonusTriggerBomb() {
        super(BonusType.TRIGGER_BOMB);
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
