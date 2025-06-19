package com.bomber7.core.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.bomber7.core.components.PlayerScoreBoard;
import com.bomber7.core.controller.HumanController;
import com.bomber7.core.model.entities.Character;
import com.bomber7.core.model.entities.Player;
import com.bomber7.core.views.ViewCharacter;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.views.ViewMap;
import com.bomber7.utils.SoundManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the game screen in the Bomber7 game.
 * This screen displays the game map
 */
public class GameScreen extends BomberScreen {

    /**
     * List of scoreboards for each player in the game.
     */
    private List<PlayerScoreBoard> scoreboards;

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
//        mainTable.setDebug(true);

        List<ViewCharacter> characterViews = new ArrayList<>();
        scoreboards = new ArrayList<>();

        for (Character character : game.getCurrentMap().getCharacters()) {
                ViewCharacter characterView = new ViewCharacter(
                    character,
                    resources
                );
                characterViews.add(characterView);
        }

        ViewMap viewMap = new ViewMap(game.getCurrentMap(), characterViews, resources);

        mainTable.add(viewMap)
            .expandX()
            .expandY();

        Table scoreTable = new Table();

        for (Character character : game.getCurrentMap().getCharacters()) {
            Player player = (Player) character;
            PlayerScoreBoard scoreBoard = new PlayerScoreBoard(player);
            scoreboards.add(scoreBoard);
            scoreTable.add(scoreBoard)
                .right()
                .spaceBottom(Dimensions.COMPONENT_SPACING_SM)
                .row();
        }

        mainTable.add(scoreTable)
            .right()
            .padRight(Dimensions.COMPONENT_SPACING_LG);

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

        for (PlayerScoreBoard scoreBoard : scoreboards) {
            scoreBoard.refresh();
        }

        game.update();

        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
        SoundManager.getInstance().playFightMusic();
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GAME;
    }

    /**
     * Processes user input for the game screen.
     * This method checks for key presses and updates the game state accordingly.
     */
    public void processInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.pause();
        }

        for (HumanController humanController : game.getHumanControllers()) {
            humanController.processKeys();
        }
    }
}
