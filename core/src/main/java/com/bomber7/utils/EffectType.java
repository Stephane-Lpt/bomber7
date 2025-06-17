package com.bomber7.utils;

/**
 * Enum listing different effects in the game.
 */
public enum EffectType {
    /**
     * Explosion effect.
     */
    EXPLOSION(
        5
    );

    /**
     * The number of animation frames of this effect.
     * This number, combined with the assetName, will genera
     */
    private final int framesCount;

    /**
     * Array containing all animated sprites in order.
     * <p>
     *     Example: EXPLOSION(5).getFrames() will return
     *     [ "explosion-1", "explosion-2", "explosion-3", "explosion-4", "explosion-5" ]
     * </p>
     * @return String list of sprite names for each animation frame.
     */
    public String[] getFrames() {
        String[] frames = new String[framesCount];

        for (int i = 0; i < framesCount; i++) {
            frames[i] = this.name() + "-" + i;
        }

        return frames;
    }

    /**
     * Getter of framesCount.
     *
     * @return The number of animation frames of this effect.
     */
    public int getFramesCount() {
        return framesCount;
    }

    /**
     * Initializes the map.
     * @param framesCount The number of animation frames of this effect.
     */
    EffectType(int framesCount) {
        this.framesCount = framesCount;
    }
}
