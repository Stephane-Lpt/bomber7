package com.bomber7.core.model.entities;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.GameCharacter;
import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalScoreOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;

/**
 * Classe Character.
 */
public abstract class Character {

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
        this.mapX = mapX;
        this.mapY = mapY;
        this.map = map;
        this.x = this.map.getAbsoluteCoordinates(mapX, mapY).getKey();
        this.y = this.map.getAbsoluteCoordinates(mapX, mapY).getValue();
        this.life = life;
        this.speed = speed;
        this.gameCharacter = gameCharacter;
        this.isAlive = true;
        this.movingStatus = CharacterState.STANDING_STILL;
        this.score = 0;
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
    public void setScore(int newScore) {
        if (newScore > this.score) {
            this.score = newScore;
        } else {
            throw new IllegalScoreOperationException("Score value must be positive.");
        }
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
            this.movingStatus = CharacterState.DIE;
        }
    }

    /**
     * Character current X-Axis position setter.
     * @param newX The new x-position to set
     */
    public void setPositionX(int newX) {
        this.x = newX;
        this.mapX = this.map.getSquareCoordinates(newX, this.y).getKey();
    }

    /**
     * Character current Y-Axis position setter.
     * @param newY The new y-position to set
     * @throws IllegalPositionOperationException If character is moving of more than
     *                                           1 square
     */
    public void setPositionY(int newY) {
        this.y = newY;
        this.mapY = this.map.getSquareCoordinates(this.x, newY).getValue();
    }

    /* ------[OTHER]------------------------------------ */

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
        Gdx.app.debug("STANDING_STILL", this.getName());
        this.movingStatus = CharacterState.STANDING_STILL;
    }

    /**
     * Move character to the right.
     */
    public void moveRight() {
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
        int futureMapX = this.map.getSquareCoordinates(futureX, futureY).getKey();
        int futureMapY = this.map.getSquareCoordinates(futureX, futureY).getValue();

        if (futureMapX < 0 || futureMapY < 0 || futureMapX >= this.map.getWidth() || futureMapY >= this.map.getHeight()) {
            return false; // Out of bounds
        }

        Square square = this.map.getSquare(futureMapX, futureMapY);
        return square.isWalkable();
    }

}
