package com.bomber7.core.model;

import com.bomber7.core.model.square.Square;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.bomber7.utils.MapParser.createTextureMatrix;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    private String backgroundCsvFilePath;
    private String breakableCsvFilePath;
    private String unbreakableCsvFilePath;
    private List<List<Square>> map;


    /**
     * Constructs a LevelMap object by loading the map data from the specified CSV files.
     *
     * @param backgroundCsvPath  Path to the CSV file containing the background squares placement.
     * @param breakableCsvPath   Path to the CSV file containing breakable walls placement.
     * @param unbreakableCsvPath Path to the CSV file containing unbreakable walls placement.
     */
    public LevelMap(String backgroundCsvPath, String breakableCsvPath, String unbreakableCsvPath) {
        this.backgroundCsvFilePath = backgroundCsvPath;
        this.breakableCsvFilePath = breakableCsvPath;
        this.unbreakableCsvFilePath = unbreakableCsvPath;
        this.map = loadMap(backgroundCsvPath, breakableCsvPath, unbreakableCsvPath);
    }

    private List<List<Square>> loadMap(String backgroundCsvPath, String breakableCsvPath, String unbreakableCsvPath) {
        Path jsonPath = Paths.get("../assets/textures/tileset.tsj");
        return createTextureMatrix(backgroundCsvPath, breakableCsvPath, unbreakableCsvPath, jsonPath.toAbsolutePath().toString());
    }

    /**
     * Returns the square at the specified coordinates.
     *
     * @param x  the x-coordinate of the square
     * @param y  the y-coordinate of the square
     * @return   the square at the specified coordinates
     * @throws IndexOutOfBoundsException if the coordinates are out of bounds
     */
    public Square getSquare(int x, int y) {
        if (y < 0 || y >= map.size()) {
            throw new IndexOutOfBoundsException("Invalid row index y=" + y);
        }
        if (x < 0 || x >= map.get(y).size()) {
            throw new IndexOutOfBoundsException("Invalid column index x=" + x + " for row " + y);
        }

        return this.map.get(y).get(x);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Square> row : map) {
            for (Square square : row) {
                sb.append(square.toString());
            }
            sb.append('\n');
        }

        return sb.toString();
    }

}


