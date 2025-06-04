import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
<<<<<<< HEAD
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Square;
import java.util.List;
import java.util.ArrayList;

import com.bomber7.core.model.entities.Character;
=======

>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharacter {

<<<<<<< HEAD
    /** Grid. */
    private List<List<Square>> grid = new ArrayList<>();

    /** Level map. */
    private LevelMap map = new LevelMap(this.grid);

    /**
     * Test class for Character.
     */
    private static class ConcreteCharacter extends Character {
        ConcreteCharacter(String name, LevelMap map, int x, int y, int life, int speed, String spriteFP) {
            super(name, map, x, y, life, speed, spriteFP);
=======
    /**
     * Test class for Character.
     */
    private static class ConcreteCharacter extends com.bomber7.core.model.entities.Character {
        ConcreteCharacter(String name, int x, int y, int life, int speed, String spriteFP) {
            super(name, x, y, life, speed, spriteFP);
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
        }
    }

    /* ------[CONSTRUCTOR][GETTERS]------------------------------------ */

    /**
     * Test of a simple character creation.
     */
    @Test
    public void testCharacterCreation() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        assertEquals("Test", character.getName());
        assertEquals(0, character.getPositionX());
        assertEquals(0, character.getPositionY());
        assertEquals(100, character.getLife());
        assertEquals(1, character.getSpeed());
        assertEquals("path/to/sprite", character.getSpriteFP());
        assertTrue(character.isAlive());
    }

    /**
     * Test of invalid constructor fields.
     */
    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
<<<<<<< HEAD
            new ConcreteCharacter(null, this.map, 0, 0, 100, 1, "path/to/sprite");
        });
        assertThrows(IllegalPositionOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, -1, 0, 100, 1, "path/to/sprite");
        });
        assertThrows(IllegalLifeOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 0, 1, "path/to/sprite");
        });
        assertThrows(IllegalSpeedOperationException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 100, 0, "path/to/sprite");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ConcreteCharacter("Test", this.map, 0, 0, 100, 1, null);
=======
            new ConcreteCharacter(null, 0, 0, 100, 1, "path/to/sprite");
        });
        assertThrows(IllegalPositionOperationException.class, () -> {
            new ConcreteCharacter("Test", -1, 0, 100, 1, "path/to/sprite");
        });
        assertThrows(IllegalLifeOperationException.class, () -> {
            new ConcreteCharacter("Test", 0, 0, 0, 1, "path/to/sprite");
        });
        assertThrows(IllegalSpeedOperationException.class, () -> {
            new ConcreteCharacter("Test", 0, 0, 100, 0, "path/to/sprite");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ConcreteCharacter("Test", 0, 0, 100, 1, null);
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
        });
    }

    /* ------[SETTERS][GETTERS]------------------------------------ */

    /**
     * Set test of legit speed value.
     */
    @Test
    public void testSetSpeed() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        character.setSpeed(2);
        assertEquals(2, character.getSpeed());
    }

    /**
     * Set test of invalid speed value.
     */
    @Test
    public void testSetInvalidSpeed() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        assertThrows(IllegalSpeedOperationException.class, () -> {
            character.setSpeed(-1);
        });
    }

    /**
     * Add test of one life.
     */
    @Test
    public void testAddOneLife() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        character.addOneLife();
        assertEquals(101, character.getLife());
    }

    /**
     * Remove test of one life.
     */
    @Test
    public void testRemoveOneLife() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        character.removeOneLife();
        assertEquals(99, character.getLife());
    }

    /**
     * Character death test.
     */
    @Test
    public void testRemoveLastLifeDead() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 1, 1,
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 1, 1,
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
                "path/to/sprite");
        character.removeOneLife();
        assertEquals(0, character.getLife());
        assertFalse(character.isAlive());
    }

    /**
     * Move character right.
     */
    @Test
    public void testMoveRight() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 1,
                "path/to/sprite");
        character.moveRight();
        assertEquals(Character.MOOVE_VALUE, character.getPositionX());
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
                "path/to/sprite");
        character.moveRight();
        assertEquals(1, character.getPositionX());
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
    }

    /**
     * Move character left.
     */
    @Test
    public void testMoveLeft() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map,
                Character.MOOVE_VALUE, 0, 100, 1, "path/to/sprite");
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 1, 0, 100, 1,
                "path/to/sprite");
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
        character.moveLeft();
        assertEquals(0, character.getPositionX());
    }

    /**
     * Move character down.
     */
    @Test
    public void testMoveDown() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0,
                Character.MOOVE_VALUE, 100, 1, "path/to/sprite");
        character.moveDown();
        assertEquals(0, character.getPositionY());
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 0, 100, 1,
                "path/to/sprite");
        character.moveDown();
        assertEquals(1, character.getPositionY());
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
    }

    /**
     * Move character up.
     */
    @Test
    public void testMoveUp() {
<<<<<<< HEAD
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0,
                0, 100, 1, "path/to/sprite");
        character.moveUp();
        assertEquals(Character.MOOVE_VALUE, character.getPositionY());
=======
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", 0, 1, 100, 1,
                "path/to/sprite");
        character.moveUp();
        assertEquals(0, character.getPositionY());
>>>>>>> 7a136fd3f2d2c4adf36b5f8e55b97808b44669f9
    }

}
