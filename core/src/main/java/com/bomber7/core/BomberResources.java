package com.bomber7.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

public final class BomberResources {
    public Skin skin;
    public I18NBundle bundle;

    public Sound hoverSound;
    public Sound clickSound;

    public BomberResources() {
        skin = new Skin(
            Gdx.files.internal("ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        );
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/english"));

        initSounds();
    }

    private void initSounds() {
        hoverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hover.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
    }

    private void disposeSounds() {
        hoverSound.dispose();
        clickSound.dispose();
    }

    public void dispose() {
        skin.dispose();
        disposeSounds();
    }
}
