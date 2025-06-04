package com.bomber7.core.controller;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.model.entities.HumanPlayer;

/**
 * The {@code HumanController} class is responsible for handling user input and
 * controlling the movement of a {@link HumanPlayer} in the game. It processes
 * keyboard input according to the player's configuration and updates the
 * player's position accordingly.
 *
 * <p>
 * This controller is typically used to allow a human player to interact with
 * the game using customizable key bindings defined in a {@link Config} object.
 * </p>
 */
public class HumanController {

    /** The HumanPlayer instance that this controller manages. */
    private HumanPlayer player;

    /** The configuration object containing key bindings for the player. */
    private Config playerConfig;

    /**
     * Constructs a HumanController for the specified player.
     *
     * @param player the player this controller will manage
     */
    public HumanController(HumanPlayer player) {
        this.player = player;
        this.playerConfig = player.getConfig();
    }

    /**
     * Processes input keys to control the player's movement. This method checks if
     * the player is pressing any movement keys and updates the player's position
     * accordingly.
     */
    public void processKeys() {
        if (Gdx.input.isKeyPressed(this.playerConfig.getUp())) {
            this.player.moveUp();
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getDown())) {
            this.player.moveDown();
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getLeft())) {
            this.player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getRight())) {
            this.player.moveRight();
<<<<<<< HEAD
        } else {
=======
        }
        else {
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
            this.player.setStandingStill();
        }
    }

}
