package src.test.java.com.bomber7.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.com.bomber7.core.model.Bomb;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class Bombtest {

    private Bomb bomb;
    private Map map;

    @BeforeEach
    void setUp() {
        bomb = new Bomb(3, "bomb.png") {
            @Override
            public void onExplosion(Map m, int x, int y) {
                System.out.println("Explosion en case (" + x + "," + y + ")");
            }
        };
        // init instance de Map
    }

    @Test
    void testGetPower() {
        assertEquals(3, bomb.getPower());
    }

    @Test
    void testSetPowerValid() {
        bomb.setPower(5);
        assertEquals(5,bomb.getPower());
    }

    @Test
    void testSetPowerInvalid() {
        assertThrows(IllegalArgumentException.class, () -> bomb.setPower(0));
        assertThrows(IllegalArgumentException.class, () -> bomb.setPower(-1));
    }

    @Test
    void testActivateBomb() {
        bomb.activateBomb(map);
    }
}
