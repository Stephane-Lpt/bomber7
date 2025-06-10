import com.bomber7.core.model.exceptions.IllegalLifeOperationException;
import com.bomber7.core.model.exceptions.IllegalPositionOperationException;
import com.bomber7.core.model.exceptions.IllegalSpeedOperationException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Square;
import java.util.List;
import java.util.ArrayList;

import com.bomber7.core.model.entities.Character;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    /** Grid. */
    private List<List<Square>> grid = new ArrayList<>();

    /** Level map. */
    private LevelMap map = new LevelMap(this.grid, 800, 600);

    /**
     * Test class for Character.
     */
    private static class ConcreteCharacter extends Character {
        ConcreteCharacter(String name, LevelMap map, int x, int y, int life, int speed, String spriteFP) {
            super(name, map, x, y, life, speed, spriteFP);
        }
    }

    /* ------[CONSTRUCTOR][GETTERS]------------------------------------ */

    /**
     * Test of a simple character creation.
     */
    @Test
    public void testCharacterCreation() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
                "path/to/sprite");
        assertEquals("Test", character.getName());
        assertEquals(0, character.getPositionX());
        assertEquals(0, character.getPositionY());
        assertEquals(100, character.getLife());
        assertEquals(10, character.getSpeed());
        assertEquals("path/to/sprite", character.getSpriteFP());
        assertTrue(character.isAlive());
    }

    /**
     * Test of invalid constructor fields.
     */
    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
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
            new ConcreteCharacter("Test", this.map, 0, 0, 100, 10, null);
        });
    }

    /* ------[SETTERS][GETTERS]------------------------------------ */

    /**
     * Set test of legit speed value.
     */
    @Test
    public void testSetSpeed() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
                "path/to/sprite");
        character.setSpeed(2);
        assertEquals(2, character.getSpeed());
    }

    /**
     * Set test of invalid speed value.
     */
    @Test
    public void testSetInvalidSpeed() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
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
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
                "path/to/sprite");
        character.addOneLife();
        assertEquals(101, character.getLife());
    }

    /**
     * Remove test of one life.
     */
    @Test
    public void testRemoveOneLife() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
                "path/to/sprite");
        character.removeOneLife();
        assertEquals(99, character.getLife());
    }

    /**
     * Character death test.
     */
    @Test
    public void testRemoveLastLifeDead() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 1, 1,
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
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0, 0, 100, 10,
                "path/to/sprite");
        character.moveRight();
        assertEquals(10, character.getPositionX());
    }

    /**
     * Move character left.
     */
    @Test
    public void testMoveLeft() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map,
                10, 0, 100, 10, "path/to/sprite");
        character.moveLeft();
        assertEquals(0, character.getPositionX());
    }

    /**
     * Move character down.
     */
    @Test
    public void testMoveDown() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0,
                10, 100, 10, "path/to/sprite");
        character.moveDown();
        assertEquals(0, character.getPositionY());
    }

    /**
     * Move character up.
     */
    @Test
    public void testMoveUp() {
        com.bomber7.core.model.entities.Character character = new ConcreteCharacter("Test", this.map, 0,
                0, 100, 10, "path/to/sprite");
        character.moveUp();
        assertEquals(10, character.getPositionY());
    }

}
