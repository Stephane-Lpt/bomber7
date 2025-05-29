package com.bomber7.utils;

public enum PlayerStrategy {
    HUMAN,
    PEACEFUL,
    BEGINNER,
    PRO;

    private static final PlayerStrategy[] VALUES = values();

    public PlayerStrategy next() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    public PlayerStrategy previous() {
        return VALUES[(this.ordinal() - 1 + VALUES.length) % VALUES.length];
    }
}

