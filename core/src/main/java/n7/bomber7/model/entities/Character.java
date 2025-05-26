package n7.bomber7.model.entities;

import n7.bomber7.model.exception.*;

/**
 * Classe Charater
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Character {

    protected final Integer MINLIFE = 1;
    protected final Integer MAXLIFE = 3;
    protected final Integer MINSPEED = 1;
    protected final Integer MAXSPEED = 5;
    private final String name;
    private int speed;
    private int life;
    private int x;
    private int y;

    /**
     * Character Constructor
     */
    public Character(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Character name getter
     * @return tmpName Current name
     */
    public String getName(){
        String tmpName = this.name;
        return tmpName;
    }

    /**
     * Character Ccurrent speed getter 
     * @return tmpSpeed Current speed
     */
    public Integer getSpeed(){
        int tmpSpeed = this.speed;
        return tmpSpeed;
    }

    /**
     * Character current life getter 
     * @return tmpLife Current life
     */
    public Integer getLife(){
        int tmpLife = this.life;
        return tmpLife;
    }

    /**
     * Character current X-Axis position getter 
     * @return tmpX Current X-position
     */
    public Integer getPositionX(){
        int tmpX = this.x;
        return tmpX;
    }

    /**
     * Character current Y-Axis position getter 
     * @return tmpY Current Y-position
     */
    public Integer getPositionY(){
        int tmpY = this.y;
        return tmpY;
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Character speed setter
     * @param newSpeed The new speed value of character
     * @throws IllegalSpeedOperationException If new speed value not in range of MIN-MAX value
     */
    public void setSpeed(int newSpeed){
        if ((newSpeed >= this.MINSPEED) && (newSpeed <= this.MAXSPEED)){
            this.life = newSpeed;
        } else {
            throw new IllegalSpeedOperationException(this.MINLIFE + "<= speed_value_to_set <=" + this.MAXLIFE);
        }
    }

    /**
     * Character life setter
     * @param newLife The new life value of character
     * @throws IllegalLifeOperationException If new life value not in range of MIN-MAX value
     */
    public void setLife(int newLife){
        if ((newLife >= this.MINLIFE) && (newLife <= this.MAXLIFE)){
            this.life = newLife;
        } else {
            throw new IllegalLifeOperationException(this.MINLIFE + "<= life_value_to_set <=" + this.MAXLIFE);
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
