package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

public class BonusSpeed extends Bonus {

    /**
     * Constructs a BonusSpeed.
     */
    public BonusSpeed() {
        super(BonusType.SPEED);
    }

    @Override
    public void applyBonusEffect(Player player) {
        // Managing displaying the bonus effect
        int currentSpeed = player.getSpeed();
        currentSpeed++;
        player.setSpeed(currentSpeed);
    }
}
