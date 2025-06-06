package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */
public class TriggerBomb extends Bomb {

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     * @param power explosion power of the bomb
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param textureName The texture name associated with the bomb.
     */

    public TriggerBomb(int power, int x, int y, String textureName) {
        super(power, x, y, textureName);
    }

    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
