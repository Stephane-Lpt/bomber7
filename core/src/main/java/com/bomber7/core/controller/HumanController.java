package com.bomber7.core.controller;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.utils.Controls;

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
    private final HumanPlayer player;

    /** The configuration object containing key bindings for the player. */
    private final PlayerConfig playerConfig;

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
        boolean anyKeyPressed = false;

        if (Gdx.input.isKeyPressed(this.playerConfig.getKeyBinding(Controls.UP))) {
            this.player.moveUp();
            anyKeyPressed = true;
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getKeyBinding(Controls.DOWN))) {
            this.player.moveDown();
            anyKeyPressed = true;
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getKeyBinding(Controls.LEFT))) {
            this.player.moveLeft();
            anyKeyPressed = true;
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getKeyBinding(Controls.RIGHT))) {
            this.player.moveRight();
            anyKeyPressed = true;
        }
        if (Gdx.input.isKeyPressed(this.playerConfig.getKeyBinding(Controls.DROP_BOMB))) {
            this.player.dropBomb();
            anyKeyPressed = true;
        }
        if (!anyKeyPressed) {
            this.player.setStandingStill();
        }
    }

}
