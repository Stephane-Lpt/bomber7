package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.bomber7.utils.ScreenType;

/**
 * Pause screen.
 */
public class PauseScreen extends BomberScreen {
    /**
     * Constructs a new PauseScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public PauseScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initController() {

    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.PAUSE;
    }
}

