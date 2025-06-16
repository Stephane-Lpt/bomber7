package com.bomber7.core;

import com.badlogic.gdx.Game;
import com.bomber7.core.controller.HumanController;
import com.bomber7.core.model.GameCandidate;
import com.bomber7.core.model.entities.Character;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.utils.Constants;
import com.bomber7.utils.GameCharacter;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.bomber7.utils.SoundManager;

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
     * Current level map.
     */
    private LevelMap levelMap;

    /**
     * List of players.
     */
    private Character[] characters;

    /**
     * List of human player controllers.
     */
    private HumanController[] humanControllers;

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

        characters = new Character[Constants.MAX_PLAYERS];
        humanControllers = new HumanController[Constants.MAX_PLAYERS];

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
     * Returns the gameCandidate used to create the game.
     * @return the game candidate instance
     */
    public GameCandidate getCandidate() {
        return gameCandidate;
    }

    /**
     * Launches the game.
     */
    public void start() {
        // Printing gameCandidate for debug
        Gdx.app.debug("BomberGame", "GameCandidate: " + gameCandidate.toString());
        Gdx.app.debug("BomberGame", "Started game");

        levelMap = LevelMapFactory.createLevelMap(gameCandidate.getMaps().get(0).getAssetName(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        HumanPlayer player0 = new HumanPlayer(
            ConfigManager.getInstance().getConfig().getPlayerConfig(0),
            levelMap,
            "test",
            1,
            2,
            GameCharacter.TEST
        );
        HumanPlayer player1 = new HumanPlayer(
            ConfigManager.getInstance().getConfig().getPlayerConfig(1),
            levelMap,
            "test",
            1,
            2,
            GameCharacter.TEST
        );
        HumanPlayer player2 = new HumanPlayer(
            ConfigManager.getInstance().getConfig().getPlayerConfig(2),
            levelMap,
            "test",
            1,
            2,
            GameCharacter.TEST
        );
        HumanPlayer player3 = new HumanPlayer(
            ConfigManager.getInstance().getConfig().getPlayerConfig(3),
            levelMap,
            "test",
            1,
            2,
            GameCharacter.TEST
        );

        HumanController controller0 = new HumanController(player0);
        HumanController controller1 = new HumanController(player1);
        HumanController controller2 = new HumanController(player2);
        HumanController controller3 = new HumanController(player3);

        characters[0] = player0;
        characters[1] = player1;
        characters[2] = player2;
        characters[3] = player3;

        humanControllers[0] = controller0;
        humanControllers[1] = controller1;
        humanControllers[2] = controller2;
        humanControllers[3] = controller3;

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
        ScreenManager.getInstance().showScreen(ScreenType.PAUSE, true, true);
    }

    /**
     * Stops the game.
     */
    public void stop() {
        gameCandidate.reset();
        ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
    }

    /**
     * Returns the levelMap.
     */
    public LevelMap getLevelMap() {
        return levelMap;
    }

    /**
     * Returns the players currently present in the game.
     */
    public Character[] getCharacters() {
        return characters;
    }

    /**
     * Returns the players currently present in the game.
     */
    public HumanController[] getHumanControllers() {
        return humanControllers;
    }
}
