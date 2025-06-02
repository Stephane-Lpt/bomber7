package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Constants;
import com.bomber7.utils.ScreenType;

/**
 * Screen where the user selects the map
 * Here he can define the number of players, their skins, as well as their type (human or AI)
 * as well as the difficulty of the AI.
 */
public class MapSelectionScreen extends BomberScreen {
    /**
     * Button to return to the previous screen (character selection).
     */
    private TextButton goToPlayerSelectionButton;

    /**
     * Button to continue and go to the map selection screen.
     */
    private TextButton startGameButton;

    /**
     * Button used to decrease the number of round in the game.
     */
    private TextButton decreaseNumberOfRounds;

    /**
     * Button used to increase the number of round in the game.
     */
    private TextButton increaseNumberOfRounds;

    /**
     * The currently configured number of rounds in the game.
     */
    private int rounds = Constants.MIN_ROUNDS_NUMBER;

    /**
     * Label that shows the configured number of rounds.
     */
    private Label roundsNumberLabel;

    /**
     * Constructs a new LevelSetupScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public MapSelectionScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() {
        final int cols = 1;
        BomberTable table = new BomberTable();
        table.setFillParent(true);
        table.setDebug(true);

        Label numberOfRoundsLabel = new Label(resources.getString("number_of_rounds"), resources.getSkin());
        roundsNumberLabel = new Label(String.valueOf(rounds), resources.getSkin());

        goToPlayerSelectionButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        startGameButton = new TextButton(resources.getString("start_game"), resources.getSkin());
        decreaseNumberOfRounds = new TextButton("<", resources.getSkin(), "transparent-sm");
        increaseNumberOfRounds = new TextButton(">", resources.getSkin(), "transparent-sm");

        table.setTitle(new Label(resources.getString("map_selection"), resources.getSkin()), cols);

        table.add(numberOfRoundsLabel);
        table.add(decreaseNumberOfRounds);
        table.add(roundsNumberLabel);
        table.add(increaseNumberOfRounds)
            .row();

        table.setupDoubleButtons(goToPlayerSelectionButton, startGameButton, cols);

        super.addActor(table);
    }

    @Override
    public void initController() {
        decreaseNumberOfRounds.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rounds--;
            }
        }, resources));

        increaseNumberOfRounds.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rounds++;
            }
        }, resources));

        goToPlayerSelectionButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showPreviousScreen();
            }
        }, resources));

        startGameButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.start();
            }
        }, resources));
    }
}
