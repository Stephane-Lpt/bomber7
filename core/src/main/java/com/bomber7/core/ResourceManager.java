package com.bomber7.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.bomber7.utils.Language;

/**
 * A singleton manager responsible for loading, saving, and managing the resources used in the game (textures, skin, bundles).
 */
public final class ResourceManager {
    /**
     * The singleton instance of the ResourceManager.
     */
    private static ResourceManager instance;
    /**
     * Skin object that contains UI textures and style definitions.
     */
    private Skin skin;
    /**
     * Skin object that contains map textures and style definitions.
     */
    private Skin mapSkin;
    /**
     * Skin object that contains character textures and style definitions.
     */
    private Skin characterSkin;
    /**
     * I18NBundle object that contains localized strings used in the game.
     */
    private I18NBundle bundle;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ResourceManager() {
        super();
    }

    /**
     * Retrieves the singleton instance of the {@code ConfigManager}.
     *
     * @return the single {@code ConfigManager} instance.
     */
    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    /**
     * Initializes the ResourceManager manager.
     * <p>
     * This method must be called before any config access occurs.
     * </p>
     *
     */
    public void initialize() {
        skin = new Skin(
            Gdx.files.internal("skin/ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("skin/ui/uiskin.atlas"))
        );
        mapSkin = new Skin(
            Gdx.files.internal("skin/map/mapskin.json"),
            new TextureAtlas(Gdx.files.internal("skin/map/mapskin.atlas"))
        );
        characterSkin = new Skin(
            Gdx.files.internal("skin/characters/characterskin.json"),
            new TextureAtlas(Gdx.files.internal("skin/characters/characterskin.atlas"))
        );
        Language language = ConfigManager.getInstance().getConfig().getLanguage();
        bundle = I18NBundle.createBundle(Gdx.files.internal("i18n/" + language.toString().toLowerCase()));
    }

    /**
     * Disposes of all loaded game resources to free up memory.
     * Should be called when the application or screen is closed.
     */
    public void dispose() {
        skin.dispose();
        mapSkin.dispose();
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
     * Returns the map skin used throughout the game.
     *
     * @return the loaded {@link Skin} object
     */
    public Skin getMapSkin() {
        return mapSkin;
    }

    /**
     * Returns the character skin used throughout the game.
     *
     * @return the loaded {@link Skin} object
     */
    public Skin getCharacterSkin() {
        return characterSkin;
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
     * Updates the I18N bundle object accordingly to Config Manager's language.
     */
    public void updateLanguage() {
        bundle = I18NBundle.createBundle(
            Gdx.files.internal("i18n/" + ConfigManager.getInstance().getConfig().getLanguage().toString().toLowerCase())
        );
    }
}
