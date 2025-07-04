package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalBombOperationException;
import com.bomber7.core.model.exceptions.IllegalPowerOperationException;

import java.util.ArrayList;
import java.util.List;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.BombType;
import com.bomber7.core.model.square.Bonus;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TriggerBomb;
import com.bomber7.utils.GameCharacter;
import com.bomber7.utils.Constants;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.utils.Score;
import com.bomber7.utils.SoundManager;
import com.bomber7.utils.SoundType;

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
     * @param mapX     X-coordinate map of the player on the map
     * @param mapY     Y-coordinate map of the player on the map
     * @param life     Life points of the player
     * @param speed    Speed of the player
     * @param gameCharacter The game character type
     */
    public Player(String name, LevelMap map, int mapX, int mapY, int life, int speed, GameCharacter gameCharacter) {
        super(name, map, mapX, mapY, life, speed, gameCharacter);
        this.nbBomb = 1;
        this.power = Constants.DEFAULT_BOMB_POWER;
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

    /**
     * Get the List of current TriggerBomb dropped.
     * @return the list of the dropped trigger bombs
     */
    public List<TriggerBomb> getTriggerBombsDropped() {
        return this.triggerBombsDropped;
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

    /**
     *  Sets the List of TriggerBombs dropped by the player.
     *  @param triggerBombsDropped The new list of TriggerBombs
     */
    public void setTriggerBombsDropped(List<TriggerBomb> triggerBombsDropped) {
        this.triggerBombsDropped.clear();
        this.triggerBombsDropped.addAll(triggerBombsDropped);
    }

    /* ------[OTHER]------------------------------------ */

    /**
     * Play song.
     */
    public void playSong() {
        SoundManager.getInstance().play(SoundType.BOMB_CHARGE);
    }
    /**
     * Allow the Player to drop a bomb.
     * @return true if the bomb was successfully dropped, false otherwise
     */
    public boolean dropBomb() {
        if (!this.isAlive()) {
            return false;
        }

        if (this.map.getSquare(this.getMapX(), this.getMapY()).hasMapElement()) {
            return false;
        }

        if (nbBomb >= 1) {
            addScore(Score.DROP_BOMB);

            Bomb bombToDrop;
            switch (this.typeBomb) {
                case TRIGGER:
                    playSong();
                    bombToDrop = new TriggerBomb(power, this.getMapX(), this.getMapY(), this);
                    this.triggerBombsDropped.add((TriggerBomb) bombToDrop); // Add it to the trigger bombs dropped list
                    break;
                case TIME:
                    playSong();
                    bombToDrop = new TimeBomb(power, this.getMapX(), this.getMapY(), this);
                    break;
                default:
                    bombToDrop = null;
            }
            Square currentSquare = this.map.getSquare(this.getMapX(), this.getMapY());
            currentSquare.setMapElement(bombToDrop);
            this.nbBomb--;  // Decrease the number of bombs available
            this.setStandingStill();
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

    /**
     * Moves the character to the right with the super method
     * and then collects a bonus if there is any.
     */
    @Override
    public void moveRight() {
        super.moveRight();
        collectBonus();
    }

    /**
     * Moves the character to the left with the super method
     * and then collects a bonus if there is any.
     */
    @Override
    public void moveLeft() {
        super.moveLeft();
        collectBonus();
    }

    /**
     * Moves the character down with the super method
     * and then collects a bonus if there is any.
     */
    @Override
    public void moveDown() {
        super.moveDown();
        collectBonus();

    }

    /**
     * Moves the character up with the super method
     * and then collects a bonus if there is any.
     */
    @Override
    public void moveUp() {
        super.moveUp();
        collectBonus();
    }

    /**
     * Checks whether the square where the player is situated has a bonus or not,
     * and if it does, collects it and applies it to the player.
     */
    public void collectBonus() {
        Square currentSquare = map.getSquare(this.getMapX(), this.getMapY());
        if (currentSquare.hasMapElement() && currentSquare.getMapElement() instanceof Bonus) {
            ((Bonus) currentSquare.getMapElement()).applyBonusEffect(this);
            currentSquare.clearMapElement();
        }
    }

    /**
     * Reset method to add bomb type and bomb number resetting to the super method reset.
     */
    @Override
    public void reset() {
        super.reset();
        this.nbBomb = 1;
        this.typeBomb = BombType.TIME;
    }

}
