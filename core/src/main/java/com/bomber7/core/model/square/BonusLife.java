package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

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
