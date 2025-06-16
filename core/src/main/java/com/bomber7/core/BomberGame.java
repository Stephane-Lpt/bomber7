package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.core.model.GameCandidate;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.utils.Constants;
import com.bomber7.utils.GameCharacter;
import com.bomber7.utils.GameMap;
import com.bomber7.utils.PlayerBlueprint;
import com.bomber7.utils.PlayerStrategy;
import com.bomber7.utils.ProjectPaths;
import com.bomber7.utils.ScreenType;
import com.bomber7.utils.SpawnPoints;
import com.bomber7.core.model.entities.Character;

import java.lang.reflect.Array;
import java.nio.file.Path;
import com.bomber7.utils.ProjectPaths; 
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.bomber7.utils.SoundManager;
import com.badlogic.gdx.Screen;

/**
 * Main class of the Bomber7 game, extending LibGDX's {@link com.badlogic.gdx.Game} class.
 * <p>
 * Manages game lifecycle and resources.
 * </p>
 */
public class BomberGame extends Game {
    /**
     * GameCandidate that holds the players, maps and rounds configured {@link com.bomber7.core.screens.PlayerSelectionScreen} and
     * {@link com.bomber7.core.screens.MapSelectionScreen}.
     * When a game is started, the characters as well as the map is initialized used this object.
     */
    private GameCandidate gameCandidate;

    /**
     * List of LevelMaps selected by the user
     */
    private ArrayList<LevelMap> mapList;

    /**
     * Current map being played in the game.
     */
    private LevelMap currentMap;

    /**
     * Current round number in the game.
     * Used to track the progress of the game across multiple rounds.
     */
    private int currentRound = 0;

    private boolean roundCompleted = false; // Tracks whether the current round is completed

    private List<Character> players = new ArrayList<>();

    /**
     * Called once when the application is created.
     * Initializes the resource manager and sets the initial screen to the main menu.
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        ScreenManager.getInstance().initialize(this);
        ConfigManager.getInstance().initialize();
        ResourceManager.getInstance().initialize();
        SoundManager.getInstance().initialize();
        gameCandidate = new GameCandidate();
        mapList = new ArrayList<>();

        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }

    /**
     * Called when the application is closed.
     * Disposes of game resources to free up memory.
     */
    @Override
    public void dispose() {
        ResourceManager.getInstance().dispose();
        SoundManager.getInstance().dispose();
        super.dispose();
    }

    /**
     * Returns a LevelMap from the mapList at the specified index.
     * @param i
     * @return the LevelMap at the specified index
     */
    public LevelMap getMapListElement(int i) {
        return mapList.get(i);
    }

    /**
     * Returns the gameCandidate used to create the game.
     * @return the game candidate instance
     */
    public GameCandidate getCandidate() {
        return gameCandidate;
    }

    /**
     * Launches the game.
     * Precondition : the gameCandidate must be initialized with players and maps.
     */
    public void start() {
        Gdx.app.debug("BomberGame", "GameCandidate: " + gameCandidate.toString());

        // Check if gameCandidate is properly initialized
        if (gameCandidate.getPlayerBlueprints() == null) {
            Gdx.app.error("BomberGame", "GameCandidate is missing player configurations.");
            return;
        }

        if (gameCandidate.getMaps() == null || gameCandidate.getMaps().isEmpty()) {
            Gdx.app.error("BomberGame", "GameCandidate is missing map configurations.");
            return;
        }

        if (gameCandidate.getRounds() < Constants.MIN_ROUNDS) {
            Gdx.app.error("BomberGame", "GameCandidate has an invalid number of rounds.");
            return;
        }

        // Initialize the LevelMap list for each map in the gameCandidate
        mapList.clear(); // Ensure the list is empty before populating
        for (GameMap maps : gameCandidate.getMaps()) {
            if (maps.getAssetName() != null && !maps.getAssetName().isEmpty()) {
                LevelMap levelMap = LevelMapFactory.createLevelMap(maps.getAssetName(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                mapList.add(levelMap);
            }
        }

        if (mapList.isEmpty()) {
            Gdx.app.error("BomberGame", "No valid maps found in GameCandidate.");
            return;
        }

        // Start the first round
        currentRound = 1;
        roundCompleted = false;
        gameRound(mapList.get((currentRound - 1) % mapList.size()));
    }

    /**
     * Verify status of the game : WIN, player dead, etc...
     * Precondition : the gameCandidate must be initialized with players and maps.
     * @return true if the game is won, false otherwise
     */
    public boolean checkGameWin() {
        if (currentMap == null) {
            Gdx.app.error("BomberGame", "No current map set. Cannot check game win conditions.");
            return false;
        }

        List<Character> characters = currentMap.getCharacters();
        int aliveCount = 0;

        for (Character character : characters) {
            if (character.isAlive()) {
                aliveCount++;
            }
        }

        if (aliveCount == 1) {
            for (Character character : characters) {
                if (character.isAlive()) {
                    Gdx.app.debug("BomberGame", "Player " + character.getName() + " wins the round!");
                    return true;
                }
            }
        }

        return false;
    }

public void gameRound(LevelMap levelMap) {
    currentMap = levelMap;
    currentMap.getCharacters().clear(); // Remove any old references

    if (players.isEmpty()) {
        // First round: instantiate players and add to both players list and current map
        System.out.println("1st INIT");
        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            if (gameCandidate.getPlayerBlueprints()[i] != null) {
                SpawnPoints spawnPointPlayer = SpawnPoints.values()[i];
                if (gameCandidate.getPlayerBlueprints()[i].getStrategy() == PlayerStrategy.HUMAN) {
                    HumanPlayer humanPlayer = new HumanPlayer(
                        ConfigManager.getInstance().getConfig().getPlayerConfig(i),
                        currentMap,
                        gameCandidate.getPlayerBlueprints()[i].getName(),
                        spawnPointPlayer.getX(),
                        spawnPointPlayer.getY(),
                        gameCandidate.getPlayerBlueprints()[i].getCharacter()
                    );
                    players.add(humanPlayer);
                    currentMap.addCharacter(humanPlayer);
                }
                // Add AIPlayer instantiation here if needed
            }
        }
    } else {
        // Not the first round: reset all players and add them to the new map
        for (int i = 0; i < players.size(); i++) {
            Character character = players.get(i);
            SpawnPoints spawnPointPlayer = SpawnPoints.values()[i];
            resetPlayer(character, spawnPointPlayer);
            System.out.println("RESET PLAYER: " + character.getName() + " at " + spawnPointPlayer);
            character.setMap(currentMap); // Make sure the player references the new map
            System.out.println("ADD PLAYER: " + character.getName() + " to map " + currentMap.getMapName());
            currentMap.addCharacter(character);
        }
    }

    ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
}

public void advanceToNextRound() {
    Gdx.app.debug("BomberGame", "Advance to next round called. Current round: " + currentRound + ", Total rounds: " + gameCandidate.getRounds());

    if (currentRound < gameCandidate.getRounds()) {
        currentRound++;
        roundCompleted = false;
        
        // Get next map (cycle through maps)
        LevelMap nextMap = mapList.get((currentRound - 1) % mapList.size());
        gameRound(nextMap);
    } else {
        Gdx.app.debug("BomberGame", "All rounds completed. Returning to player setup screen.");
        ScreenManager.getInstance().showScreen(ScreenType.PLAYER_SETUP, false, false);
    }
}

public LevelMap getCurrentMap() {
    return currentMap;
}

    /**
     * Simulates the end of a round by marking all players except one as dead.
     * This is useful for testing round transitions and map changes.
     */
    public void simulateEndOfRound() {
        if (currentMap == null) {
            Gdx.app.error("BomberGame", "No current map set. Cannot simulate end of round.");
            return;
        }

        List<Character> characters = currentMap.getCharacters();
        int aliveCount = 0;

        // Find the first alive player to keep alive
        Character survivor = null;
        for (Character character : characters) {
            if (character.isAlive()) {
                if (survivor == null) {
                    survivor = character;
                } else {
                    // Kill all other players
                    character.removeOneLife(); // Mark as dead
                }
            }
        }

        // Now check if only one is alive
        aliveCount = 0;
        for (Character character : characters) {
            if (character.isAlive()) {
                aliveCount++;
            }
        }

        if (aliveCount == 1 && survivor != null) {
            Gdx.app.debug("BomberGame", "Player " + survivor.getName() + " wins the round!");
            roundCompleted = true; // Mark the round as completed
        } else {
            Gdx.app.debug("BomberGame", "More than one player alive. Round not completed.");
        }
    }

    /**
     * Logs the current round number to the debug output.
     */
    public void logCurrentRound() {
        Gdx.app.debug("BomberGame", "Current Round: " + currentRound);
    }

    public void startRound() {
        gameRound(mapList.get((currentRound - 1) % mapList.size())); // Start the round with the correct map

        List<Character> characters = currentMap.getCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i) instanceof HumanPlayer) {
                HumanPlayer humanPlayer = (HumanPlayer) characters.get(i);
                SpawnPoints spawnPointPlayer = SpawnPoints.values()[i]; // Get the spawn point for the player
                resetPlayer(humanPlayer, spawnPointPlayer); // Reset the player's position and state
            }
        }
    }

    private void resetPlayer(Character player, SpawnPoints spawnPointPlayer) {
        player.setPositionX(spawnPointPlayer.getX());
        player.setPositionY(spawnPointPlayer.getY());
        if (!player.isAlive()) {
            player.ressucitate();
        }
    }

    public boolean isRoundCompleted() {
        return roundCompleted;
    }

    public void setRoundCompleted(boolean roundCompleted) {
        this.roundCompleted = roundCompleted;
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        ScreenManager.getInstance().showScreen(ScreenType.PAUSE, true, true);
    }

    /**
     * Stops the game.
     */
    public void stop() {
        gameCandidate.reset();
        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }
}
