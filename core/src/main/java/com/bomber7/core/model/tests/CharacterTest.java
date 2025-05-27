package com.bomber7.core.model.entities;

import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    @Test
    public void testCharacterCreation() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        assertEquals("Test", character.getName());
        assertEquals(0, character.getPositionX());
        assertEquals(0, character.getPositionY());
        assertEquals(100, character.getLife());
        assertEquals(1, character.getSpeed());
        assertEquals("path/to/sprite", character.getSpriteFP());
        assertTrue(character.isAlive());
    }

    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestCharacter(null, 0, 0, 100, 1, "path/to/sprite");
        });
    }

    @Test
    public void testInvalidPosition() {
        assertThrows(IllegalPositionOperationException.class, () -> {
            new TestCharacter("Test", -1, 0, 100, 1, "path/to/sprite");
        });
    }

    @Test
    public void testInvalidLife() {
        assertThrows(IllegalLifeOperationException.class, () -> {
            new TestCharacter("Test", 0, 0, 0, 1, "path/to/sprite");
        });
    }

    @Test
    public void testInvalidSpeed() {
        assertThrows(IllegalSpeedOperationException.class, () -> {
            new TestCharacter("Test", 0, 0, 100, 0, "path/to/sprite");
        });
    }

    @Test
    public void testInvalidSpritePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestCharacter("Test", 0, 0, 100, 1, null);
        });
    }

    @Test
    public void testSetSpeed() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        character.setSpeed(2);
        assertEquals(2, character.getSpeed());
    }

    @Test
    public void testSetInvalidSpeed() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        assertThrows(IllegalSpeedOperationException.class, () -> {
            character.setSpeed(-1);
        });
    }

    @Test
    public void testAddOneLife() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        character.addOneLife();
        assertEquals(101, character.getLife());
    }

    @Test
    public void testRemoveOneLife() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        character.removeOneLife();
        assertEquals(99, character.getLife());
    }

    @Test
    public void testRemoveOneLifeToZero() {
        Character character = new TestCharacter("Test", 0, 0, 1, 1, "path/to/sprite");
        character.removeOneLife();
        assertEquals(0, character.getLife());
        assertFalse(character.isAlive());
    }

    @Test
    public void testRemoveOneLifeFromZero() {
        Character character = new TestCharacter("Test", 0, 0, 0, 1, "path/to/sprite");
        assertThrows(IllegalStateException.class, () -> {
            character.removeOneLife();
        });
    }

    @Test
    public void testMoveRight() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        character.moveRight();
        assertEquals(1, character.getPositionX());
    }

    @Test
    public void testMoveLeft() {
        Character character = new TestCharacter("Test", 1, 0, 100, 1, "path/to/sprite");
        character.moveLeft();
        assertEquals(0, character.getPositionX());
    }

    @Test
    public void testMoveDown() {
        Character character = new TestCharacter("Test", 0, 0, 100, 1, "path/to/sprite");
        character.moveDown();
        assertEquals(1, character.getPositionY());
    }

    @Test
    public void testMoveUp() {
        Character character = new TestCharacter("Test", 0, 1, 100, 1, "path/to/sprite");
        character.moveUp();
        assertEquals(0, character.getPositionY());
    }

    // Classe interne pour tester Character qui est abstraite
    private static class TestCharacter extends Character {
        public TestCharacter(String name, int x, int y, int life, int speed, String spriteFP) {
            super(name, x, y, life, speed, spriteFP);
        }
    }
}
