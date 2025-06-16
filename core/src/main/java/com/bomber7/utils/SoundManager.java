package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

/**
 * SoundManager manages sound effects used in the game.
 */
public final class SoundManager {
    /**
     * The singleton instance of the SoundManager.
     */
    private static SoundManager instance;
    /**
     * A hashmap that contains all the sounds (values) mapped to a SoundType (key).
     */
    private final Map<SoundType, Sound> sounds = new HashMap<>();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private SoundManager() {
        super();
    }

    /**
     * Retrieves the singleton instance of the {@code SoundManager}.
     *
     * @return the single {@code SoundManager} instance.
     */
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Initializes the SoundManager manager.
     * <p>
     * This method must be called before any sound access / play occurs.
     * </p>
     *
     */
    public void initialize() {
        load(SoundType.HOVER, "sounds/hover.wav");
        load(SoundType.CLICK, "sounds/click.wav");
    }

    /**
     * Load a sound.
     * @param type the sound to load
     * @param path the path to the sound asset
     */
    private void load(SoundType type, String path) {
        sounds.put(type, Gdx.audio.newSound(Gdx.files.internal(path)));
    }

    /**
     * Play a sound.
     * @param type the sound to play
     */
    public void play(SoundType type) {
        sounds.get(type).play();
    }

    /**
     * Disposes of the sound resources to free memory.
     * Should be called when the sounds are no longer needed.
     */
    public void dispose() {
        sounds.forEach((soundType, sound) -> {
            sound.dispose();
        });
    }
}
