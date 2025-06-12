package com.bomber7.core.screens;


import com.badlogic.gdx.Gdx;
import com.bomber7.core.ConfigManager;
import com.bomber7.core.model.entities.HumanPlayer;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;

import java.nio.file.Path;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.bomber7.utils.GameCharacter;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.views.ViewCharacter;
import com.bomber7.core.views.ViewMap;
import com.bomber7.utils.ProjectPaths;

/**
 * Represents the game screen in the Bomber7 game.
 * This screen displays the game map
 */
public class GameScreen extends BomberScreen {
    /**
     * Constructs a new GameScreen associated with the given game.
     * @param game the Game instance this screen belongs to
     */
    public GameScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    /**
     * Game screen initialization.
     */
    @Override
    public void initView() {

        /* =======[GENERAL PURPOSES]=============================================== */

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        /* =======[MAP VIEW]=============================================== */

        /* Path to the tileset JSON file. */
        Path tilesetJsonPath = ProjectPaths.getTileset();
        /* Map name for the current game. */
        String mapName = "foy";
        /* Create a LevelMapFactory to load the map. */

        LevelMap levelMap = LevelMapFactory.createLevelMap(mapName, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        ArrayList<ViewCharacter> characters = new ArrayList<>();

        characters.add(
            new ViewCharacter(
                new HumanPlayer(
                    ConfigManager.getInstance().getConfig().getPlayerConfig(1),
                    levelMap,
                    "test",
                    0,
                    0,
                    GameCharacter.TEST
                ),
                resources
            )
        );

        /* Map view of the game. */
        ViewMap viewMap = new ViewMap(levelMap, resources, characters);

        /* =======[FULL FRAME]=============================================== */

        mainTable.add(viewMap);
        this.addActor(mainTable);
    }

    /**
     * Game controller initialization.
     */
    @Override
    public void initController() {

    }

    /**
     * Update the game screen for each frame.
     * @param delta time since the last frame
     */
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GAME;
    }
}
