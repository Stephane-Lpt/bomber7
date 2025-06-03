package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;
import java.nio.file.Path;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */

public class TriggerBomb extends Bomb {

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     * @param power explosion power of the bomb
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param textureFilePath the path to the texture file
     * @param textureId the ID of the texture
     */

    public TriggerBomb(int power, int x, int y, Path textureFilePath, int textureId) {
        super(power, x, y, textureFilePath, textureId);
    }

    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
