package com.bomber7.utils;

/**
 * Represents the player a user can configure in the
 * {@link com.bomber7.core.components.PlayerSelector} widget.
 * <p>
 * The instance of a PlayerBlueprint is hold inside a {@link PlayerBlueprintObservable} to allow
 * null values (= player not configured). Is it then used when starting a game,
 * to create a character.
 * </p>
 *
 */
public class PlayerBlueprint {
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The strategy of the player.
     */
    private PlayerStrategy strategy;
    /**
     * The skin of the player.
     */
    private GameCharacter character;

    /**
     * Instantiates the PlayerBlueprint with a name and default values for strategy (Human) and skin (Student).
     * @param name the name of the player.
     */
    public PlayerBlueprint(String name) {
        this.name = name;
        this.strategy = PlayerStrategy.HUMAN;
        this.character = GameCharacter.STUDENT;
    }

    /**
     * Name setter.
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Strategy setter.
     * @param strategy the strategy to set.
     */
    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Skin (character) setter.
     * @param character the name to set.
     */
    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    /**
     * Name getter.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Strategy getter.
     * @return the strategy of the player.
     */
    public PlayerStrategy getStrategy() {
        return strategy;
    }

    /**
     * Skin (character) getter.
     * @return the skin (character) of the player.
     */
    public GameCharacter getCharacter() {
        return character;
    }

    /**
     * Returns whether the configured player is valid or not.
     * A player is not valid if the selected skin is locked (= not enough point earned).
     * @return a boolean which indicates the validity of the configured player.
     */
    public boolean isValid() {
        // TODO
        return true;
    }
}
