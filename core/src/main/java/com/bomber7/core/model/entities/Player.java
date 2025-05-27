package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.*;

import java.util.List;

import com.bomber7.core.model.bombs.*;

/**
 * Classe Player
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Player extends Character {

    private List<Bomb> droppedBombs;
    private Bomb typeBomb;
    private int nbBomb;

    /**
     * Player Constructor
     */
    public Player(String name, int x, int y, int life, int speed, String spriteFP){
        super(name, x, y, life, speed, spriteFP);
        this.nbBomb = 1;
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Player number of bomb playable getter
     * @return nbBomb Current number of bomb playable
     */
    public Integer getNbBomb(){
        return this.nbBomb;
    }

    /**
     * Player number of bomb playable getter
     * @return typeBomb Current number of bomb playable
     */
    public Bomb getTypeBomb(){
        return this.typeBomb;
    }

    public Integer getNbDroppdeBomb(){
        return this.droppedBombs.size();
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Player number of bomb playable setter
     * @param newNbBomb The new nbBomb value
     * @throws IllegalBombSetException If new number of bomb is >= 0
     */
    public void setNbBomb(int newNbBomb){
        if (newNbBomb >= 0) {
            this.nbBomb = newNbBomb;
        } else {
            throw new IllegalBombOperationException("nb_bomb_to_set >= 0");
        }
    }

    /**
     * Player type of bomb playable setter
     * @param newTypeBomb The new type of bomb
     * @throws IllegalBombSetException If new type of bomb is not an instance of
     */
    public void setTypeBomb(Bomb newTypeBomb){
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
     * Allow the Player to drop a bomb
     * @param bombToDrop Permit to identify wich
     */
    public boolean dropBomb(Bomb bombToDrop){
        boolean isValidBomb = true;
        for (Bomb bomb : droppedBombs) {
            if (bomb.equals(bombToDrop)){
                isValidBomb = false;
            }
        }
        if(isValidBomb == true) {
            droppedBombs.add(bombToDrop);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allow the Player the timer of a bomb
     * @param bombToDrop Permit to identify wich
     */
    public boolean activateBomb(Bomb bombToActivate){
        boolean isActivated = false;
        for (Bomb bomb : droppedBombs) {
            if (bomb.equals(bombToActivate)){
                isActivated = true;
            }
        }
        if(isActivated == true) {
            bombToActivate.setStatusBomb(isActivated);
            return true;
        } else {
            return false;
        }
    }

}
