package com.bomber7.core.screens;


import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.BomberGame;

import java.nio.file.Path;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.views.ViewMap;
import com.bomber7.utils.Dimensions;

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

        /* =======[BUTTON]========================================================= */

        /* Buttons to go to key bindings menu. */
        TextButton settingsButton = new TextButton(resources.getString("options"), resources.getSkin());
        /* Buttons to go to key bindings menu. */
        TextButton goBackButton = new TextButton(resources.getString("go_back"), resources.getSkin());

        mainTable.add(settingsButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .left();
        mainTable.row();
        mainTable.add(goBackButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT);
        mainTable.row();

        /* =======[MAP VIEW]======================================================= */

        ViewMap viewMap = new ViewMap(game.getCurrentMap(), resources);

        /* =======[FULL FRAME]===================================================== */

        mainTable.add(viewMap);
        this.addActor(mainTable);
    }

    /**
     * Game controller initialization.
     */
    @Override
    public void initController() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.logCurrentRound();

        //display current map name
        Gdx.app.debug("GameScreen", "Current map: " + game.getCurrentMap().getMapName());
        
        if (!game.isRoundCompleted()) {
            game.simulateEndOfRound();

            if (game.checkGameWin()) {
                Gdx.app.debug("GameScreen", "Round completed. Advancing to next round...");
                game.setRoundCompleted(true);
                game.advanceToNextRound();
            }
        } else {
            Gdx.app.debug("GameScreen", "Round already completed. Waiting for next round.");
        }
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GAME;
    }
}
