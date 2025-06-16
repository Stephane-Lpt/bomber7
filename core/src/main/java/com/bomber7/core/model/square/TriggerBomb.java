package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */
public class TriggerBomb extends Bomb {

    /**
     * The texture name prefix associated with the TriggerBomb.
     */
    public static final String TEXTURE_PREFIX = "trigger-bomb";

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     * @param power explosion power of the bomb
     * @param x X-coordinate
     * @param y Y-coordinate
     */

    public TriggerBomb(int power, int x, int y) {
        super(power, x, y, TriggerBomb.TEXTURE_PREFIX + "-1");
    }

    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
