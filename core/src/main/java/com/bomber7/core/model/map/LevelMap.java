package com.bomber7.core.model.map;

import com.bomber7.core.model.square.Square;

import java.util.List;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    /** A 2D list representing the checkerboard of squares in the level map. */
    private List<List<Square>> checkerboard;

    /**
     * Constructs a LevelMap with the specified checkerboard.
     *
     * @param checkerboard a 2D list representing the checkerboard of squares
     */
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

    /**
     * Returns the width of the checkerboard (number of columns).
     * We look at the first row because we verify in the factory that
     * all rows have the same number of columns.
     * @return the width of the checkerboard
     */
    public int getWidth() {
        return checkerboard.get(0).size();
    }

    /**
     * Returns the height of the checkerboard (number of rows).
     * @return the height of the checkerboard
     */
    public int getHeight() {
        return checkerboard.size();
    }

}


