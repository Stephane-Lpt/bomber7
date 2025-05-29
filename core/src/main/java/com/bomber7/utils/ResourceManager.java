package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

// TODO : use AssetManager libgdx class instead
/**
 * ResourceManager contains all the resources used by the game.
 */
public final class ResourceManager {
    /**
     * Skin object that contains UI textures and style definitions.
     */
    private final Skin skin;

    /**
     * I18NBundle object that contains localized strings used in the game.
     */
    private final I18NBundle bundle;

    /**
     * GameSounds object that encapsulates game sound effects.
     */
    private final SoundManager sound;

    /**
     * Initializes all resources.
     */
    public ResourceManager() {
        skin = new Skin(
            Gdx.files.internal("ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        );
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/english"));
        sound = new SoundManager();
    }

    /**
     * Disposes of all loaded game resources to free up memory.
     * Should be called when the application or screen is closed.
     */
    public void dispose() {
        skin.dispose();
        sound.dispose();
    }

    /**
     * Returns the UI skin used throughout the game.
     *
     * @return the loaded {@link Skin} object
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Returns the localized string associated with the specified key
     * from the internationalized string bundle.
     *
     * @param key the key for the desired string
     * @return the localized string corresponding to the given key
     */
    public String getString(String key) {
        return bundle.get(key);
    }


    /**
     * Returns the sound manager.
     *
     * @return the {@link SoundManager} object containing sound effects
     */
    public SoundManager getSound() {
        return sound;
    }
}
