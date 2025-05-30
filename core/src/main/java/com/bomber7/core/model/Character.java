package com.bomber7.core.model;

import com.bomber7.core.model.map.LevelMap;

/**
 * Abstract class representing a character in the game.
 * A character has a name, position, life points, movement speed,
 * an associated sprite, and is placed on a game map.
 */
public abstract class Character {

    /**
     * The name of the character.
     */
    private String name;

    /**
     * The horizontal position of the character on the map.
     */
    private int xCoord;

    /**
     * The vertical position of the character on the map.
     */
    private int yCoord;

    /**
     * The current life points of the character.
     */
    private int life;

    /**
     * The movement speed of the character.
     */
    private int speed;

    /**
     * The file path to the character's sprite image.
     */
    private String spriteFilePath;

    /**
     * The game map on which the character is located.
     */
    private LevelMap gameMap;

    /**
     * Constructs a new character with the specified attributes.
     *
     * @param name            the name of the character
     * @param xCoord         the initial x-coordinate
     * @param yCoord         the initial y-coordinate
     * @param life            the initial life points
     * @param speed           the movement speed of the character
     * @param spriteFilePath  the file path to the character's sprite image
     * @param gameMap         the map the character is placed on
     */
    public Character(String name,
                     int xCoord,
                     int yCoord,
                     int life,
                     int speed,
                     String spriteFilePath,
                     LevelMap gameMap) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.life = life;
        this.speed = speed;
        this.spriteFilePath = spriteFilePath;
        this.gameMap = gameMap;
        loadSprite();
    }

    /**
     * Moves the character by the specified values if the move is valid.
     *
     * @param x  the change in x-coordinate
     * @param y  the change in y-coordinate
     */
    public void move(int x, int y) {
        if (checkMove(x, y, this.gameMap)) {
            this.xCoord += x;
            this.yCoord += y;
        }
    }

    /**
     * Checks whether a movement is valid on the given map.
     *
     * @param x    the change in x-coordinate
     * @param y    the change in y-coordinate
     * @param map  the map to validate the movement against
     * @return true if the move is allowed, false otherwise
     */
    private boolean checkMove(int x, int y, LevelMap map) {
        //Check if the move is valid
        return true;
    }

    /**
     * Returns the name of the character.
     *
     * @return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x-coordinate of the character.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return xCoord;
    }

    /**
     * Returns the y-coordinate of the character.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return yCoord;
    }

    /**
     * Returns the current life points of the character.
     *
     * @return the life points
     */
    public int getLife() {
        return life;
    }

    /**
     * Returns the movement speed of the character.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Loads the character's sprite from the file path.
     */
    private void loadSprite() {
        // Load the sprite from the file
        // This could be done using a library like JavaFX or Swing
    }
}
