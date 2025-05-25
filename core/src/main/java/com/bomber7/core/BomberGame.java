package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.bomber7.core.screens.MainMenuScreen;


public class BomberGame extends Game {
    public Skin sharedSkin;
    public I18NBundle bundle;

    @Override
    public void create () {
        sharedSkin = new Skin(
            Gdx.files.internal("ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        );
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/french"));
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        sharedSkin.dispose();
        super.dispose();
    }
}
