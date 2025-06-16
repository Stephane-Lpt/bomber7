package com.bomber7.core.model.entities;

/**
 * Enum representing different animation states of a character.
 */
public enum CharacterState {
        /** The character is not moving. */
        STANDING_STILL,
        /** The character is moving upwards. */
        MOVING_UP,
        /** The character is moving downwards. */
        MOVING_DOWN,
        /** The character is moving to the left. */
        MOVING_LEFT,
        /** The character is moving to the right. */
        MOVING_RIGHT,
        /** The character has died. */
        DIE
}
