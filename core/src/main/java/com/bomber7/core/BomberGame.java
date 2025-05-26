package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.bomber7.core.screens.MainMenuScreen;

/**
 * Main class of the Bomber7 game, extending LibGDX's Game class.
 */
public class BomberGame extends Game {
    public Skin sharedSkin;
    public I18NBundle bundle;

    /**
     * Called once when the application is created.
     * Initializes the sprite batch and loads the initial texture.
     */
    @Override
    public void create () {
        sharedSkin = new Skin(
            Gdx.files.internal("ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        );
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/french"));
        setScreen(new MainMenuScreen(this));
    }

    /**
     * Called when the application is closed.
     * Disposes of game resources to free up memory.
     */
    @Override
    public void dispose() {
        sharedSkin.dispose();
        super.dispose();
    }
}
