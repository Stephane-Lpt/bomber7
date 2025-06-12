package com.bomber7.utils;

import com.bomber7.core.model.Observer;
import com.bomber7.core.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable wrapper of {@link PlayerBlueprint}.
 * It was created to allow a null value for a playerBlueprint (= not added), which was
 * needed to implement UI logic in {@link com.bomber7.core.components.PlayerSelector}
 */
public class PlayerBlueprintObservable implements Subject {
    /**
     * The list of this subject's observers.
     */
    private final List<Observer> observers = new ArrayList<>();
    /**
     * The PlayerBlueprint instance this observable is linked to.
     */
    private PlayerBlueprint value;

    /**
     * Constructor that initializes a null playerBlueprint instance.
     */
    public PlayerBlueprintObservable() {
        value = null;
    }

    /**
     * Used to create a valid instance of PlayerBlueprint with a given name.
     * @param name the name of the player.
     */
    public void initialize(String name) {
        value = new PlayerBlueprint(name);
        this.notifyChanged();
    }

    /**
     * Returns the playerBlueprint held by this observable.
     * @return playerBlueprint of this observable
     */
    public PlayerBlueprint getPlayerBlueprint() {
        return value;
    }

    /**
     * Wrapper of {@link PlayerBlueprint#setName(String)}.
     * It does not notify its observers when changed because no UI objects are
     * linked to the player's name.
     * @param name the name to set.
     */
    public void setName(String name) {
        value.setName(name);
    }

    /**
     * Wrapper of {@link PlayerBlueprint#setStrategy(PlayerStrategy)}.
     * Notifies its observers when called.
     * @param strategy the strategy to set.
     */
    public void setStrategy(PlayerStrategy strategy) {
        value.setStrategy(strategy);
        this.notifyChanged();
    }

    /**
     * Wrapper of {@link PlayerBlueprint#setCharacter(GameCharacter)}.
     * Notifies its observers when called.
     * @param character the skin (character) to set.
     */
    public void setCharacter(GameCharacter character) {
        value.setCharacter(character);
        this.notifyChanged();
    }

    /**
     * Wrapper of {@link PlayerBlueprint#getName()}.
     * @return the name of the player.
     */
    public String getName() {
        return value.getName();
    }

    /**
     * Wrapper of {@link PlayerBlueprint#getStrategy()}.
     * @return the strategy of the player.
     */
    public PlayerStrategy getStrategy() {
        return value.getStrategy();
    }

    /**
     * Wrapper of {@link PlayerBlueprint#getCharacter()}.
     * @return the skin (character) of the player.
     */
    public GameCharacter getCharacter() {
        return value.getCharacter();
    }

    /**
     * Disposed the linked PlayerBlueprint instance (sets it to null).
     */
    public void dispose() {
        value = null;
        this.notifyChanged();
    }

    /**
     * Checks whether the player blueprint has been disposed.
     *
     * @return {@code true} if the player blueprint is disposed (= null), {@code false} otherwise.
     */
    public boolean isDisposed() {
        return value == null;
    }

    /**
     * Checks whether the player blueprint is valid.
     *
     * @return {@code true} if the player blueprint is valid (not disposed and has an unlocked character),
     * {@code false} otherwise.
     */
    public boolean isValid() {
        return !isDisposed() && value.isValid();
    }

    /**
     * Marks this observable as changed and notifies all of its observers.
     */
    public void notifyChanged() {
        this.notifyObservers();
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }
}
