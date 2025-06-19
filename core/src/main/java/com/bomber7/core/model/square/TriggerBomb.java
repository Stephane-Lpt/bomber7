package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.entities.Character;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */
public class TriggerBomb extends Bomb {

    /**
     * The texture name prefix associated with the TriggerBomb.
     */
    public static final String TEXTURE_PREFIX = "trigger_bomb";

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     * @param power explosion power of the bomb
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param c the character that planted this bomb
     */

    public TriggerBomb(int power, int x, int y, Character c) {
        super(power, x, y, TriggerBomb.TEXTURE_PREFIX, c);
    }

    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
