package com.bomber7.utils;

public enum GameCharacter {
    STUDENT("sprite-student", 0),
    BDE("sprite-bde", 100),
    GRADUATE("sprite-graduate", 200);

    private final String drawableName;
    private final int pointsToUnlock;

    private static final GameCharacter[] CHARACTERS = values();

    GameCharacter(String drawableName, int pointsToUnlock) {
        this.drawableName = drawableName;
        this.pointsToUnlock = pointsToUnlock;
    }

    public String drawableName() {
        return drawableName;
    }

    public int getPointsToUnlock() {
        return pointsToUnlock;
    }

    public GameCharacter next() {
        return CHARACTERS[(this.ordinal() + 1) % CHARACTERS.length];
    }

    public GameCharacter previous() {
        return CHARACTERS[(this.ordinal() - 1 + CHARACTERS.length) % CHARACTERS.length];
    }
}
