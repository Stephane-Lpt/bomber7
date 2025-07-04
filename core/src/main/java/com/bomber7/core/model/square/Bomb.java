package com.bomber7.core.model.square;
import com.bomber7.core.model.entities.Player;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.entities.Character;
import com.bomber7.utils.Effect;
import com.bomber7.utils.EffectType;
import com.bomber7.utils.Score;
import com.bomber7.utils.SoundManager;
import com.bomber7.utils.SoundType;

import java.util.List;

/**
 * Represents a bomb in the game, which can explode and affect surrounding squares.
 * The bomb has a power that determines the range of its explosion.
 * The Bomb class extends the MapElement class and can be placed on a map where it can
 * later be activated to trigger an explosion that affects adjacent squares on the
 * Level map.
 */
public abstract class Bomb extends MapElement {

    /**
     * Constants representing different types of bombs.
     * CLASSIC_BOMB is a standard bomb that explodes after being placed.
     */
    public static final int CLASSIC_BOMB = 0;
    /**
     * TRIGGER_BOMB is a bomb that can be manually triggered by the player.
     */
    public static final int TRIGGER_BOMB = 1;

    /**
     * boolean indicating whether the bomb has already exploded.
     */
    private boolean exploded = false;

    /**
     * The power of the bomb, which determines the range of its explosion.
     */
    private final int power;
    /**
     * The X-coordinate of the bomb.
     */
    private final int x;
    /**
     * The Y-coordinate of the bomb.
     */
    private final int y;
    /**
     * The character that planted this bomb.
     */
    private final Character planter;

    /**
     * Constructs a Bomb with a specified explosion power and texture file path.
     *
     * @param p power of the bomb, must be a positive integer.
     * @param x X-coordinate of the bomb on the map.
     * @param y Y-coordinate of the bomb on the map.
     * @param textureName The texture name associated with the bomb.
     * @param c the character that planted the bomb.
     * //@param verticalFlip    Indicates whether the texture is vertically flipped.
     * //@param horizontalFlip  Indicates whether the texture is horizontally flipped.
     * //@param diagonalFlip    Indicates whether the texture is diagonally flipped.
     */
    public Bomb(int p,
                int x,
                int y,
                String textureName,
                Character c
                ) {

        super(textureName);
        this.power = p;
        this.x = x;
        this.y = y;
        this.planter = c;
    }

    /**
     * Returns the power of the bomb.
     * @return  power of the bomb.
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Returns the X-coordinate of the bomb.
     * @return the X-coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the Y-coordinate of the bomb.
     * @return the Y-coordinate.
     */
    public int getY() {
        return this.y;
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
//            Gdx.app.debug("Bomb", "MapElement before explosion: " + sq.getMapElement());
            if (!(sq.getMapElement() instanceof Bonus)) {
                sq.clearMapElement();
                m.addEffect(new Effect(
                    xCord,
                    yCord,
                    EffectType.EXPLOSION
                ));
            }
//            Gdx.app.debug("Bomb", "MapElement after explosion: " + sq.getMapElement());
//            Gdx.app.debug("Bomb", "instanceOfBonus: " + (sq.getMapElement() instanceof Bonus));
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

        if (exploded) {
            return;
        }
        exploded = true;

        // Explosion at the bomb's position
        onExplosion(m, this.x, this.y);
        if (planter instanceof Player) {
            ((Player) planter).setNbBomb(((Player) planter).getNbBomb() + 1);
        }
        this.playExplosionSound();

        // Explosion propagation in all four directions
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        // Retrieve all characters currently on the Map.
        List<Character> characters = m.getCharacters();

        for (int[] direction : directions) {

            for (int i = 1; i <= power; i++) {
                int newX = this.x + direction[0] * i;
                int newY = this.y + direction[1] * i;

                // Check if the explosion reaches a character
                for (Character character : characters) {
                    if (character.isAlive()) {
                        if (
                            (character.getMapX() == newX && character.getMapY() == newY)
                            || character.getMapX() == this.x && character.getMapY() == this.y
                        ) {
                            if (character == planter) {
                                //Gdx.app.debug("Bomb", planter.getName() + " suicided.");
                                character.addScore(Score.SUICIDE);
                            } else {
                                //Gdx.app.debug("Bomb", planter.getName() + " killed " + character);
                                planter.addScore(Score.KILL);
                            }
                            character.removeOneLife();
                        }
                    }
                }

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
//                    break;
                }

                if (potentialSquare.getMapElement() instanceof Bomb) {
                    // If the square has another bomb, we can propagate the explosion to it
                    Bomb otherBomb = (Bomb) potentialSquare.getMapElement();
                    otherBomb.activateBomb(m);
                    break;
                }

                // Regular propagation
                onExplosion(m, newX, newY);
            }
        }
    }

    /**
     * Play explosion sound.
     * It is a distinct method so that it can be removed in tests later.
     */
    public void playExplosionSound() {
        SoundManager.getInstance().play(SoundType.EXPLOSION);
    }
}
