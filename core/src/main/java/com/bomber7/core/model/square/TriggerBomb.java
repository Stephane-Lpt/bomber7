package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */
public class TriggerBomb extends Bomb {

    /**
     * The texture name associated with the TriggerBomb.
     */
    private static final String textureName = "trigger_bomb";

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     * @param power explosion power of the bomb
     * @param x X-coordinate
     * @param y Y-coordinate
     */

    public TriggerBomb(int power, int x, int y) {
        super(power, x, y, TriggerBomb.textureName);
    }

    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
