package n7.bomber7.model.entities;

import n7.bomber7.model.exception.*;

/**
 * Classe Player
 * @author Pierre Chaveroux
 * @version Sprint n°1
 */
public abstract class Player extends Character {

    protected final Integer MINBOMB = 1;
    protected final Integer MAXBOMB = 3; 
    protected final Integer MINRADIUSBOMB = 1;
    protected final Integer MAXRADIUSBOMB = 10; 
    private Integer nbBomb;
    private Integer powerBomb;
    private String typeBomb;
    private Integer nbBombDropped = 0;

    /**
     * Player Constructor
     */
    public Player(String name, int x, int y){
        super(name, x, y);
        this.nbBomb = this.MINBOMB;
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
     * Player bomb power getter
     * @return powerBomb Current power of a bomb
     */
    public Integer getPowerBomb(){
        return this.powerBomb;
    }

    /**
     * Player number of bomb playable getter
     * @return typeBomb Current number of bomb playable
     */
    public String getTypeBomb(){
        return this.typeBomb;
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
     * Bomb Power Hit Range setter
     * @param newPowerBomb The new nbBomb range of power
     * @throws IllegalBombSetException If new power of bomb is >= 1
     */
    public void setPowerBomb(int newPowerBomb){
        if (newPowerBomb >= 1) {
            this.powerBomb = newPowerBomb;
        } else {
            throw new IllegalBombOperationException("bomb_power_to_set >= 1");
        }
    }

    /**
     * Player type of bomb playable setter
     * @param newTypeBomb The new type of bomb 
     * @throws IllegalBombSetException If new type of bomb is not an instance of
     */
    public void setTypeBomb(String newTypeBomb){
        if (/**(newTypeBomb instanceof Bomb)*/ newTypeBomb.equals(newTypeBomb)){
            this.typeBomb = newTypeBomb;
        } else {
            throw new IllegalBombOperationException(this.MINRADIUSBOMB + "<= bomb_power_to_set <=" + this.MAXRADIUSBOMB);
        }
    }

}
