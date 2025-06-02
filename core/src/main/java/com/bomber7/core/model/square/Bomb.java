package com.bomber7.core.model.square;
import com.bomber7.core.model.Character;
import com.bomber7.core.model.map.LevelMap;

import java.nio.file.Path;

/**
 * Represents a bomb in the game, which can explode and affect surrounding squares.
 * The bomb has a power that determines the range of its explosion.
 *
 * The Bomb class extends the Square class and can be placed on a map where it can
 * later be activated to trigger an explosion that affects adjacent squares on the l
 * Level map.
 */

public class Bomb extends MapElement {

    /**
     * The power of the bomb, which determines the range of its explosion.
     */
    private int power;
    private int x,y;

    /**
     * Constructs a Bomb with a specified explosion power and sprite file path.
     * @param p power of the bomb, must be positive
     */
    public Bomb(int p, int x, int y, Path textureFilePath, int textureId, boolean verticalFlip, boolean horizontalFlip, boolean diagonalFlip){
        super(textureFilePath, textureId, verticalFlip, horizontalFlip, diagonalFlip);
        this.power = p;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the power of the bomb.
     * @return  power of the bomb
     */
    public int getPower(){
        return this.power;
    }

    /**
     * Handles the explosion effect at a specific position on the map.
     * This method can eventually be overridden to implement specific explosion effects.
     * @param m map where the explosion occurs
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void onExplosion(LevelMap m, int x, int y) {
        Square sq = m.getSquare(x, y); // Get the square
        sq.clearMapElement();
        // effects
    }
    /**
     * Activates the bomb, causing it to explode and affect surrounding squares.
     * The explosion propagates in all four cardinal directions (up, down, left, right)
     * based on the bomb's power.
     * @param m the LevelMap where the bomb is activated
     */
    public void activateBomb(LevelMap m) {

        // Explosion at the bomb's position
        int bombX = this.x;
        int bombY = this.y;
        onExplosion(m,bombX,bombY);

        // Explosion propagation in all four directions
        int[][] directions = { {0, 1}, {0, -1}, {-1,0}, {1,0}};

        for (int[] direction : directions) {

            for (int i = 1; i <= power; i++) {
                int newX = bombX + direction[0] * i;
                int newY = bombY + direction[1] * i;

                Square potentialSquare = m.getSquare(newX, newY);

                // Out of bounds or hit unbreakable wall - stop propagation
                if (potentialSquare == null || potentialSquare.getMapElement() instanceof UnbreakableWall) {
                    break;
                }

                // Hit breakable wall - explode it and stop further propagation
                if (potentialSquare.getMapElement() instanceof BreakableWall) {
                    onExplosion(m,newX,newY);
                    break;
                }
                // Regular propagation
                onExplosion(m,newX,newY);
            }
        }
    }
}
