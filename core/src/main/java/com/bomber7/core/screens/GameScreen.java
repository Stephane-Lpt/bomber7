package com.bomber7.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.bomber7.core.model.entities.HumanPlayer;
import com.badlogic.gdx.Game;
import com.bomber7.core.views.ViewCharacter;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Controls;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.views.ViewMap;
import jdk.vm.ci.meta.Constant;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents the game screen in the Bomber7 game.
 * This screen displays the game map
 */
public class GameScreen extends BomberScreen {


    ViewCharacter[] characterViews;

    /**
     * Constructs a new GameScreen associated with the given game.
     * @param game the Game instance this screen belongs to
     */
    public GameScreen(Game game) {
        super(game);

        characterViews = new ViewCharacter[Constants.MAX_PLAYERS];

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

        for(int i = 0; i < Constants.MAX_PLAYERS; i++) {
            ViewCharacter characterView = new ViewCharacter(
                game.getCharacters()[i],
                resources
            );
            characterViews[i] = characterView;
        }

        /* Map view of the game. */
        ViewMap viewMap = new ViewMap(game.getLevelMap(), resources, Arrays.asList(characterViews));

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

        for(int i = 0; i < Constants.MAX_PLAYERS; i++) {
            game.getHumanControllers()[i].processKeys();
        }
    }
}
