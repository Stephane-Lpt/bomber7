package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.bomber7.core.ConfigManager;

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
     * A hashmap that contains all the music (values) mapped to a SoundType (key).
     */
    private final Map<SoundType, Music> musics = new HashMap<>();

    /**
     * Currently playing music.
     */
    private Music currentMusic;


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
        loadSound(SoundType.HOVER, "sounds/hover.wav");
        loadSound(SoundType.CLICK, "sounds/click.wav");
        loadSound(SoundType.EXPLOSION, "sounds/explosion.mp3");

        loadMusic(SoundType.BEAST_MODE, "sounds/beast_mode.mp3");
        loadMusic(SoundType.ELEVATOR, "sounds/elevator.mp3");

        currentMusic = null;
    }

    /**
     * Load a sound.
     * @param type the sound to load
     * @param path the path to the sound asset
     */
    private void loadSound(SoundType type, String path) {
        sounds.put(type, Gdx.audio.newSound(Gdx.files.internal(path)));
    }

    /**
     * Load a music.
     * @param type the music to load
     * @param path the path to the music asset
     */
    private void loadMusic(SoundType type, String path) {
        musics.put(type, Gdx.audio.newMusic(Gdx.files.internal(path)));
    }

    /**
     * Play a sound.
     * @param type the sound to play
     */
    public void play(SoundType type) {
        float volume = ConfigManager.getInstance().getConfig().getGlobalVolume() / Constants.VOLUME_CONVERT_RATIO;

        sounds.get(type).play(volume);
    }

    /**
     * Play a music.
     * @param type the sound to play
     */
    public void playMusic(SoundType type) {
        currentMusic = musics.get(type);
        currentMusic.setLooping(true);
        updateMusicVolume();
        currentMusic.play();
    }

    /**
     * Stops the currently playing music if any.
     */
    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    /**
     * Updates music volume.
     * Has to be called whenever the music volume is modified, so the modification is applied instantly.
     */
    public void updateMusicVolume() {
        float volume = Math.min(
            ConfigManager.getInstance().getConfig().getGlobalVolume(),
            ConfigManager.getInstance().getConfig().getMusicVolume()
        ) / Constants.VOLUME_CONVERT_RATIO;

        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
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
