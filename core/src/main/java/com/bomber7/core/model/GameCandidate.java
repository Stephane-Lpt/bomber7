package com.bomber7.core.model;

import com.bomber7.utils.Constants;
import com.bomber7.utils.GameMap;
import com.bomber7.utils.PlayerBlueprint;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class holds the state of the game to create.
 * When a user selects players / maps / rounds in {@link com.bomber7.core.screens.PlayerSelectionScreen}
 * and {@link com.bomber7.core.screens.MapSelectionScreen}, the selected parameters are added to this class.
 * Later on, when the game is created, the application should look for the attributes of this class to know the
 * configuration of the game it should start.
 */
public class GameCandidate {

    /**
     * The playerBlueprint configured by the user, which are going to be used
     * to instanciate the real characters.
     */
    private PlayerBlueprint[] playerBlueprints;

    /**
     * The maps that the game should be played on.
     */
    private final ArrayList<GameMap> maps;

    /**
     * The number of rounds the game will last.
     */
    private int rounds;

    /**
     * A constructor that creates an empty GameCandidate.
     * Should be called when launching the application.
     */
    public GameCandidate() {
        playerBlueprints = new PlayerBlueprint[Constants.MAX_PLAYERS];
        maps = new ArrayList<>();
        rounds = Constants.MIN_ROUNDS;
    }

    /**
     * Adds a player blueprint to the list of playerBlueprints to a given position.
     * @param playerBlueprint the playerBlueprint to add to the list
     * @param playerIndex the position where the playerBlueprint should be added to (it is the number of the player)
     */
    public void addPlayer(PlayerBlueprint playerBlueprint, int playerIndex) {
        playerBlueprints[playerIndex] = playerBlueprint;
    }

    /**
     * Returns the player blueprints as an array of {@code PlayerBlueprint}.
     * @return the player blueprints
     */
    public PlayerBlueprint[] getPlayerBlueprints() {
        return playerBlueprints;
    }

    /**
     * Returns the selected maps as a list of {@code GameMap}.
     * @return the maps list
     */
    public ArrayList<GameMap> getMaps() {
        return maps;
    }

    /**
     * Adds a map to the list of selected maps.
     * @param map the map to add to the list
     */
    public void addMap(GameMap map) {
        maps.add(map);
    }

    /**
     * Returns the selected number of rounds.
     * @return the number of rounds a game will last
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Setter for rounds.
     * @param rounds the number of rounds to set.
     */
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
     * Resets the game candidate to its "empty" state.
     * Should be called when the player / configuration screen was cancelled, or when the game is ended.
     * Should not be called while the game is running.
     */
    public void reset() {
        maps.clear();
        playerBlueprints = new PlayerBlueprint[Constants.MAX_PLAYERS];
        rounds = Constants.MIN_ROUNDS;
    }

    @Override
    public String toString() {
        return Arrays.toString(playerBlueprints) + ", " + maps +  ", rounds: " + rounds;
    }
}
