package com.bomber7.utils;

import java.util.Observable;

public class PlayerBlueprintObservable extends Observable {
    private PlayerBlueprint value;

    public PlayerBlueprintObservable() {
        value = null;
    }

    public void initialize(String name) {
        value = new PlayerBlueprint(name);
        this.notifyChanged();
    }

    public void setName(String name) {
        value.setName(name);
    }

    public void setStrategy(PlayerStrategy strategy) {
        value.setStrategy(strategy);
        this.notifyChanged();
    }

    public void setCharacter(GameCharacter character) {
        value.setCharacter(character);
        this.notifyChanged();
    }

    public String getName() {
        return value.getName();
    }

    public PlayerStrategy getStrategy() {
        return value.getStrategy();
    }

    public GameCharacter getCharacter() {
        return value.getCharacter();
    }

    public void dispose() {
        value = null;
        this.notifyChanged();
    }

    public boolean isDisposed() {
        return value == null;
    }

    public boolean isValid() {
        return !isDisposed() && value.isValid();
    }

    public void notifyChanged() {
        this.setChanged();
        this.notifyObservers();
    }
}
