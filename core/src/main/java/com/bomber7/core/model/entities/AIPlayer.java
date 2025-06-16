package com.bomber7.core.model.entities;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.utils.GameCharacter;

/**
 * AI player class.
*/
public class AIPlayer extends Player {

    /**
     * AIPlayer Constructor.
     * @param config        The configuration for the player controls
     * @param map           The map.
     * @param name          The name of the player
     * @param mapX          The x-coordinate of the player
     * @param mapY          The y-coordinate of the player
     * @param gameCharacter The game character type
     */
    public AIPlayer(LevelMap map, int mapX, int mapY, GameCharacter gameCharacter) {
        super("AI", map, mapX, mapY, 1, 1, gameCharacter);
    }

    /**
     * AI BOT movement logic.
     * The AI player is always moving towards the nearest player.
     * If no player is found, it will move randomly.
     * @param players The list of players in the game.
     * @param map The current level map.
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
        boolean moved = false;
        if (this.getMapX() < playerX && checkMove(this.getMapX() + this.getSpeed(), this.getMapY())) {
            this.moveRight();
            moved = true;
        } else if (this.getMapX() > playerX && checkMove(this.getMapX() - this.getSpeed(), this.getMapY())) {
            this.moveLeft();
            moved = true;
        }
        if (this.getMapY() < playerY && checkMove(this.getMapX(), this.getMapY() + this.getSpeed())) {
            this.moveUp();
            moved = true;
        } else if (this.getMapY() > playerY && checkMove(this.getMapX(), this.getMapY() - this.getSpeed())) {
            this.moveDown();
            moved = true;
        }
        if (!moved) {
            moveRandomly();
        }
    }

    /**
     * Moves the AI player randomly.
     * This method is called when no players are found or when the AI cannot move towards a player.
     */
    private void moveRandomly() {
        int direction = (int) (Math.random() * 4);
        switch (direction) {
            case 0:
                if (checkMove(this.getMapX() + this.getSpeed(), this.getMapY())) {
                    this.moveRight();
                }
                break;
            case 1:
                if (checkMove(this.getMapX() - this.getSpeed(), this.getMapY())) {
                    this.moveLeft();
                }
                break;
            case 2:
                if (checkMove(this.getMapX(), this.getMapY() + this.getSpeed())) {
                    this.moveUp();
                }
                break;
            case 3:
                if (checkMove(this.getMapX(), this.getMapY() - this.getSpeed())) {
                    this.moveDown();
                }
                break;
        }
    }

}
