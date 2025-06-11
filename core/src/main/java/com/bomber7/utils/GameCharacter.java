package com.bomber7.utils;

/**
 * Represents the different playable characters in the game, each associated with a specific sprite
 * and a number of points required to unlock them.
 */
public enum GameCharacter {
    /**
     * Default character.
     */
    STUDENT("sprite-student", 0),

    /**
     * Character represeting a member of the school's BDE.
     */
    BDE("sprite-bde", 100),

    /**
     * Graduate character.
     */
    GRADUATE("sprite-graduate", 200);

    /**
     * The name of the drawable (sprite asset) associated with the character.
     */
    private final String drawableName;

    /**
     * The number of points required to unlock this character.
     */
    private final int pointsToUnlock;

    /**
     * Array of all {@code GameCharacter} values for navigation methods
     * {@link #next()} and {@link #previous()}.
     */
    private static final GameCharacter[] CHARACTERS = values();

    /**
     * Constructs a {@code GameCharacter} with a drawable name and a point threshold required to unlock it.
     *
     * @param drawableName the name of the sprite asset representing the character.
     * @param pointsToUnlock the number of points needed to unlock this character.
     */
    GameCharacter(String drawableName, int pointsToUnlock) {
        this.drawableName = drawableName;
        this.pointsToUnlock = pointsToUnlock;
    }

    /**
     * Getter of drawableName.
     *
     * @return the libGDX drawable name.
     */
    public String getDrawableName() {
        return drawableName;
    }

    /**
     * Getter of pointsToUnlock.
     *
     * @return the unlock threshold in points.
     */
    public int getPointsToUnlock() {
        return pointsToUnlock;
    }

    /**
     * Returns the next character in the enum order, looping back to the first if necessary.
     *
     * @return the next {@code GameCharacter}.
     */
    public GameCharacter next() {
        return CHARACTERS[(this.ordinal() + 1) % CHARACTERS.length];
    }

    /**
     * Returns the previous character in the enum order, looping to the last if necessary.
     *
     * @return the previous {@code GameCharacter}.
     */
    public GameCharacter previous() {
        return CHARACTERS[(this.ordinal() - 1 + CHARACTERS.length) % CHARACTERS.length];
    }
}
