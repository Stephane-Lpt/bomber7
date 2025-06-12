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

/**
 * Main class of the Bomber7 game, extending LibGDX's {@link com.badlogic.gdx.Game} class.
 * <p>
 * Manages game lifecycle and resources.
 * </p>
 */
public class BomberGame extends Game {
    /**
     * Resource manager responsible for loading and managing game assets.
     */
    private ResourceManager resources;

    /**
     * GameCandidate that holds the players, maps and rounds configured {@link com.bomber7.core.screens.PlayerSelectionScreen} and
     * {@link com.bomber7.core.screens.MapSelectionScreen}.
     * When a game is started, the characters as well as the map is initialized used this object.
     */
    private GameCandidate gameCandidate;

    private ArrayList<LevelMap> mapList;

    /**
     * Called once when the application is created.
     * Initializes the resource manager and sets the initial screen to the main menu.
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        ScreenManager.getInstance().initialize(this);
        ConfigManager.getInstance().initialize();
        resources = new ResourceManager();
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
        resources.dispose();
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
     * Returns the {@link ResourceManager} instance used by this game.
     *
     * @return the resource manager for game assets
     */
    public ResourceManager getBomberResources() {
        return resources;
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
        // Printing gameCandidate for debug
        Gdx.app.debug("BomberGame", "GameCandidate: " + gameCandidate.toString());

        // Check if gameCandidate is properly initialized
        if (gameCandidate.getPlayerBlueprints() == null) {
            Gdx.app.debug("BomberGame", "GameCandidate is missing player configurations.");
        }

        if (gameCandidate.getMaps() == null || gameCandidate.getMaps().isEmpty()) {
            Gdx.app.debug("BomberGame", "GameCandidate is missing map configurations.");
        }

        if (gameCandidate.getRounds() < Constants.MIN_ROUNDS) {
            Gdx.app.debug("BomberGame", "GameCandidate has an invalid number of rounds.");
        }

        // Initialize the LevelMap list for each map in the gameCandidate
        for (GameMap maps: gameCandidate.getMaps()) {
            if (maps.getAssetName() != null && !maps.getAssetName().isEmpty()) {
                /* Create a LevelMapFactory to load the map. */
                LevelMap levelMap = LevelMapFactory.createLevelMap(maps.getAssetName(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                mapList.add(levelMap);
            }
        }
        
        // Initialize characters based on the gameCandidate
        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
           if (gameCandidate.getPlayerBlueprints()[i] != null){
                SpawnPoints spawnPointPlayer = SpawnPoints.values()[i];
                if (gameCandidate.getPlayerBlueprints()[i].getStrategy() == PlayerStrategy.HUMAN) {
                    HumanPlayer humanPlayer = new HumanPlayer(
                        ConfigManager.getInstance().getConfig().getPlayerConfig(i),
                        mapList.get(0),
                        gameCandidate.getPlayerBlueprints()[i].getName(),
                        spawnPointPlayer.getX(),
                        spawnPointPlayer.getY(),
                        gameCandidate.getPlayerBlueprints()[i].getCharacter()
                        );
                        mapList.get(0).addCharacter(humanPlayer);
                } else {
                }
           }

        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
        }
    }

    /**
     * Verify status of the game : WIN, player dead, etc...
     * Precondition : the gameCandidate must be initialized with players and maps.
     */
    public void checkGameWin() {
        
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
