package com.bomber7.core.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    /**
     * The file name or path from which the map is loaded.
     */
    private static final int WIDTH = 35;
    private static final int HEIGHT = 24;

    private String fileName;
    private Square[][] map;

    /**
     * Constructs a new LevelMap by loading it from the specified file.
     *
     * @param fileName  the name or path of the file to load the map from
     */
    LevelMap(String fileName) {
        this.fileName = fileName;
        this.map = new Square[HEIGHT][WIDTH];
        loadMap(fileName);
    }

    /**
     * Loads the map data from the given file path.
     * The method is responsible for parsing the file and initializing the map structure.
     *
     * @param filepath  the path to the map file
     */
    private void loadMap(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String ligne;
            int numeroLigne = 0;

            while ((ligne = br.readLine()) != null) {
                // Découpe la ligne en colonnes selon la virgule
                String[] colonnes = ligne.split(",");


                for (int i = 0; i < colonnes.length; i++) {
                    Square s = new Square(numeroLigne, i);
                    this.map[numeroLigne][i] = s;
                    
                }

                numeroLigne++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


