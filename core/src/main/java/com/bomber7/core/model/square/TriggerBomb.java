package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;
import java.nio.file.Path;
import com.bomber7.core.model.Character;

/**
 * TriggerBomb is a type of bomb that can be manually activated by the player.
 */

public class TriggerBomb extends Bomb {

    /**
     * Constructs a TriggerBomb with the specified explosion, power, sprite file path and owner.
     */
    public TriggerBomb(int power, int x, int y, Path textureFilePath, int textureId,
                       boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip) {
        super(power, x, y, textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
    }


    /**
     * Manually triggers the bomb to activate its explosion on the specified map.
     * @param map the map on which the bomb is activated
     */
    public void trigger(LevelMap map) {
        activateBomb(map);
    }
}
