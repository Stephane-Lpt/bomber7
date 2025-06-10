package com.bomber7.core.model.map;

import com.badlogic.gdx.Gdx;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.views.ViewMap;
import com.bomber7.utils.Constants;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Represents a level with a map in the game.
 * Responsible for loading and parsing the map data from a file.
 */
public class LevelMap {

    private int windowWidth;
    private int windowHeight;

    /** A 2D list representing the checkerboard of squares in the level map. */
    private List<List<Square>> checkerboard;

    /**
     * Constructs a LevelMap with the specified checkerboard.
     *
     * @param checkerboard a 2D list representing the checkerboard of squares
     */
    public LevelMap(List<List<Square>> checkerboard, int windowWidth, int windowHeight) {
        this.checkerboard = checkerboard;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
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


    /**
     * Returns the checkerboard of squares.
     * @return the checkerboard of squares
     */
    public Pair<Integer, Integer> getAbsoluteCoordinates(int x, int y){

        float originX =  (this.windowWidth - Constants.TEXTURE_SIZE * this.getWidth() * ViewMap.scale ) / 2;
        float originY = (this.windowHeight - Constants.TEXTURE_SIZE * this.getHeight() * ViewMap.scale) / 2;

        return Pair.of((int) (originX + x * Constants.TEXTURE_SIZE * ViewMap.scale), (int) (originY + y * Constants.TEXTURE_SIZE * ViewMap.scale));
    }

    /**
     * Returns the absolute coordinates of the square at the specified coordinates.
     * This method calculates the absolute pixel coordinates based on the current screen size and scale.
     *
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return a Pair containing the absolute x and y coordinates
     */
    public Pair<Integer, Integer> getSquareCoordinates(int x, int y){
        float originX =  (this.windowWidth - Constants.TEXTURE_SIZE * this.getWidth() * ViewMap.scale ) / 2;
        float originY = (this.windowHeight - Constants.TEXTURE_SIZE * this.getHeight() * ViewMap.scale) / 2;

        return Pair.of((int) ((x - originX) / (Constants.TEXTURE_SIZE * ViewMap.scale)),
                       (int) ((y - originY) / (Constants.TEXTURE_SIZE * ViewMap.scale)));

    }

}


