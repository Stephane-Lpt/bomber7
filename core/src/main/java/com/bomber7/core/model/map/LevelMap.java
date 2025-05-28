package com.bomber7.core.model.map;

import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    private List<List<Square>> checkerboard;

    public LevelMap(List<List<Square>> checkerboard) {
        this.checkerboard = checkerboard;
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
        if (y < 0 || y >= checkerboard.size()) {
            throw new IndexOutOfBoundsException("Invalid row index y=" + y);
        }
        if (x < 0 || x >= checkerboard.get(y).size()) {
            throw new IndexOutOfBoundsException("Invalid column index x=" + x + " for row " + y);
        }

        return this.checkerboard.get(y).get(x);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Square> row : checkerboard) {
            for (Square square : row) {
                sb.append(square.toString());
            }
            sb.append('\n');
        }

        return sb.toString();
    }

}


