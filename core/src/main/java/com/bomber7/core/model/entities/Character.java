package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;

/**
 * Classe Charater
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Character {

    private final String name;
    private boolean isAlive;
    private int speed;
    private int life;
    private int x;
    private int y;

    /**
     * Character Constructor
     */
    public Character(String name, int x, int y){
        this.isAlive = true;
        this.name = name;
        this.x = x;
        this.y = y;
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
     * Character Ccurrent speed getter
     * @return speed Current speed
     */
    public Integer getSpeed(){
        return this.speed;
    }

    /**
     * Character current life getter
     * @return life Current life
     */
    public Integer getLife(){
        return this.life;
    }

    /**
     * Character current state of life
     * @return boolean Player state of life
     */
    public boolean getIsAlive(){
        if ((this.isAlive == true) && (this.life > 0)){
            return true;
        } else {
            this.isAlive = false;
            this.life = 0;
            return false;
        }

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
     * This method increments the current life count of the character by one.
     */
    public void addOneLife() {
        this.life++;
    }

    /**
     * Decreases the character's life by one.
     * This method decrements the current life count of the character by one.
     * If the life count reaches zero, the character is marked as not alive.
     */
    public void removeOneLife() {
        if (this.life > 0) {
            this.life--;
        }
        if (this.life == 0) {
            this.isAlive = false;
        }
    }


    /**
     * Character current X-Axis position setter
     * @param newX The new x-position to set
     * @throws IllegalPositionOperationException If character is moving of more than 1 square
     */
    public void setPositionX(int newX){
        if ((newX == this.x-1) || (newX == this.x) || (newX == this.x+1)) {
            this.x = newX;
        } else {
            throw new IllegalPositionOperationException("Character can't move more than 1 square at a time.");
        }
    }

    /**
     * Character current Y-Axis position setter
     * @param newY The new y-position to set
     * @throws IllegalPositionOperationException If character is moving of more than 1 square
     */
    public void setPositionY(int newY){
        if ((newY == this.x-1) || (newY == this.x) || (newY == this.x+1)) {
            this.x =  newY;
        } else {
            throw new IllegalPositionOperationException("Character can't move more than 1 square at a time.");
        }
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Moving the character on the map
     * @param x X-Axis position value to set
     * @param y Y-Axis position value to set
     * @throws IllegalPositionOperationException
     */
    public void move(int x, int y) throws IllegalPositionOperationException {
        setPositionX(x);
        setPositionY(y);
    }

}
