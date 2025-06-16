package com.bomber7.utils;

/**
 * Represents a visual effect at a specific position with an associated sprite animation.
 */
public final class Effect {

    /**
     * X coordinate of the effect on the map (square coord).
     */
    private int x;

    /**
     * Y coordinate of the effect on the map (square coord).
     */
    private int y;

    /**
     * Type of the effect.
     */
    private EffectType type;

    /**
     * Constructs a new Effect.
     *
     * @param x           the X-coordinate of the square where this effect is placed
     * @param y           the Y-coordinate of the square where this effect is placed
     * @param type        the {@code EffectType} of the effect
     */
    public Effect(int x, int y, EffectType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Getter for X-coordinate.
     * @return the X-coordinate of the effect
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for Y-coordinate.
     * @return the Y-coordinate of the effect
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for effect type.
     * @return the effect type
     */
    public EffectType getType() {
        return type;
    }
}
