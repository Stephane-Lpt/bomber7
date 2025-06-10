package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.controller.PlayerConfig;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the user configuration settings such as global volume,
 * music volume, and selected language.
 * <p>
 * This class is serializable and can be saved or loaded using
 * standard Java serialization.
 * </p>
 */
public class Config implements Serializable {

    /**
     * The global volume level.
     */
    private int globalVolume;

    /**
     * The music volume level.
     */
    private int musicVolume;

    /**
     * The selected language.
     */
    private Language language;

    /**
     * A list of PlayerConfig objects for player input controls.
     */
    private PlayerConfig[] playerConfigs;

    /**
     * Constructs a new {@code Config} instance with default values.
     */
    public Config() {
        this.globalVolume = DefaultConfig.GLOBAL_VOLUME;
        this.musicVolume = DefaultConfig.MUSIC_VOLUME;
        this.language = DefaultConfig.LANGUAGE;

        this.playerConfigs = new PlayerConfig[Constants.MAX_PLAYERS];

        playerConfigs[0] = new PlayerConfig(DefaultConfig.getControlsForPlayer(0));
        playerConfigs[1] = new PlayerConfig(DefaultConfig.getControlsForPlayer(1));
        playerConfigs[2] = new PlayerConfig(DefaultConfig.getControlsForPlayer(2));
        playerConfigs[3] = new PlayerConfig(DefaultConfig.getControlsForPlayer(3));
    }

    /**
     * Constructs a new {@code Config} by copying another instance.
     *
     * @param other the {@code Config} instance to copy from
     */
    public Config(Config other) {
        this.globalVolume = other.globalVolume;
        this.musicVolume = other.musicVolume;
        this.language = other.language;

        this.playerConfigs = new PlayerConfig[other.playerConfigs.length];
        for (int i = 0; i < other.playerConfigs.length; i++) {
            this.playerConfigs[i] = new PlayerConfig(other.playerConfigs[i]);
        }
    }

    /**
     * Returns the global volume level.
     *
     * @return the global volume
     */
    public int getGlobalVolume() {
        return globalVolume;
    }

    /**
     * Sets the global volume level.
     *
     * @param volume the new global volume
     */
    public void setGlobalVolume(int volume) {
        this.globalVolume = volume;
    }

    /**
     * Returns the music volume level.
     *
     * @return the music volume
     */
    public int getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the music volume level.
     *
     * @param volume the new music volume
     */
    public void setMusicVolume(int volume) {
        this.musicVolume = volume;
    }

    /**
     * Returns the current language setting.
     *
     * @return the selected {@code Language}
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the language preference.
     *
     * @param language the new {@code Language} to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Sets the player config for player {@code player}.
     *
     * @param playerConfig the new {@code PlayerConfigf} to set
     * @param player the id of the player this config should be set to
     */
    public void setPlayerConfig(PlayerConfig playerConfig, int player) {
        this.playerConfigs[player] = playerConfig;
    }

    public void setPlayerControl(int playerIndex, Controls control, int keycode) {
        playerConfigs[playerIndex].setKeyBinding(control, keycode);
    }

    /**
     * Returns the config of the specified player.
     *
     * @return the player to get the config from
     */
    public PlayerConfig getPlayerConfig(int player) {
        return this.playerConfigs[player];
    }

    /**
     * Compares this {@code Config} to another object for equality.
     * Two {@code Config} instances are equal all their attributes are equal.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Config)) {
            return false;
        }
        Config other = (Config) obj;

        Gdx.app.debug("Config", "checking for equality");
        Gdx.app.debug("Config", "" + this.playerConfigs[0].equals(other.playerConfigs[0]));
        Gdx.app.debug("Config", "" + this.playerConfigs[1].equals(other.playerConfigs[1]));
        Gdx.app.debug("Config", "" + this.playerConfigs[2].equals(other.playerConfigs[2]));
        Gdx.app.debug("Config", "" + this.playerConfigs[3].equals(other.playerConfigs[3]));

        return
            this.globalVolume == other.globalVolume &&
            this.musicVolume == other.musicVolume &&
            this.language == other.language &&
            this.playerConfigs[0].equals(other.playerConfigs[0]) &&
            this.playerConfigs[1].equals(other.playerConfigs[1]) &&
            this.playerConfigs[2].equals(other.playerConfigs[2]) &&
            this.playerConfigs[3].equals(other.playerConfigs[3]);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalVolume, musicVolume, language);
    }
}
