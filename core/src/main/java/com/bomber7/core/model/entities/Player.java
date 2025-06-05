/**
 * Classe Player.
 */

package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalBombOperationException;
import com.bomber7.core.model.exceptions.IllegalPowerOperationException;

import java.util.ArrayList;
import java.util.List;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.BombType;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TriggerBomb;
import com.bomber7.core.model.square.TimeBomb;

/**
 * Class Player.
 */
public abstract class Player extends Character {

    /**
     * List of trigger bombs dropped by the player.
     */
    private final List<TriggerBomb> triggerBombsDropped;
    /**
     * Type of bomb used by the player.
     */
    private BombType typeBomb;
    /**
     * Number of bombs the player can drop.
     */
    private int nbBomb;
    /**
     * Power for the future bombs.
     */
    private int power;


    /**
     * Player Constructor.
     * @param name     Name of the player
     * @param map      Map
     * @param x        X coordinate of the player
     * @param y        Y coordinate of the player
     * @param life     Life points of the player
     * @param speed    Speed of the player
     * @param spriteFP File path to the player's sprite
     */
    public Player(String name, LevelMap map, int x, int y, int life, int speed, String spriteFP) {
        super(name, map, x, y, life, speed, spriteFP);
        this.nbBomb = 1;
        this.power = 1;
        this.typeBomb = BombType.TIME; // Default bomb type is TIME
        this.triggerBombsDropped = new ArrayList<>();
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Player number of bomb playable getter.
     * @return nbBomb Current number of bomb playable
     */
    public int getNbBomb() {
        return this.nbBomb;
    }

    /**
     * Player number of bomb playable getter.
     * @return typeBomb Current number of bomb playable
     */
    public BombType getBombType() {
        return this.typeBomb;
    }

    /**
     * Player list of dropped bombs getter.
     * @return droppedBombs Current list of dropped bombs
     */
    public int getNbTriggeredBombDropped() {
        return this.triggerBombsDropped.size();
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Player number of bomb playable setter.
     * @param newNbBomb The new nbBomb value
     * @throws IllegalBombOperationException If new number of bomb is >= 0
     */
    public void setNbBomb(int newNbBomb) {
        if (newNbBomb >= 0) {
            this.nbBomb = newNbBomb;
        } else {
            throw new IllegalBombOperationException("nb_bomb_to_set >= 0");
        }
    }

    /**
     * Player type of bomb playable setter.
     * @param bombType The new type of bomb
     */
    public void setTypeBomb(BombType bombType) {
        this.typeBomb = bombType;
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Allow the Player to drop a bomb.
     * @return true if the bomb was successfully dropped, false otherwise
     */
    public boolean dropBomb() {
        if (nbBomb >= 1) {
            int currX = this.getPositionX(); // get our current X position
            int currY = this.getPositionY(); // get our current Y position
            Square currentSquare = this.map.getSquare(currX, currY);
            Bomb bombToDrop;
            switch (this.typeBomb) {
                case TRIGGER:
                    bombToDrop = new TriggerBomb(power, currX, currY);
                    this.triggerBombsDropped.add((TriggerBomb) bombToDrop); // Add it to the trigger bombs dropped list
                    break;
                case TIME:
                    bombToDrop = new TimeBomb(power, currX, currY);
                    break;
                default:
                    bombToDrop = null;
            }
            currentSquare.setMapElement(bombToDrop);
            this.nbBomb--; // Decrease the number of bombs available
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allow the Player to activate all trigger bomb that he dropped.
     */
    public void activateAllTriggerBombs() {
        for (TriggerBomb bomb : this.triggerBombsDropped) {
            bomb.activateBomb(this.map);
        }
        this.triggerBombsDropped.clear();
    }

    /**
     * Gets the power of the player's bombs.
     * @return The current power of the bombs
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the player's bombs.
     * @param power The new power to set for the bombs
     * @throws IllegalPowerOperationException If the power is less than 0
     */
    public void setPower(int power) throws IllegalPowerOperationException {
        if (power >= 0) {
            this.power = power;
        } else {
            throw new IllegalPowerOperationException("power_to_set >= 0");
        }
    }

    /**
     * Increases the player's bomb power by 1.
     * This method is used to enhance the explosion radius of the bombs dropped by the player.
     */
    public void addPower() {
        this.power++;
    }

}
