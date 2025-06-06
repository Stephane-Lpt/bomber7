package com.bomber7.utils;

import java.io.Serializable;
import java.util.Objects;

public class Config implements Serializable {

    private int globalVolume;
    private int musicVolume;
    private Language language;

    public Config() {
        this.globalVolume = DefaultConfig.GLOBAL_VOLUME;
        this.musicVolume = DefaultConfig.MUSIC_VOLUME;
        this.language = DefaultConfig.LANGUAGE;
    }

    public Config(Config other) {
        this.globalVolume = other.globalVolume;
        this.musicVolume = other.musicVolume;
        this.language = other.language;
    }

    public int getGlobalVolume() {
        return globalVolume;
    }

    public void setGlobalVolume(int volume) {
        this.globalVolume = volume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int volume) {
        this.musicVolume = volume;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Config)) return false;
        Config other = (Config) obj;

        if (globalVolume != other.globalVolume) return false;
        if (musicVolume != other.musicVolume) return false;
        if (language != other.language) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalVolume, musicVolume, language);
    }
}
