package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.bomber7.utils.ScreenType;

/**
 * The main game screen where gameplay takes place.
 */
public class GameScreen extends BomberScreen {
    /**
     * Initializes the screen.
     *
     * @param game the Game instance this screen belongs to.
     */
    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() { }

    @Override
    public void initController() { }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GAME;
    }
}
