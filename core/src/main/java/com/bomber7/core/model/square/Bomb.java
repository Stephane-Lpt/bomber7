package com.bomber7.core.model.square;
import com.bomber7.core.model.map.LevelMap;

/**
 * Represents a bomb in the game, which can explode and affect surrounding squares.
 *
 * The bomb has a power that determines the range of its explosion.
 *
 * The Bomb class extends the MapElement class and can be placed on a map where it can
 * later be activated to trigger an explosion that affects adjacent squares on the
 * Level map.
 */
public class Bomb extends MapElement {

    /**
     * The power of the bomb, which determines the range of its explosion.
     */
    private int power;
    /**
     * The X-coordinate of the bomb.
     */
    private int x;
    /**
     * The Y-coordinate of the bomb.
     */
    private int y;

    /**
     * Constructs a Bomb with a specified explosion power and texture file path.
     *
     * @param p power of the bomb, must be a positive integer.
     * @param x X-coordinate of the bomb on the map.
     * @param y Y-coordinate of the bomb on the map.
     * @param textureName The texture name associated with the bomb.
     * //@param verticalFlip    Indicates whether the texture is vertically flipped.
     * //@param horizontalFlip  Indicates whether the texture is horizontally flipped.
     * //@param diagonalFlip    Indicates whether the texture is diagonally flipped.
     */
    public Bomb(int p,
                int x,
                int y,
                String textureName
                ) {

        super(textureName);
        this.power = p;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the power of the bomb.
     * @return  power of the bomb.
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Handles the explosion effect at a specific position on the map.
     * @param m The map where the explosion occurs.
     * @param xCord The X-coordinate.
     * @param yCord The Y-coordinate.
     */
    public void onExplosion(LevelMap m, int xCord, int yCord) {
        Square sq = m.getSquare(xCord, yCord);
        if (sq != null) {
            sq.clearMapElement();
        }
    }

    /**
     * Activates the bomb, causing it to explode and affect surrounding squares.
     * The explosion propagates in all four cardinal directions (up, down, left, right)
     * based on the bomb's power.
     *
     * The propagation stops when it encounters a map boundary, an unbreakable wall, or
     * in the case of a breakable wall, after destroying it.
     *
     * @param m the LevelMap where the bomb is activated.
     * @throws NullPointerException if the LevelMap is null.
     */
    public void activateBomb(LevelMap m) {
        if (m == null) {
            throw new NullPointerException("LevelMap cannot be null");
        }
        // Explosion at the bomb's position
        onExplosion(m, this.x, this.y);

        // Explosion propagation in all four directions
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        for (int[] direction : directions) {

            for (int i = 1; i <= power; i++) {
                int newX = this.x + direction[0] * i;
                int newY = this.y + direction[1] * i;

                if (newX < 0 || newX >= m.getWidth() || newY < 0 || newY >= m.getHeight()) {
                    break;
                }

                Square potentialSquare = m.getSquare(newX, newY);

                // Out of bounds or hit unbreakable wall - stop propagation
                if (potentialSquare == null || potentialSquare.getMapElement() instanceof UnbreakableWall) {
                    break;
                }

                // Hit breakable wall - explode it and stop further propagation
                if (potentialSquare.getMapElement() instanceof BreakableWall) {
                    onExplosion(m, newX, newY);
                    break;
                }
                // Regular propagation
                onExplosion(m, newX, newY);
            }
        }
    }
}
