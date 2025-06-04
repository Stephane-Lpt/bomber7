package com.bomber7.utils;

/**
* An enum that contains all the possible controls with their default values.
*/
public enum Controls {

    /**
     * Move the character up.
     */
    MOVE_UP("Z", "I", "UP", "NUMPAD8"),
    /**
     * Move the character down.
     */
    MOVE_DOWN("S", "K", "DOWN", "NUMPAD5"),
    /**
     * Move the character left.
     */
    MOVE_LEFT("Q", "J", "LEFT", "NUMPAD4"),
    /**
     * Move the character right.
     */
    MOVE_RIGHT("D", "L", "RIGHT", "NUMPAD6"),
    /**
     * Plant a bomb.
     */
    PLANT_BOMB("E", "O", "RIGHT_CTRL", "NUMPAD0"),
    /**
     * Activate a trigger bomb.
     */
    ACTIVATE_BOMB("A", "U", "RIGHT_SHIFT", "NUMPAD_DECIMAL");

    /**
     * Key for player 1.
     */
    private final String player1Key;
    /**
     * Key for player 2.
     */
    private final String player2Key;
    /**
     * Key for player 3.
     */
    private final String player3Key;
    /**
     * Key for player 4.
     */
    private final String player4Key;

    /**
     * Initializes the control with a default key for each player.
     * @param player1 key for player 1
     * @param player2 key for player 2
     * @param player3 key for player 3
     * @param player4 key for player 4
     */
    Controls(String player1, String player2, String player3, String player4) {
        this.player1Key = player1;
        this.player2Key = player2;
        this.player3Key = player3;
        this.player4Key = player4;
    }

    /**
     * Returns the default input key for a given player
     * @param playerIndex the inder of the player
     * @return the key
     */
    public String getKeyForPlayer(int playerIndex) {
        switch (playerIndex) {
            case 0:
                return player1Key;
            case 1:
                return player2Key;
            case 2:
                return player3Key;
            case 3:
                return player4Key;
            default:
                throw new IllegalArgumentException("Invalid player index: " + playerIndex);
        }
    }
}
