package com.bomber7.core.model.entities;

import com.bomber7.core.model.LevelMap;
import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;

/**
 * Classe Charater
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Character {

    private final String spriteFP;
    private final String name;
    private boolean isAlive;
    private LevelMap map; 
    private int speed;
    private int life;
    private int x;
    private int y;

    /**
     * Character Constructor
     * @throws IllegalArgumentException in case an argument is not wrong
     */
    public Character(String name, int x, int y, int life, int speed, String spriteFP){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        if ((x < 0) || (y < 0)){
            throw new IllegalPositionOperationException("x or y values can not be negative");
        }
        if (life <= 0) {
            throw new IllegalLifeOperationException("Life initial value must be > 0");
        }
        if (speed <= 0) {
            throw new IllegalSpeedOperationException("Initial speed of character > 0");
        }
        if (spriteFP == null || spriteFP.trim().isEmpty()) {
            throw new IllegalArgumentException("Sprite file path can't be null or empty");
        }
        this.name = name;
        this.x = x;
        this.y = y;
        this.life = life;
        this.speed = speed;
        this.spriteFP = spriteFP;
        this.isAlive = true;
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Character name getter
     * @return name Current name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Character current X-Axis position getter
     * @return x Current X-position
     */
    public Integer getPositionX(){
        return this.x;
    }

    /**
     * Character current Y-Axis position getter
     * @return y Current Y-position
     */
    public Integer getPositionY(){
        return this.y;
    }

    /**
     * Character current life getter
     * @return life Current life
     */
    public Integer getLife(){
        return this.life;
    }

    /**
     * Character current speed getter
     * @return speed Current speed
     */
    public Integer getSpeed(){
        return this.speed;
    }

    /**
     * Character current sprite path file 
     * @return spriteFP Current sprite path file 
     */
    public String getSpriteFP(){
        return this.spriteFP;
    }

    /**
     * Character current map to play on
     * @return tmpMap Current map
     */
    public LevelMap getMap(){
        LevelMap tmpMap = this.map; 
        return tmpMap;
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Character speed setter
     * @param newSpeed The new speed value of character
     * @throws IllegalSpeedOperationException If new speed value not in range of MIN-MAX value
     */
    public void setSpeed(int newSpeed){
        if (newSpeed > 0){
            this.speed = newSpeed;
        }
        else {
            throw new IllegalSpeedOperationException("Speed value must be positive.");
        }
    }

    /**
     * Increases the character's life by one.
     */
    public void addOneLife(){
        this.life++;
    }

    /**
     * Decreases the character's life by one.
     * If the life count reaches zero, the character is marked as not alive (dead).
     */
    public void removeOneLife(){
        if (this.life > 0) {
            this.life--;
        }
        else {
            throw new IllegalStateException("Character cannot have negative life.");
        }
        if (this.life == 0) {
            this.isAlive = false;
        }
    }

    /**
     * Character current X-Axis position setter
     * @param newX The new x-position to set
     */
    public void setPositionX(int newX){
        this.x = newX;
    }

    /**
     * Character current Y-Axis position setter
     * @param newY The new y-position to set
     * @throws IllegalPositionOperationException If character is moving of more than 1 square
     */
    public void setPositionY(int newY){
        this.x =  newY;
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Character current state of life
     * @return boolean Player state of life
     */
    public boolean isAlive(){
        return this.isAlive;
    }

    /**
     * Move character to the right
     */
    public void moveRight(int x, int y, LevelMap map){
        if (checkMove(x, y, map)) {
            this.x++;
        }
    }

    /**
     * Move character to the right
     */
    public void moveLeft(int x, int y, LevelMap map){
        if (checkMove(x, y, map)) {
            this.x--;
        }
    }

    /**
     * Move character Down
     */
    public void moveDown(int x, int y, LevelMap map){
        if (checkMove(x, y, map)) {
            this.y++;
        }
    }

    /**
     * Move character Up
     */
    public void moveUp(int x, int y, LevelMap map){
        if (checkMove(x, y, map)) {
            this.y--;
        }
    }

    /**
     * Check if the move is possible or not
     * @param x x-position value
     * @param y y-position value
     * @param map The map to check
     * @retrun boolean 
     */
    public boolean checkMove(int x, int y, LevelMap map){
        //TODO : I don't know yet how to do that without the LevelMap Class Implemented
        return true;
    }

}
