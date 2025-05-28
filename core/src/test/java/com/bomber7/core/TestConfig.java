package com.bomber7.core;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.jupiter.api.BeforeEach;

import com.badlogic.gdx.Input.Keys;
import com.bomber7.core.controller.Config;


/**
 * TestConfig is a placeholder class for testing purposes.
 * It currently does not contain any fields or methods.
 * This class can be expanded in the future to include test configurations or settings.
 */
public class TestConfig {
    private Config config;
    private int up;
    private int down;
    private int left;
    private int right;
    private int dropBomb;
    private int activateBomb;

    @BeforeEach
    public void setUp() {
        try {
            // Initialize the Config object with a test configuration file
            config = new Config("testConfig.txt", 
                                Keys.W, 
                                Keys.S, 
                                Keys.A, 
                                Keys.D, 
                                Keys.SPACE, 
                                Keys.ENTER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to initialize Config: " + e.getMessage());
        }
    }

    /**
     * Test the configuration settings.
     */
    @org.junit.jupiter.api.Test
    public void testConfigSettings() {
        assertEquals(Keys.W, config.getUp(), "Up key should be W");
        assertEquals(Keys.S, config.getDown(), "Down key should be S");
        assertEquals(Keys.A, config.getLeft(), "Left key should be A");
        assertEquals(Keys.D, config.getRight(), "Right key should be D");
        assertEquals(Keys.SPACE, config.getDropBomb(), "Drop Bomb key should be SPACE");
        assertEquals(Keys.ENTER, config.getActivateBomb(), "Activate Bomb key should be ENTER");
    }

    /**
     * Test the configuration file saving functionality.
     */
    @org.junit.jupiter.api.Test
    public void testSaveConfig() {
        try {
            config.saveConfig();
            // If saveConfig does not throw an exception, the test passes
        } catch (Exception e) {
            fail("Failed to save configuration: " + e.getMessage());
        }
        Config loadedConfig = null;
        try {
            // Load the configuration from the saved file
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("testConfig.txt"));
            loadedConfig = (Config) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            fail("Failed to load configuration: " + e.getMessage());
        }
        // Verify that the loaded configuration matches the original
        assertEquals(config.getUp(), loadedConfig.getUp(), "Up key should match");
        assertEquals(config.getDown(), loadedConfig.getDown(), "Down key should match");
        assertEquals(config.getLeft(), loadedConfig.getLeft(), "Left key should match");
        assertEquals(config.getRight(), loadedConfig.getRight(), "Right key should match");
        assertEquals(config.getDropBomb(), loadedConfig.getDropBomb(), "Drop Bomb key should match");
        assertEquals(config.getActivateBomb(), loadedConfig.getActivateBomb(), "Activate Bomb key should match");
    }

    /**
     * Test setter methods for configuration keys.
     */
    @org.junit.jupiter.api.Test
    public void testSetters() {
        config.setUp(Keys.UP);
        config.setDown(Keys.DOWN);
        config.setLeft(Keys.LEFT);
        config.setRight(Keys.RIGHT);
        config.setDropBomb(Keys.B);
        config.setActivateBomb(Keys.ENTER);

        assertEquals(Keys.UP, config.getUp(), "Up key should be UP");
        assertEquals(Keys.DOWN, config.getDown(), "Down key should be DOWN");
        assertEquals(Keys.LEFT, config.getLeft(), "Left key should be LEFT");
        assertEquals(Keys.RIGHT, config.getRight(), "Right key should be RIGHT");
        assertEquals(Keys.B, config.getDropBomb(), "Drop Bomb key should be B");
        assertEquals(Keys.ENTER, config.getActivateBomb(), "Activate Bomb key should be ENTER");
    }

    

}
