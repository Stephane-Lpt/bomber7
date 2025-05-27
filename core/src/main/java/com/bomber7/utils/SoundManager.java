package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

/**
 * SoundManager manages sound effects used in the game.
 */
public class SoundManager {
    /**
     * A hashmap that contains all the sounds (values) mapped to a SoundType (key).
     */
    private final Map<SoundType, Sound> sounds = new HashMap<>();

    /**
     * Loads all sounds into the sound map.
     */
    public SoundManager() {
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
