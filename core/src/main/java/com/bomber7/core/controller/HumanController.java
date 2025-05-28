package com.bomber7.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.core.model.entities.Player;

public class HumanController {
    private HumanPlayer player;
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
     * Processes input keys to control the player's movement.
     * This method checks if the player is pressing any movement keys and updates the player's position accordingly.
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
        }
    }

}
