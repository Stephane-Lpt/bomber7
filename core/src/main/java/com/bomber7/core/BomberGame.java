package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.GridPoint2;
import com.bomber7.core.controller.HumanController;
import com.bomber7.core.model.GameCandidate;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.utils.Constants;
import com.bomber7.utils.GameMap;
import com.bomber7.utils.PlayerStrategy;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.utils.Score;
import com.bomber7.utils.ScreenType;
import com.bomber7.utils.SpawnPoint;
import com.bomber7.core.model.entities.Character;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.bomber7.utils.SoundManager;
import com.bomber7.utils.SoundType;

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
     * Current map being played in the game.
     */
    private LevelMap currentMap;

    /**
     * Current round number in the game.
     * Used to track the progress of the game across multiple rounds.
     */
    private int currentRound;

    /**
     * A boolean that indicates whether a game is started or not.
     */
    private boolean gameStarted;

    /**
     * List of characters present in the game.
     * <p>
     * Once a game is started, this list stores all the characters in the current.
     * At the beginning of each round the list of characters of the current map is populated with these characters.
     * At the end of each round these characters are being reset to their initial state (lives, bombs, position)
     * all while keeping their score and humanController binds.
     * </p>
     */
    private ArrayList<Character> characters;

    /**
     * List of human player controllers.
     */
    private List<HumanController> humanControllers;

    /**
     * Time elapsed since the last time players were credited points for being alive during a game.
     * TODO: there may be a bug when this timer works incorrectly when the game is being paused (scoreboard or pause menu).
     */
    private float aliveScoreTimer;

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
        humanControllers = new ArrayList<>();
        characters = new ArrayList<>();

        reset();

        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }

    /**
     * Launches the game.
     * Precondition : the gameCandidate must be initialized with players and maps.
     */
    public void start() {
        gameStarted = true;

        // Printing gameCandidate for debug
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

        if (gameCandidate.getMaps().isEmpty()) {
            Gdx.app.error("BomberGame", "No valid maps found in GameCandidate.");
            return;
        }

        // Start the first round
        nextRound();

        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
    }

    /**
     * Resumes the game after being paused.
     */
    public void resume() {
        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        ScreenManager.getInstance().showScreen(ScreenType.PAUSE, false, false   );
    }

    /**
     * Stops the game.
     */
    public void stop() {
        gameStarted = false;
        reset();
        SoundManager.getInstance().resetFightMusic();
        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }

    /**
     * Function that is being called every render() of GameScreen to update the game.
     * Not entirely respecting the MVC pattern but we had to do it this way because it's the only
     * way we can get the game updating constantly on every tick of gameScreen
     */
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        aliveScoreTimer += delta;

        // Crediting all alive players with score points for being alive.
        if (aliveScoreTimer >= Constants.ALIVE_SCORE_TIMER) {
            aliveScoreTimer = 0f;

            for (Character character : currentMap.getCharacters()) {
                if (character.isAlive()) {
                    character.addScore(Score.ALIVE);
                }
            }
        }

        if (checkGameWin()) {
            ScreenManager.getInstance().showScreen(ScreenType.SCOREBOARD, false, false);
        }
    }

    /**
     * Initializes characters before the first round.
     * <p>
     *     This cannot be done in the start() method because a character needs a valid levelMap to be created.
     *     The solution is to call it the first time nextRound() is called in a game.
     * </p>
     */
    private void initializeCharacters() {
        Gdx.app.debug("BomberGame", "Initializing all characters");
        // Initializing characters
        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            if (gameCandidate.getPlayerBlueprints()[i] != null) {
                GridPoint2 spawnPoint = SpawnPoint.getForPlayer(i);

                if (gameCandidate.getPlayerBlueprints()[i].getStrategy() == PlayerStrategy.HUMAN) {
                    HumanPlayer player = new HumanPlayer(
                        ConfigManager.getInstance().getConfig().getPlayerConfig(i),
                        currentMap,
                        gameCandidate.getPlayerBlueprints()[i].getName(),
                        spawnPoint.x,
                        spawnPoint.y,
                        gameCandidate.getPlayerBlueprints()[i].getCharacter()
                    );
                    characters.add(player);
                }
                // Add AIPlayer instantiation here if needed
            }
        }

        // Initializing human controllers
        for (Character character : characters) {
            if (character instanceof HumanPlayer) {
                humanControllers.add(new HumanController((HumanPlayer) character));
            }
        }
    }

    /**
     * Verify status of the game : WIN, player dead, etc...
     * Precondition : the gameCandidate must be initialized with players and maps.
     * @return true if the game is won, false otherwise
     */
    private boolean checkGameWin() {
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
                    Gdx.app.debug("BomberGame", character.getName() + " wins the round!");
                    character.addScore(Score.WIN);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Starts a new round by setting the currentMap to the nextMap and by resetting all the characters.
     * Precondition: the game is not finished
     */
    public void nextRound() {
        if(isLastRound()) {
            Gdx.app.debug("BomberGame", "Last round was played. Stopping the game.");
            stop();
        }

        currentRound++;

        Gdx.app.debug("BomberGame", "Round " + currentRound);
        currentMap = getNextMap();

        // Initializing character in case if it is the first round
        if (currentRound == 1) {
            initializeCharacters();
        }

        // Resetting players & adding them to the new map
        for (Character character : characters) {
            character.reset();
            character.setMap(currentMap);
            currentMap.addCharacter(character);
        }

        ScreenManager.getInstance().showScreen(ScreenType.GAME, false, false);
    }

    /**
     * Indicates whether the current round is the last one.
     * @return true if it is, false otherwise
     */
    public boolean isLastRound() {
        return currentRound == gameCandidate.getRounds();
    }

    /**
     * Simulates the end of a round by marking all players except one as dead.
     * This is useful for testing round transitions and map changes.
     */
    private void simulateEndOfRound() {
        if (currentMap == null) {
            Gdx.app.error("BomberGame", "No current map set. Cannot simulate end of round.");
            return;
        }

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
    }

    /**
     * Returns the current map.
     * @return the current levelMap
     */
    public LevelMap getCurrentMap() {
        return currentMap;
    }

    /**
     * Returns the next map based on the currentRound (that has already been incremented before calling it).
     * @return the levelMap for the next round
     */
    private LevelMap getNextMap() {
        GameMap mapToCreate = gameCandidate.getMaps().get((currentRound - 1) % gameCandidate.getMaps().size());
        LevelMap levelMap = LevelMapFactory.createLevelMap(mapToCreate.getAssetName(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return levelMap;
    }

    /**
     * Returns the human player controllers.
     * @return the list of humanControllers.
     */
    public List<HumanController> getHumanControllers() {
        return humanControllers;
    }

    /**
     * Returns the gameCandidate used to create the game.
     * @return the game candidate instance
     */
    public GameCandidate getCandidate() {
        return gameCandidate;
    }

    /**
     * Resets the game to its initial conditions.
     */
    private void reset() {
        gameCandidate.reset();
        characters.clear();
        currentRound = 0;
        currentMap = null;
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
}
