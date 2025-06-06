package com.bomber7.utils;

/**
 * An enum that lists all the languages supported by the game.
 */
public enum Language {
    /**
     * French language.
     */
    FRENCH,
    /**
     * English language.
     */
    ENGLISH;

    /**
     * Array of all {@code Language} values for navigation methods
     * {@link #next()} and {@link #previous()}.
     */
    private static final Language[] VALUES = values();

    /**
     * Returns the next language in the enum order, looping back to the first if necessary.
     *
     * @return the next {@code Language}.
     */
    public Language next() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    /**
     * Returns the previous language in the enum order, looping to the last if necessary.
     *
     * @return the previous {@code Language}.
     */
    public Language previous() {
        return VALUES[(this.ordinal() - 1 + VALUES.length) % VALUES.length];
    }
}
