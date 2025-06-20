package com.bomber7.core;

import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.utils.Constants;
import com.bomber7.utils.GameCharacter;

import com.bomber7.core.model.entities.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    /** Grid. */
    private LevelMap map = LevelMapFactory.createLevelMap("foy", 800, 600);
    /** Game character. */
    private GameCharacter gameCharacter = GameCharacter.STUDENT;

    /**
     * Test class for Character.
     */
    private static class ConcreteCharacter extends Character {
        ConcreteCharacter(String name, LevelMap map, int x, int y, int life, int speed, GameCharacter gameCharacter) {
            super(name, map, x, y, life, speed, gameCharacter);
        }
    }

    /**
     * A character with 100 hp.
     */
    private ConcreteCharacter invincibleCharacter;

    /**
     * A default character with 1 life.
     */
    private ConcreteCharacter defaultCharacter;

    /**
     * Test setup.
     */
    @BeforeEach
    void setup() {
        invincibleCharacter = Mockito.spy(new ConcreteCharacter(
            "Test",
            this.map,
            2,
            22,
            100,
            20,
            gameCharacter
        ));

        defaultCharacter = Mockito.spy(new ConcreteCharacter(
            "Test",
            this.map,
            1,
            23,
            1,
            1,
            gameCharacter
        ));

        Mockito.doNothing().when(invincibleCharacter).playDeadSound();
        Mockito.doNothing().when(defaultCharacter).playDeadSound();
    }

    /* ------[CONSTRUCTOR][GETTERS]------------------------------------ */

    /**
     * Test of a simple character creation.
     */
    @Test
    public void testCharacterCreation() {
        assertEquals("Test", invincibleCharacter.getName());
        assertEquals(2, invincibleCharacter.getMapX());
        assertEquals(22, invincibleCharacter.getMapY());
        assertEquals(100, invincibleCharacter.getLife());
        assertEquals(20, invincibleCharacter.getSpeed());
        assertEquals(gameCharacter, invincibleCharacter.getGameCharacter());
        assertEquals("sprite-student", invincibleCharacter.getGameCharacter().getDrawableName());
        assertTrue(invincibleCharacter.isAlive());
    }

    /**
     * Test of invalid constructor fields.
     */
    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ConcreteCharacter(null, this.map, 0, 0, 100, 1, gameCharacter);
        });
        assertThrows(IllegalPositionOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, -1, 0, 100, 1, gameCharacter);
        });
        assertThrows(IllegalLifeOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 0, 1, gameCharacter);
        });
        assertThrows(IllegalSpeedOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 100, 0, gameCharacter);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 100, 10, null);
        });
    }

    /* ------[SETTERS][GETTERS]------------------------------------ */

    /**
     * Set test of legit speed value.
     */
    @Test
    public void testSetSpeed() {
        defaultCharacter.setSpeed(2);
        assertEquals(2, defaultCharacter.getSpeed());
    }


    /**
     * Add test of one life.
     */
    @Test
    public void testAddOneLife() {
        invincibleCharacter.addOneLife();
        assertEquals(101, invincibleCharacter.getLife());
    }

    /**
     * Remove test of one life.
     */
    @Test
    public void testRemoveOneLife() {
        invincibleCharacter.removeOneLife();
        assertEquals(99, invincibleCharacter.getLife());
    }

    /**
     * Character death test.
     */
    @Test
    public void testRemoveLastLifeDead() {
        defaultCharacter.removeOneLife();
        assertEquals(0, defaultCharacter.getLife());
        assertFalse(defaultCharacter.isAlive());
    }

    /**
     * Move character right.
     */
    @Test
    public void testMoveRight() {
        Character character = Mockito.spy(new ConcreteCharacter("Test", this.map,
                1, 23, 100, 20, gameCharacter));
        Mockito.doNothing().when(defaultCharacter).playDeadSound();

        int initialMapX = character.getMapX();
        int initialX = character.getPositionX();

        character.moveRight();
        assertEquals(initialMapX + 1, character.getMapX());
        assertEquals(initialX + 20, character.getPositionX());

        character.moveRight();
        assertEquals(initialMapX + 1, character.getMapX());
        assertEquals(initialX + 20, character.getPositionX());
    }

    /**
     * Move character left.
     */
    @Test
    public void testMoveLeft() {
        Character character = Mockito.spy(new ConcreteCharacter("Test", this.map,
            2, 23, 100, 20, gameCharacter));
        Mockito.doNothing().when(defaultCharacter).playDeadSound();

        int initialMapX = character.getMapX();
        int initialX = character.getPositionX();

        character.moveLeft();
        assertEquals(initialMapX - 1, character.getMapX());
        assertEquals(initialX - 20, character.getPositionX());

        character.moveLeft();
        assertEquals(initialMapX - 1, character.getMapX());
        assertEquals(initialX - 20, character.getPositionX());
    }

    /**
     * Move character down.
     */
    @Test
    public void testMoveDown() {
        Character character = Mockito.spy(new ConcreteCharacter("Test", this.map,
            1, 23, 100, 20, gameCharacter));
        Mockito.doNothing().when(defaultCharacter).playDeadSound();

        int initialY = character.getPositionY();
        int initialMapY = character.getMapY();

        character.moveDown();
        assertEquals(initialY - 20, character.getPositionY());
        assertEquals(initialMapY - 1, character.getMapY());

        character.moveDown();
        assertEquals(initialY - 20, character.getPositionY());
        assertEquals(initialMapY - 1, character.getMapY());
    }

    /**
     * Move character up.
     */
    @Test
    public void testMoveUp() {
        Character character = Mockito.spy(new ConcreteCharacter("Test", this.map,
            1, 22, 100, 20, gameCharacter));
        Mockito.doNothing().when(defaultCharacter).playDeadSound();

        int initialY = character.getPositionY();
        int initialMapY = character.getMapY();

        character.moveUp();
        assertEquals(initialY + 20, character.getPositionY());
        assertEquals(initialMapY + 1, character.getMapY());

        character.moveUp();
        assertEquals(initialY + 20, character.getPositionY());
        assertEquals(initialMapY + 1, character.getMapY());
    }

    /**
     * Tests that the player resets correctly.
     */
    @Test
    public void testCharacterReset() {
        int initialMapX = defaultCharacter.getMapX();
        int initialMapY = defaultCharacter.getMapY();

        defaultCharacter.removeOneLife();
        defaultCharacter.moveDown();
        defaultCharacter.moveRight();
        defaultCharacter.setSpeed(10);

        defaultCharacter.reset();

        assertEquals(initialMapX, defaultCharacter.getMapX());
        assertEquals(initialMapY, defaultCharacter.getMapY());
        assertEquals(Constants.DEFAULT_LIFE, defaultCharacter.getLife());
        assertEquals(Constants.DEFAULT_SPEED, defaultCharacter.getSpeed());
        assertTrue(defaultCharacter.isAlive());
    }

}
