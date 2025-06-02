/**
 * Classe Player.
 */

package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalBombOperationException;

import java.util.List;

import com.bomber7.core.model.bombs.Bomb;

public abstract class Player extends Character {

    /** List of dropped bombs by the player. */
    private List<Bomb> droppedBombs;
    /** Type of bomb used by the player. */
    private int typeBomb;
    /** Number of bombs the player can drop. */
    private int nbBomb;

    /**
     * Player Constructor.
     * @param name     Name of the player
     * @param x        X coordinate of the player
     * @param y        Y coordinate of the player
     * @param life     Life points of the player
     * @param speed    Speed of the player
     * @param spriteFP File path to the player's sprite
     */
    public Player(String name, LevelMap map, int x, int y, int life, int speed, String spriteFP) {
        super(name, map, x, y, life, speed, spriteFP);
        this.nbBomb = 1;
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
    public int getTypeBomb() {
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
     * @throws IllegalBombSetException If new type of bomb is not an instance of
     */
    public void setTypeBomb(Bomb newTypeBomb) {
        boolean isValidBomb = false;
        for (Bomb.BombType type : Bomb.BombType.values()) {
            if (type.name().equals(newTypeBomb.getClass().getSimpleName())) {
                isValidBomb = true;
                break;
            }
        }
        if (isValidBomb) {
            this.typeBomb = newTypeBomb;
        } else {
            throw new IllegalBombOperationException("Invalid bomb type: " + newTypeBomb.getClass().getSimpleName());
        }
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Allow the Player to drop a bomb.
     * @param bombToDrop Permit to identify which bomb to drop
     * @return true if the bomb was successfully dropped, false otherwise
     */
    public boolean dropBomb(Bomb bombToDrop) {
        boolean isValidBomb = true;
        for (Bomb bomb : droppedBombs) {
            if (bomb.equals(bombToDrop)) {
                isValidBomb = false;
            }
        }
        if (isValidBomb) {
            droppedBombs.add(bombToDrop);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allow the Player to activate a bomb.
     * @param bombToActivate Permit to identify which bomb to activate
     * @return true if the bomb was successfully activated, false otherwise
     */
    public boolean activateBomb(Bomb bombToActivate) {
        boolean isActivated = false;
        for (Bomb bomb : droppedBombs) {
            if (bomb.equals(bombToActivate)) {
                isActivated = true;
            }
        }
        if (isActivated) {
            bombToActivate.setStatusBomb(isActivated);
            return true;
        } else {
            return false;
        }
    }

}
