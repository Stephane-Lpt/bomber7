package com.bomber7.core.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.bomber7.core.controller.HumanController;
import com.bomber7.core.model.entities.Character;
import com.bomber7.core.views.ViewCharacter;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.views.ViewMap;

import java.util.ArrayList;
import java.util.List;


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
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        List<ViewCharacter> characterViews = new ArrayList<>();

        for(Character character : game.getCurrentMap().getCharacters()) {
                ViewCharacter characterView = new ViewCharacter(
                    character,
                    resources
                );
                characterViews.add(characterView);
        }

        ViewMap viewMap = new ViewMap(game.getCurrentMap(), characterViews, resources);

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
        processInput();

//        game.logCurrentRound();
//        Gdx.app.debug("GameScreen", "Current map: " + game.getCurrentMap().getMapName());

        if (!game.isRoundCompleted()) {
//            game.simulateEndOfRound();

            if (game.checkGameWin()) {
                Gdx.app.debug("GameScreen", "Round completed. Advancing to next round...");
                game.setRoundCompleted(true);
                game.advanceToNextRound();
             }
        } else {
            Gdx.app.debug("GameScreen", "Round already completed. Waiting for next round.");
        }

        super.render(delta);
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GAME;
    }

    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.pause();
        }

        for(HumanController humanController : game.getHumanControllers()) {
            humanController.processKeys();
        }
    }
}
