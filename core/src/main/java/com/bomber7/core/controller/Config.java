package com.bomber7.core.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Config class is used to manage the configuration settings for the game. It
 * allows setting and getting key bindings for player controls. The
 * configuration can be saved to a file.
 */

public class Config implements java.io.Serializable {

    /** The key for moving up. */
    private int up;
    /** The key for moving down. */
    private int down;
    /** The key for moving left. */
    private int left;
    /** The key for moving right. */
    private int right;
    /** The key for dropping a bomb. */
    private int dropBomb;
    /** The key for activating a bomb. */
    private int activateBomb;
    /** The path to the configuration file. */
    private String fileString;

    /**
     * Constructor for the Config class. Initializes the configuration with default
     * key bindings.
     * @param fileString   The path to the configuration file.
     * @param up           The key for moving up.
     * @param down         The key for moving down.
     * @param left         The key for moving left.
     * @param right        The key for moving right.
     * @param dropBomb     The key for dropping a bomb.
     * @param activateBomb The key for activating a bomb.
     */
    public Config(String fileString, int up, int down, int left, int right, int dropBomb, int activateBomb) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dropBomb = dropBomb;
        this.activateBomb = activateBomb;
        this.fileString = fileString;
    }

    /**
     * Class method to load the configuration from a file.
     * @param configFile the path to the configuration file.
     * @return the Config object loaded from the file, or null if an error occurs.
     */
    public static Config loadConfig(String configFile) {
        Config config = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(configFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            config = (Config) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * Saves the current configuration to a file.
     */
    public void saveConfig() {
        java.io.File file = new java.io.File(this.fileString);
        java.io.File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the key for moving up.
     * @param up The key to set for moving up.
     */
    public void setUp(int up) {
        this.up = up;
    }

    /**
     * Set the key for moving down.
     * @param down the key to set for moving down
     */
    public void setDown(int down) {
        this.down = down;
    }

    /**
     * Set the key for moving left.
     * @param left the key to set for moving left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Set the key for moving right.
     * @param right the key to set for moving right
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * Set the key for dropping a bomb.
     * @param dropBomb the key to set for dropping a bomb
     */
    public void setDropBomb(int dropBomb) {
        this.dropBomb = dropBomb;
    }

    /**
     * Set the key for activating a bomb.
     * @param activateBomb the key to set for activating a bomb
     */
    public void setActivateBomb(int activateBomb) {
        this.activateBomb = activateBomb;
    }

    /**
     * Get the key for moving up.
     * @return the key for moving up
     */
    public int getUp() {
        return up;
    }

    /**
     * Get the key for moving down.
     * @return the key for moving down
     */
    public int getDown() {
        return down;
    }

    /**
     * Get the key for moving left.
     * @return the key for moving left
     */
    public int getLeft() {
        return left;
    }

    /**
     * Get the key for moving right.
     * @return the key for moving right
     */
    public int getRight() {
        return right;
    }

    /**
     * Get the key for dropping a bomb.
     * @return the key for dropping a bomb
     */
    public int getDropBomb() {
        return dropBomb;
    }

    /**
     * Get the key for activating a bomb.
     * @return the key for activating a bomb
     */
    public int getActivateBomb() {
        return activateBomb;
    }
}
