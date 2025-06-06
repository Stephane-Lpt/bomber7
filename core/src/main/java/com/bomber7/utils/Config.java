package com.bomber7.utils;

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
     * Constructs a new {@code Config} instance with default values.
     */
    public Config() {
        this.globalVolume = DefaultConfig.GLOBAL_VOLUME;
        this.musicVolume = DefaultConfig.MUSIC_VOLUME;
        this.language = DefaultConfig.LANGUAGE;
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

        if (globalVolume != other.globalVolume) {
            return false;
        }
        if (musicVolume != other.musicVolume) {
            return false;
        }
        if (language != other.language) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalVolume, musicVolume, language);
    }
}
