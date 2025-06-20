package com.bomber7.core.model.square;

import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.BonusType;

/**
 * Represents a bonus that adds an additional bomb to the player's inventory.
 * This bonus increases the number of bombs a player can place on the map.
 */
public class BonusAddBomb extends Bonus {

    /**
     * Constructs a BonusAddBomb.
     *
     */
    public BonusAddBomb() {
        super(BonusType.ADD_BOMB);
    }

    @Override
    public void applyBonusEffect(Player player) {
        player.setNbBomb(player.getNbBomb() + 1);
    }
}
