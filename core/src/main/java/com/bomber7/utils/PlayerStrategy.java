package com.bomber7.utils;

/**
 * Represents the different strategies that a BomberGame player's can use.
 * Each strategy defines a different behaviour.
 */
public enum PlayerStrategy {

    /**
     * Human player.
     */
    HUMAN,

    /**
     * Passive AI that does not attack others.
     */
    PEACEFUL,

    /**
     * Basic AI with simple behavior (random moves, random bomb placement).
     */
    BEGINNER,

    /**
     * Advanced AI (TBD).
     */
    PRO;

    /**
     * Aray of all {@code PlayerStrategy} values for next() and previous() methods.
     */
    private static final PlayerStrategy[] VALUES = values();

    /**
     * Returns the next strategy in the list, looping to the first if necessary.
     *
     * @return the next {@code PlayerStrategy}.
     */
    public PlayerStrategy next() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    /**
     * Returns the previous strategy in the list, looping to the last if necessary.
     *
     * @return the previous {@code PlayerStrategy}.
     */
    public PlayerStrategy previous() {
        return VALUES[(this.ordinal() - 1 + VALUES.length) % VALUES.length];
    }
}

