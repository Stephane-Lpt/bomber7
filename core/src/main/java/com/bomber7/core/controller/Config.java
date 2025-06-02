package com.bomber7.core.controller;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Input.Keys;

public class Config implements java.io.Serializable {
    private int up;
    private int down;
    private int left;
    private int right;
    private int dropBomb;
    private int activateBomb;

    private String fileString;


    public Config(String fileString, int up, int down, int left, int right, int dropBomb, int activateBomb) throws Exception {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.dropBomb = dropBomb;
        this.activateBomb = activateBomb;
        this.fileString = fileString;
    }

    /**
     * Saves the current configuration to a file.
     * @param fileString The path to the file where the configuration will be saved.
     */
    public void saveConfig() throws Exception {
        java.io.File file = new java.io.File(this.fileString);
        java.io.File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
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
