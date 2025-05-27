package com.bomber7.core.model;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    public static final int SIZEX = 35;
    public static final int SIZEY = 25;

    /**
     * The file name or path from which the map is loaded.
     */
    private String fileName;

    /**
     * Constructs a new LevelMap by loading it from the specified file.
     *
     * @param fileName  the name or path of the file to load the map from
     */
    LevelMap(String fileName) {
        this.fileName = fileName;
        loadMap(fileName);
    }

    /**
     * Loads the map data from the given file path.
     * The method is responsible for parsing the file and initializing the map structure.
     *
     * @param filepath  the path to the map file
     */
    private void loadMap(String filepath) {
        // Load map from file
        // Parse the file and create the map
    }
}
