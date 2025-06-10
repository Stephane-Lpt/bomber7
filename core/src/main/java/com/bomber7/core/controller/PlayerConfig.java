package com.bomber7.core.controller;

/**
 * Represents the key bindings configuration for a player.
 * <p>
 * Holds key codes for movement directions and bomb actions.
 * </p>
 */

import com.badlogic.gdx.Gdx;
import com.bomber7.utils.Controls;
import com.bomber7.utils.DefaultConfig;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the key bindings configuration for a player.
 * Maps control actions to their corresponding key codes.
 */
public class PlayerConfig implements Serializable {
    private final Map<Controls, Integer> keyBindings;

    /**
     * Constructs a new PlayerConfig with default key bindings.
     * @param up            key code for moving up
     * @param down          key code for moving down
     * @param left          key code for moving left
     * @param right         key code for moving right
     * @param dropBomb      key code for dropping a bomb
     * @param activateBomb  key code for activating a bomb
     */
    public PlayerConfig(int up, int down, int left, int right, int dropBomb, int activateBomb) {
        this.keyBindings = new EnumMap<>(Controls.class);

        keyBindings.put(Controls.UP, up);
        keyBindings.put(Controls.DOWN, down);
        keyBindings.put(Controls.LEFT, left);
        keyBindings.put(Controls.RIGHT, right);
        keyBindings.put(Controls.DROP_BOMB, dropBomb);
        keyBindings.put(Controls.ACTIVATE_BOMB, activateBomb);
    }

    public PlayerConfig(Map<Controls, Integer> controlsBinding) {
        this(
            controlsBinding.get(Controls.UP),
            controlsBinding.get(Controls.DOWN),
            controlsBinding.get(Controls.LEFT),
            controlsBinding.get(Controls.RIGHT),
            controlsBinding.get(Controls.DROP_BOMB),
            controlsBinding.get(Controls.ACTIVATE_BOMB)
        );
    }

    public PlayerConfig(PlayerConfig other) {
        this(
            other.getKeyBinding(Controls.UP),
            other.getKeyBinding(Controls.DOWN),
            other.getKeyBinding(Controls.LEFT),
            other.getKeyBinding(Controls.RIGHT),
            other.getKeyBinding(Controls.DROP_BOMB),
            other.getKeyBinding(Controls.ACTIVATE_BOMB)
        );
    }

    /**
     * Sets the key code for the specified control action.
     * @param control the control action to configure
     * @param keyCode the key code to assign
     */
    public void setKeyBinding(Controls control, int keyCode) {
        keyBindings.put(control, keyCode);
    }

    /**
     * Gets the key code for the specified control action.
     * @param control the control action to query
     * @return the associated key code
     */
    public int getKeyBinding(Controls control) {
        return keyBindings.get(control);
    }

    /**
     * Compares this {@code PlayerConfig} to another object for equality.
     * Two {@code PlayerConfig} instances are equal all their key bindings are equal.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerConfig)) {
            return false;
        }

        PlayerConfig other = (PlayerConfig) obj;
//
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.UP) == other.keyBindings.get(Controls.UP)));
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.DOWN) == other.keyBindings.get(Controls.DOWN)));
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.LEFT) == other.keyBindings.get(Controls.LEFT)));
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.RIGHT) == other.keyBindings.get(Controls.RIGHT)));
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.DROP_BOMB) == other.keyBindings.get(Controls.DROP_BOMB)));
//        Gdx.app.debug("PlayerConfig", "" + (this.keyBindings.get(Controls.ACTIVATE_BOMB) == other.keyBindings.get(Controls.ACTIVATE_BOMB)));

        return
            this.keyBindings.get(Controls.UP).intValue() == other.keyBindings.get(Controls.UP).intValue() &&
            this.keyBindings.get(Controls.DOWN).intValue() == other.keyBindings.get(Controls.DOWN).intValue() &&
            this.keyBindings.get(Controls.LEFT).intValue() == other.keyBindings.get(Controls.LEFT).intValue() &&
            this.keyBindings.get(Controls.RIGHT).intValue() == other.keyBindings.get(Controls.RIGHT).intValue() &&
            this.keyBindings.get(Controls.DROP_BOMB).intValue() == other.keyBindings.get(Controls.DROP_BOMB).intValue() &&
            this.keyBindings.get(Controls.ACTIVATE_BOMB).intValue() == other.keyBindings.get(Controls.ACTIVATE_BOMB).intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        keyBindings.get(Controls.UP),
            keyBindings.get(Controls.DOWN),
            keyBindings.get(Controls.LEFT),
            keyBindings.get(Controls.RIGHT),
            keyBindings.get(Controls.DROP_BOMB),
            keyBindings.get(Controls.ACTIVATE_BOMB)
        );
    }
}
