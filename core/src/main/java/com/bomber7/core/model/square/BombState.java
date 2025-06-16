package com.bomber7.core.model.square;

/**
 * Represents the state of a bomb in the game.
 * This enum can be extended in the future to include
 * properties and methods related to the bomb's state.
 */
public enum BombState {
    /**
     * The bomb is currently active and ticking down.
     */
    PLACED,
    /**
     * The bomb has exploded.
     */
    EXPLODED
}
