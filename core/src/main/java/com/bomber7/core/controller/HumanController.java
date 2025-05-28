package com.bomber7.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class HumanController {
    private Player player;

    /**
     * Constructs a HumanController for the specified player.
     *
     * @param player the player this controller will manage
     */
    public HumanController(Player player) {
        this.player = player;
    }

    /**
     * Processes input keys to control the player's movement.
     * This method checks if the player is pressing any movement keys and updates the player's position accordingly.
     */
    public void processKeys() {
        if (Gdx.input.isKeyPressed(this.player.getUpKey())) {
            this.player.moveUp();
        }
        if (Gdx.input.isKeyPressed(this.player.getDownKey())) {
            this.player.moveDown();
        }
        if (Gdx.input.isKeyPressed(this.player.getLeftKey())) {
            this.player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(this.player.getRightKey())) {
            this.player.moveRight();    
        }
    }

}
