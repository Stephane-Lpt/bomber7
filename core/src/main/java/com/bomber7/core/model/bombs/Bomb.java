package com.bomber7.core.model.bombs;

import com.bomber7.core.model.exceptions.IllegalBombOperationException;

/**
 * Classe Bomb
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Bomb {

    protected final int MINRADIUSBOMB = 1;
    protected final int MAXRADIUSBOMB = 10;
    private boolean activationStatus;
    private int power;

    /* Acceptable types of bomb */
    public enum BombType {
        TRIGGER_BOMB,
        TIME_BOMB
    }

    /**
     * Bomb constructor
     */
    public Bomb(Integer power) {
        this.power = power;
    }

    /* ------[GETTERS]------------------------------------ */

    /**
     * Bomb power getter
     * @return power Current power of a bomb
     */
    public int getPowerBomb(){
        return this.power;
    }

    /**
     * Activation status of bomb getter
     * @return activated Current status of a bomb
     */
    public boolean getStatusBomb(){
        return this.activationStatus;
    }

    /* ------[SETTERS]------------------------------------ */

    /**
     * Bomb Power Hit Range setter
     * @param newPowerBomb The new nbBomb range of power
     * @throws IllegalBombSetException If new power of bomb is >= 1
     */
    public void setPowerBomb(int newPowerBomb){
        if (newPowerBomb >= 1) {
            this.power = newPowerBomb;
        } else {
            throw new IllegalBombOperationException("bomb_power_to_set >= 1");
        }
    }

    /**
     * Bomb activation status setter
     * @param newPowerBomb The new nbBomb range of power
     */
    public void setStatusBomb(boolean statusBomb){
        this.activationStatus = statusBomb;
    }

    /* ------[OTHER]------------------------------------ */


}
