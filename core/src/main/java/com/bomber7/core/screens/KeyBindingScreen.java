package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.utils.Controls;

/**
 * A screen where a player can bind its input controls.
 */
public class KeyBindingScreen extends BomberScreen {
    private TextButton[] inputButton

    /**
     * Constructs a new KeyBindingScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public KeyBindingScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() {
        Table table = new Table();

        Label[] inputLabels = new Label[Controls.values().length];
        inputButtons = new TextButton[Controls.values().length];
    }

    @Override
    public void initController() {

    }
}
