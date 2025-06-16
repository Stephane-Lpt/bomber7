package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.core.components.DisableableTextButton;
import com.bomber7.core.components.MapCheckBox;
import com.bomber7.core.model.Observer;
import com.bomber7.core.model.Subject;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.GameMap;
import com.bomber7.utils.ScreenType;

/**
 * Screen where the user selects the map
 * Here he can define the number of players, their skins, as well as their type (human or AI)
 * as well as the difficulty of the AI.
 */
public class MapSelectionScreen extends BomberScreen implements Observer {
    /**
     * Button to return to the previous screen (character selection).
     */
    private TextButton goToPlayerSelectionButton;

    /**
     * Button to continue and go to the map selection screen.
     */
    private DisableableTextButton startGameButton;

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
    private int rounds = Constants.MIN_ROUNDS;

    /**
     * Label that shows the configured number of rounds.
     */
    private Label roundsNumberLabel;
    /**
     * List of map checkboxes.
     */
    private MapCheckBox[] mapCheckboxes;
    /**
     * The number of currently checked checkboxes.
     */
    private int checkedCount;

    /**
     * Constructs a new LevelSetupScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public MapSelectionScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    @Override
    public void initView() {
        final int cols = 4;
        BomberTable table = new BomberTable();
        table.setFillParent(true);
//        table.setDebug(true);

        Label numberOfRoundsLabel = new Label(resources.getString("number_of_rounds"), resources.getSkin());
        roundsNumberLabel = new Label(String.valueOf(Constants.MIN_ROUNDS), resources.getSkin());

        goToPlayerSelectionButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        startGameButton = new DisableableTextButton(resources.getString("start_game"), resources.getSkin(), "default");
        startGameButton.disable();
        decreaseNumberOfRounds = new TextButton("<", resources.getSkin(), "transparent-sm");
        increaseNumberOfRounds = new TextButton(">", resources.getSkin(), "transparent-sm");

        table.setTitle(new Label(resources.getString("map_selection"), resources.getSkin(), "large"), cols);

        mapCheckboxes = new MapCheckBox[GameMap.values().length];

        for (int i = 0; i < GameMap.values().length; i++) {
            mapCheckboxes[i] = new MapCheckBox(GameMap.values()[i], resources);
            mapCheckboxes[i].registerObserver(this);

            table.add(mapCheckboxes[i])
                .pad(
                    Dimensions.COMPONENT_SPACING_LG,
                    Dimensions.COMPONENT_SPACING_SM,
                    0f,
                    Dimensions.COMPONENT_SPACING_SM
                );
        }

        table.row();

        HorizontalGroup group = new HorizontalGroup();
        group.space(Dimensions.COMPONENT_SPACING_XS);
        group.align(Align.center);
        group.addActor(numberOfRoundsLabel);
        group.addActor(decreaseNumberOfRounds);
        group.addActor(roundsNumberLabel);
        group.addActor(increaseNumberOfRounds);
        table.add(group)
            .colspan(cols)
            .center()
            .padTop(Dimensions.COMPONENT_SPACING)
            .row();

        table.setupDoubleButtons(goToPlayerSelectionButton, startGameButton, cols);

        super.addActor(table);
    }

    @Override
    public void initController() {
        decreaseNumberOfRounds.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rounds = Math.max(Constants.MIN_ROUNDS, rounds - 1);
                roundsNumberLabel.setText(String.valueOf(rounds));

                if (rounds < checkedCount) {
                    for (int i = GameMap.values().length - 1; i >= 0; i--) {
                        if (mapCheckboxes[i].isChecked()) {
                            mapCheckboxes[i].uncheck();
                            break;
                        }
                    }
                }
            }
        }));

        increaseNumberOfRounds.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rounds = Math.min(Constants.MAX_ROUNDS, rounds + 1);
                roundsNumberLabel.setText(String.valueOf(rounds));
            }
        }));

        goToPlayerSelectionButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getCandidate().reset();

                ScreenManager.getInstance().showPreviousScreen(true, true);
            }
        }));

        startGameButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (MapCheckBox mapCheckBox : mapCheckboxes) {
                    if (mapCheckBox.isChecked()) {
                        game.getCandidate().addMap(mapCheckBox.getMap());
                    }
                }
                game.getCandidate().setRounds(rounds);
                game.start();
            }
        }));
    }

    /**
     * Updates the checkedCount variable which holds the current number of checked MapCheckboxes.
     */
    private void updateCheckedCount() {
        int count = 0;

        for (int i = 0; i < GameMap.values().length; i++) {
            if (mapCheckboxes[i].isChecked()) {
                count++;
            }
        }

        checkedCount = count;
    }

    @Override
    public void refresh(Subject subject) {
        updateCheckedCount();

        // If the number of checked checkboxes is greater that the number of rounds, increase the number of rounds
        if (checkedCount > rounds) {
            rounds++;
            roundsNumberLabel.setText(String.valueOf(rounds));
        }

        startGameButton.toggle(checkedCount != 0);
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.MAP_SELECTION;
    }
}
