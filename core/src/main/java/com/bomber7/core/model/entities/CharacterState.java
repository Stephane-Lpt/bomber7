package com.bomber7.core.model.entities;

/** This class is used to define and manage the different states
 * a character can have in terms of movement and actions.
 */
public class CharacterState {

    /** Enumeration representing the possible movement statuses of characters */
    public enum State {
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
}
