package com.bomber7.utils;

public class PlayerBlueprint {
    private String name;
    private PlayerStrategy strategy;
    private GameCharacter character;

    public PlayerBlueprint(String name) {
        this.name = name;
        this.strategy = PlayerStrategy.HUMAN;
        this.character = GameCharacter.STUDENT;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public PlayerStrategy getStrategy() {
        return strategy;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    /**
     * Returns whether the configured player is valid or not.
     * A player is not valid if the selected skin is locked.
     * @return a boolean which indicates the validity of the configured player.
     */
    public boolean isValid() {
        // TODO
        return true;
    }
}
