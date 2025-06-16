package com.bomber7.core.model.entities;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.GameCharacter;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * AI player class.
*/
public class AIPlayer extends Player {

    /**
     * Relative coordinates representing potential safe directions:
     * Each array is composed of 4 values:
     * [first step dx, first step dy, second step dx, second step dy]
     * Meaning: if (first step) is walkable AND (second step) is walkable → it's a safe spot.
     */
    private static final int[][] SAFE_DIRECTIONS = {
        { 1, 0,  1,  1}, // Right then Up-Right
        { 1, 0,  1, -1}, // Right then Down-Right
        {-1, 0, -1,  1}, // Left then Up-Left
        {-1, 0, -1, -1}, // Left then Down-Left
        { 0, 1,  1,  1}, // Up then Up-Right
        { 0, 1, -1,  1}, // Up then Up-Left
        { 0,-1,  1, -1}, // Down then Down-Right
        { 0,-1, -1, -1}  // Down then Down-Left
    };

    /**
     * AIPlayer Constructor.
     * @param map           The map.
     * @param mapX          The x-coordinate of the player
     * @param mapY          The y-coordinate of the player
     * @param gameCharacter The game character type
     */
    public AIPlayer(LevelMap map, int mapX, int mapY, GameCharacter gameCharacter) {
        super("AI", map, mapX, mapY, 1, 32, gameCharacter);
    }

    /**
     * AI BOT movement logic.
     * The AI player is always moving towards the nearest player.
     * If no player is found, it will move randomly.
     * @param players The list of players in the game.
     */
    public void moveAI(Player[] players) {
        if (players == null || players.length == 0) {
            moveRandomly();
            return;
        }
        Player nearestPlayer = findNearestPlayer(players);
        if (nearestPlayer != null) {
            moveTowardsPlayer(nearestPlayer);
        } else {
            moveRandomly();
        }
    }

    public void tick(float delta){


        if(this.hasSafeSpot()) {
            System.out.println("HAS SAFE SPOT");
            if (this.dropBomb()) {
                boolean hasMovedToSafeSpot = this.moveToSafeSpot();
                System.out.println("SUR SAFE SPOT OU PAS ? " + hasMovedToSafeSpot);
            }
        }
    }

    /**
     * Checks whether there is any safe spot around the player.
     * A safe spot is defined as two consecutive walkable tiles (in a given pattern).
     */
    public boolean hasSafeSpot() {
        int baseX = getMapX();
        int baseY = getMapY();
        LevelMap map = getMap();

        for (int[] dir : SAFE_DIRECTIONS) {
            int dx1 = dir[0], dy1 = dir[1];
            int dx2 = dir[2], dy2 = dir[3];

            int x1 = baseX + dx1, y1 = baseY + dy1;
            int x2 = baseX + dx2, y2 = baseY + dy2;

            if (isWalkable(x1, y1) && isWalkable(x2, y2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to move the player toward the first valid safe spot found.
     * The movement is split into two steps based on the SAFE_DIRECTIONS pattern.
     * Returns true if a safe spot is found and the player is moved.
     */
    public boolean moveToSafeSpot() {
        int baseX = getMapX();
        int baseY = getMapY();

        for (int[] dir : SAFE_DIRECTIONS) {
            int dx1 = dir[0], dy1 = dir[1];
            int dx2 = dir[2], dy2 = dir[3];

            int x1 = baseX + dx1, y1 = baseY + dy1;
            int x2 = baseX + dx2, y2 = baseY + dy2;

            if (isWalkable(x1, y1) && isWalkable(x2, y2)) {
                moveBy(dx1, dy1);                      // Move to the first step
                moveBy(dx2 - dx1, dy2 - dy1);          // Then to the final safe spot
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the player by a relative offset.
     * @param dx horizontal offset (-1 = left, 1 = right)
     * @param dy vertical offset (-1 = down, 1 = up)
     */
    private void moveBy(int dx, int dy) {
        if (dx == 1) moveRight();
        else if (dx == -1) moveLeft();

        if (dy == 1) moveUp();
        else if (dy == -1) moveDown();
    }

    /**
     * Checks if the specified map coordinates are walkable (and in bounds).
     */
    private boolean isWalkable(int mapX, int mapY) {
        LevelMap map = getMap();
        if (mapX < 0 || mapY < 0 || mapX >= map.getWidth() || mapY >= map.getHeight()) {
            return false;
        }
        return map.getSquare(mapX, mapY).isWalkable();
    }

    /**
     * Finds the nearest player to this AI player.
     * @param players The list of players in the game.
     * @return The nearest player, or null if no players are alive.
     */
    public Player findNearestPlayer(Player[] players) {
        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;
        for (Player player : players) {
            if (player.isAlive()) {
                double distance = calculateDistance(player);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPlayer = player;
                }
            }
        }
        return nearestPlayer;
    }

    /**
     * Calculates the distance between this AI player and another player.
     * @param player The player to calculate the distance to.
     * @return The distance between the two players.
     */
    private double calculateDistance(Player player) {
        int deltaX = this.getMapX() - player.getMapX();
        int deltaY = this.getMapY() - player.getMapY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Moves the AI player towards the specified player.
     * @param player The player to move towards.
     */
    private void moveTowardsPlayer(Player player) {
        int playerX = player.getMapX();
        int playerY = player.getMapY();
        if (this.getMapX() < playerX) {
            this.moveRight();
        } else if (this.getMapX() > playerX) {
            this.moveLeft();
        }
        if (this.getMapY() < playerY) {
            this.moveUp();
        } else if (this.getMapY() > playerY) {
            this.moveDown();
        }
    }

        /**
         * Moves the AI player randomly.
         * This method is called when no players are found or when the AI cannot move towards a player.
         */
    private void moveRandomly() {
            int initialX = this.getMapX();
            int initialY = this.getMapY();
            Runnable[] moves = new Runnable[] {
                this::moveRight,
                this::moveLeft,
                this::moveUp,
                this::moveDown
            };
            java.util.Collections.shuffle(java.util.Arrays.asList(moves));
            for (Runnable move : moves) {
                move.run();
                if (this.getMapX() != initialX || this.getMapY() != initialY) {
                    break;
                }
            }
        }




}
