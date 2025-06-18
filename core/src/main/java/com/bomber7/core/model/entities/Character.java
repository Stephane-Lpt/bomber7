package com.bomber7.core.model.entities;

import com.badlogic.gdx.math.GridPoint2;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.Constants;
import com.bomber7.utils.GameCharacter;
import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalScoreOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;
import com.bomber7.utils.Score;

/**
 * Classe Character.
 */
public abstract class Character implements Comparable<Character> {

    /** The file path to the character's sprite image. */
    private final GameCharacter gameCharacter;
    /** The name of the character. */
    private final String name;
    /** Indicates whether the character is alive or not. */
    private boolean isAlive;
    /** The map on which the character is currently located. */
    protected LevelMap map;

    /** The speed of the character. */
    private int speed;
    /** The score of the character. */
    private int score;
    /** The life points of the character. */
    private int life;
    /** The X-axis position of the character on the map. */
    private int mapX;
    /** The Y-axis position of the character on the map. */
    private int mapY;
    /** Current X-Axis position of the character. */
    private int x;
    /** Current Y-Axis position of the character. */
    private int y;
    /** Moving status of player, needed for sprite animation. */
    private CharacterState movingStatus;
    /** The spawnpoint (initial position) of the character. */
    private final GridPoint2 spawnPoint;

    /**
     * Character Constructor.
     * @throws IllegalArgumentException in case an argument is not wrong
     * @param name     The name of the character
     * @param map      The map
     * @param mapX        The X-axis position of the character
     * @param mapY        The Y-axis position of the character
     * @param life     The initial life points of the character
     * @param speed    The initial speed of the character
     * @param gameCharacter The game character type
     */
    public Character(String name, LevelMap map, int mapX, int mapY, int life, int speed, GameCharacter gameCharacter) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        if ((mapX < 0) || (mapY < 0)) {
            throw new IllegalPositionOperationException("x or y values can not be negative");
        }
        if (life <= 0) {
            throw new IllegalLifeOperationException("Life initial value must be > 0");
        }
        if (speed <= 0) {
            throw new IllegalSpeedOperationException("Initial speed of character > 0");
        }
        if (gameCharacter == null) {
            throw new IllegalArgumentException("GameCharacter cannot be null");
        }
        this.name = name;
        this.map = map;
        this.spawnPoint = new GridPoint2(mapX, mapY);
        this.mapX = mapX;
        this.mapY = mapY;
        this.x = this.map.getAbsoluteCoordinates(mapX, mapY).getKey();
        this.y = this.map.getAbsoluteCoordinates(mapX, mapY).getValue();
        this.life = life;
        this.speed = speed;
        this.gameCharacter = gameCharacter;
        this.isAlive = true;
        this.movingStatus = CharacterState.STANDING_STILL;
        this.score = Constants.MIN_PLAYER_SCORE;
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Character moving status getter.
     * @return movingStatus Current moving status
     */
    public CharacterState getMovingStatus() {
        return this.movingStatus;
    }

    /**
     * Character name getter.
     * @return name Current name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Character current X-Axis position getter.
     * @return x Current X-position
     */
    public int getPositionX() {
        return this.x;
    }

    /**
     * Character current X-Axis position getter.
     * @return x Current X-position
     */
    public int getMapX() {
        return this.mapX;
    }

    /**
     * Character current Y-Axis position getter.
     * @return y Current Y-position
     */
    public int getPositionY() {
        return this.y;
    }

    /**
     * Character current Y-Axis position getter.
     * @return y Current Y-position
     */
    public int getMapY() {
        return this.mapY;
    }

    /**
     * Character current life getter.
     * @return life Current life
     */
    public int getLife() {
        return this.life;
    }

    /**
     * Character current speed getter.
     * @return speed Current speed
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Character current score getter.
     * @return score Current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Character current sprite path file.
     * @return spriteFP Current sprite path file
     */
    public GameCharacter getGameCharacter() {
        return this.gameCharacter;
    }

    /**
     * Character current map to play on.
     * @return tmpMap Current map
     */
    public LevelMap getMap() {
        LevelMap tmpMap = this.map;
        return tmpMap;
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Character speed setter.
     * @param newSpeed The new speed value of character
     * @throws IllegalSpeedOperationException If new speed value not valid
     *                                        (negative or zero)
     */
    public void setSpeed(int newSpeed) {
        if (newSpeed > 0) {
            this.speed = newSpeed;
        } else {
            throw new IllegalSpeedOperationException("Speed value must be positive.");
        }
    }

    /**
     * Character score setter.
     * @param newScore The new score value of character
     * @throws IllegalScoreOperationException If new score value not valid
     *                                        (negative)
     */
    private void setScore(int newScore) {
        if (newScore >= Constants.MIN_PLAYER_SCORE) {
            this.score = newScore;
        } else {
            throw new IllegalScoreOperationException("Score value must be positive.");
        }
    }

    /**
     * Add a given score to the character.
     * @param score The score points to add to the character
     * @throws IllegalScoreOperationException If new score value not valid
     *                                        (negative)
     */
    public void addScore(int score) {
        int newScore = Math.max(getScore() + score, Constants.MIN_PLAYER_SCORE);
        setScore(newScore);
    }

    public void setMap(LevelMap newMap) {
        this.map = newMap;
    }

    /**
     * Increases the character's life by one.
     */
    public void addOneLife() {
        this.life++;
    }

    /**
     * Decreases the character's life by one. If the life count reaches zero, the
     * character is marked as not alive (dead).
     */
    public void removeOneLife() {
        if (this.life > 0) {
            this.life--;
        } else {
            throw new IllegalStateException("Character cannot have negative life.");
        }
        if (this.life == 0) {
            this.isAlive = false;
            addScore(Score.DEAD);
            this.movingStatus = CharacterState.DEAD;
        }
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Resets a character to its initial state (lives, position, etc...)
     */
    public void reset() {
        this.isAlive = true;
        this.life = 1;
        this.movingStatus = CharacterState.STANDING_STILL;
        this.mapX = spawnPoint.x;
        this.mapY = spawnPoint.y;
        this.x = this.map.getAbsoluteCoordinates(spawnPoint.x, spawnPoint.y).getKey();
        this.y = this.map.getAbsoluteCoordinates(spawnPoint.x, spawnPoint.y).getValue();
    }

    /**
     * Character current state of life.
     * @return boolean Player state of life
     */
    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Caracter is standing still.
     */
    public void setStandingStill() {
        this.movingStatus = CharacterState.STANDING_STILL;
    }

    /**
     * Move character to the right.
     */
    public void moveRight() {
        if(!this.isAlive()) {
            return;
        }

        if (checkMove(getPositionX() + speed, getPositionY())) {
            this.x += speed;
            this.mapX = this.map.getSquareCoordinates(this.x, this.y).getKey();
            this.movingStatus = CharacterState.MOVING_RIGHT;
        }
    }

    /**
     * Move character to the left.
     */
    public void moveLeft() {
        if(!this.isAlive()) {
            return;
        }

        if (checkMove(getPositionX() - speed, getPositionY())) {
            this.x -= speed;
            this.mapX = this.map.getSquareCoordinates(this.x, this.y).getKey();
            this.movingStatus = CharacterState.MOVING_LEFT;
        }
    }

    /**
     * Move character Down.
     */
    public void moveDown() {
        if(!this.isAlive()) {
            return;
        }

        if (checkMove(getPositionX(), getPositionY() - speed)) {
            this.y -= speed;
            this.mapY = this.map.getSquareCoordinates(this.x, this.y).getValue();
            this.movingStatus = CharacterState.MOVING_DOWN;
        }
    }

    /**
     * Move character Up.
     */
    public void moveUp() {
        if(!this.isAlive()) {
            return;
        }

        if (checkMove(getPositionX(), getPositionY() + speed)) {
            this.y += speed;
            this.mapY = this.map.getSquareCoordinates(this.x, this.y).getValue();
            this.movingStatus = CharacterState.MOVING_UP;
        }
    }

    /**
     * Check if the move is possible or not.
     * @param futureX   x-position value
     * @param futureY   y-position value
     * @return boolean
     */
    public boolean checkMove(int futureX, int futureY) {
        // Convert pixel coordinates to map grid coordinates
        int futureMapX = this.map.getSquareCoordinates(futureX, futureY).getKey();
        int futureMapY = this.map.getSquareCoordinates(futureX, futureY).getValue();

        // Check if the center position is outside the map bounds
        if (futureMapX < 0 || futureMapY < 0 || futureMapX >= this.map.getWidth() || futureMapY >= this.map.getHeight()) {
            return false;
        }

        // Define the four corners of the hitbox (top-left, top-right, bottom-left, bottom-right)
        int[][] hitboxCorners = {
            {futureX - Constants.HITBOX_WIDTH / 2, futureY + Constants.HITBOX_HEIGHT / 2}, // top-left corner
            {futureX + Constants.HITBOX_WIDTH / 2, futureY + Constants.HITBOX_HEIGHT / 2}, // top-right corner
            {futureX - Constants.HITBOX_WIDTH / 2, futureY - Constants.HITBOX_HEIGHT / 2}, // bottom-left corner
            {futureX + Constants.HITBOX_WIDTH / 2, futureY - Constants.HITBOX_HEIGHT / 2}  // bottom-right corner
        };

        // Check if all corners are on walkable squares
        for (int[] corner : hitboxCorners) {
            int x = this.map.getSquareCoordinates(corner[0], corner[1]).getKey();
            int y = this.map.getSquareCoordinates(corner[0], corner[1]).getValue();

            // Make sure the corner is within the map bounds
            if (x < 0 || y < 0 || x >= this.map.getWidth() || y >= this.map.getHeight()) {
                return false;
            }

            // If the square at this corner is not walkable, the move is invalid
            Square square = this.map.getSquare(x, y);
            if (!square.isWalkable()) {
                return false;
            }
        }

        // Optionally check the center square as well (can be removed if not needed)
        return this.map.getSquare(futureMapX, futureMapY).isWalkable();
    }

        /**
         * Compares this character to another character based on their score.
         * @param other the character to compare to
         * @return a negative integer if this character has a higher score than the other;
         *         zero if their scores are equal;
         *         a positive integer if this character has a lower score than the other
         */
        @Override
        public int compareTo(Character other) {
            // Descending order (higher score comes first)
            return Integer.compare(other.score, this.score);
        }
}
