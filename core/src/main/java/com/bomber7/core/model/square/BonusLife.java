package com.bomber7.core.model.square;
import com.bomber7.core.model.entities.HumanPlayer;

public class BonusLife extends Bonus {

    /**
     * Constructs a BonusLife with the specified texture file path.
     *
     *  @param textureName the name of the texture for the bonus
     * @param textureId id of the texture
     * @param verticalFlip whether to flip the texture vertically
     * @param horizontalFlip whether to flip the texture horizontally
     * @param diagonalFlip whether to flip the texture diagonally
     */
    public BonusLife(
        String textureName,
        int textureId,
        boolean verticalFlip,
        boolean horizontalFlip,
        boolean diagonalFlip
    ) {
        super(textureName, verticalFlip, horizontalFlip, diagonalFlip);
    }

    /**
     * Constructs a BonusLife without specifying flip options (false by default).
     * Useful for ids that don't need id_masks.
     *
     * @param textureName the file path to the sprite image for the bonus
     */
    public BonusLife(String textureName) {
        super(textureName);
    }

    @Override
    public void applyBonusEffect(HumanPlayer player) {
        player.addOneLife();
    }
}
