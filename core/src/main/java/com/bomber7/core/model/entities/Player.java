/**
 * Classe Player.
 */

package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalBombOperationException;

import java.util.List;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.*;

/**
 * Class Player.
 */
public abstract class Player extends Character {

    /**
     * List of dropped bombs by the player.
     */
    private final List<Bomb> droppedBombs;
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
        this.typeBomb = BombType.TIME; // Default bomb type is TIME
        this.droppedBombs = new java.util.ArrayList<>();
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
    public int getNbDroppedBomb() {
        return this.droppedBombs.size();
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
     * @param newTypeBomb The new type of bomb
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
        if (nbBomb>1) {
            int currX = this.getPositionX(); // get our current X position
            int currY = this.getPositionY(); // get our current Y position
            Square currentSquare = this.map.getSquare(currX,currY);
            Bomb bombToDrop = null;
            switch (this.typeBomb) {
                case TRIGGER:
                    bombToDrop = new TriggerBomb(power, currX, currY);
                case TIME:
                    bombToDrop = new TimeBomb(power, currX, currY);
            };
            currentSquare.setMapElement(bombToDrop);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allow the Player to activate a bomb.
     * @param bombToActivate Permit to identify which bomb to activate
     */
    public void activateBomb(Bomb bombToActivate) {
        boolean isActivated = false;
        for (Bomb bomb : droppedBombs) {
            if (bomb.equals(bombToActivate)) {
                bombToActivate.activateBomb(this.map);
            }
        }
    }

    public int getPower() {
        return power;
    }
}
