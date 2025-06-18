package com.bomber7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.bomber7.core.ConfigManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    private final List<SoundType> fightTracks = Arrays.asList(
        SoundType.BRAIN_ROT, SoundType.BEAST_MODE, SoundType.EPIC_FIGHT_1, SoundType.EPIC_FIGHT_2, SoundType.EPIC_FIGHT_3
    );

    private Music currentMusic;                // Menu music
    private SoundType currentMusicType;
    private Music currentFightMusic;
    private SoundType currentFightMusicType;

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
        loadSound(SoundType.BOMB_CHARGE, "sounds/bomb_charge.mp3");
        loadSound(SoundType.FOOTSTEP_1, "sounds/footstep_1.mp3");
        loadSound(SoundType.FOOTSTEP_2, "sounds/footstep_2.mp3");
        loadSound(SoundType.FOOTSTEP_3, "sounds/footstep_3.mp3");
        loadSound(SoundType.GAME_OVER, "sounds/game_over.mp3");

        loadMusic(SoundType.BEAST_MODE, "sounds/beast_mode.mp3");
        loadMusic(SoundType.ELEVATOR, "sounds/elevator.mp3");
        loadMusic(SoundType.GUITAR, "sounds/guitar.mp3");
        loadMusic(SoundType.EPIC_FIGHT_1, "sounds/epic_fight_1.mp3");
        loadMusic(SoundType.EPIC_FIGHT_2, "sounds/epic_fight_2.mp3");
        loadMusic(SoundType.EPIC_FIGHT_3, "sounds/epic_fight_3.mp3");
        loadMusic(SoundType.BRAIN_ROT, "sounds/brain_rot.mp3");

        currentMusic = null;
        currentMusicType = null;
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
     * Play menu or general music.
     * Automatically pauses fight music if it's playing.
     * @param type the music type to play
     */
    public void playMenuMusic(SoundType type) {
        if (type == currentMusicType) return;

        // Pauses fight music.
        if (currentFightMusic != null && currentFightMusic.isPlaying()) {
            currentFightMusic.pause();
        }

        stopCurrentMenuMusic();

        currentMusic = musics.get(type);
        currentMusic.setLooping(true);
        currentMusicType = type;

        updateMusicVolume();
        currentMusic.play();
    }

    /**
     * Play or resume fight music from saved position.
     * Starts random fight music if none exists.
     */
    public void playFightMusic() {
        if (currentFightMusic != null && currentFightMusic.isPlaying()) return;

        stopCurrentMenuMusic(); // Stop any general music

        if (currentFightMusic != null) {
            updateMusicVolume();
            currentFightMusic.play();
            return;
        }

        SoundType randomType = fightTracks.get(new Random().nextInt(fightTracks.size()));
        currentFightMusic = musics.get(randomType);
        currentFightMusicType = randomType;

        currentFightMusic.setLooping(true);
        updateMusicVolume();
        currentFightMusic.play();
    }

    /**
     * Resets the fight music.
     */
    public void resetFightMusic() {
        if (currentFightMusic != null) {
            currentFightMusic.stop();
            currentFightMusic = null;
            currentFightMusicType = null;
        }
    }

    /**
     * Pauses fight music.
     */
    public void pauseFightMusic() {
        if (currentFightMusic != null && currentFightMusic.isPlaying()) {
            currentFightMusic.pause();
        }
    }

    /**
     * Stops the currently playing menu/general music.
     */
    private void stopCurrentMenuMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic = null;
            currentMusicType = null;
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
