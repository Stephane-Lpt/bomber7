package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.core.screens.MainMenuScreen;


public class Bomber7 extends Game {
    @Override
    public void create () {
        setScreen(new MainMenuScreen(this));
    }
}
