package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.PlayerSelector;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.ScreenType;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.PlayerBlueprintObservable;

import java.util.Observable;
import java.util.Observer;

/**
 * Screen where the user adds or removes players to the game he is creating.
 * Here he can define the number of players, their skins, as well as their type (human or AI)
 * as well as the difficulty of the AI.
 */
public class PlayerSelectionScreen extends BomberScreen implements Observer {
    /**
     * List of {@link PlayerBlueprintObservable} components.
     */
    private PlayerBlueprintObservable[] playerBlueprintObservables;

    /**
     * Button to continue and go to the map selection screen.
     */
    private TextButton goToMapSelectionButton;

    /**
     * Button to return to the previous screen (main menu).
     */
    private TextButton goToMainMenuButton;

    /**
     * Constructs a new LevelSetupScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public PlayerSelectionScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() {
        final int cols = 4;
        Table table = new Table();
//        table.setDebug(true);
        table.setFillParent(true);

        PlayerSelector[] playerSelectors = new PlayerSelector[Constants.MAX_PLAYERS];
        Label playerSelectionLabel = new Label(resources.getString("player_selection"), resources.getSkin(), "large");
        playerBlueprintObservables = new PlayerBlueprintObservable[Constants.MAX_PLAYERS];
        goToMainMenuButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        goToMapSelectionButton = new TextButton(resources.getString("continue"), resources.getSkin(), "inactive");
        goToMapSelectionButton.setTouchable(Touchable.disabled);

        table.add(playerSelectionLabel)
            .colspan(cols)
            .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
            .row();

        for (int i = 0; i < playerSelectors.length; i++) {
            playerBlueprintObservables[i] = new PlayerBlueprintObservable();
            playerSelectors[i] = new PlayerSelector(resources, playerBlueprintObservables[i], i);
            playerBlueprintObservables[i].addObserver(this);
            table.add(playerSelectors[i])
                .padLeft(Dimensions.COMPONENT_SPACING_SM)
                .padRight(Dimensions.COMPONENT_SPACING_SM);
        }

        table.row();

        table.add(goToMainMenuButton)
            .colspan(2)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING)
            .padRight(Dimensions.COMPONENT_SPACING_LG)
            .right();
        table.add(goToMapSelectionButton)
            .colspan(2)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING)
            .padLeft(Dimensions.COMPONENT_SPACING_LG)
            .left();

        super.addActor(table);
    }

    @Override
    public void initController() {
        goToMainMenuButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showPreviousScreen();
            }
        }, resources));

        goToMapSelectionButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.MAP_SELECTION);
            }
        }, resources));
    }

    @Override
    public void update(Observable observable, Object o) {
        int validBlueprintsCount = 0;

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            if (playerBlueprintObservables[i].isValid()) {
                validBlueprintsCount++;
            }
        }

        changeGoToMapSelectionButtonState(validBlueprintsCount);
    }

    /**
     * Enabled or disabled the button that allows the user to go to the next screen (configuring map).
     * At least {@link Constants#MIN_PLAYERS} players should be valid for the button to be active.
     * @param validBlueprintsCount the number of valid players.
     */
    private void changeGoToMapSelectionButtonState(int validBlueprintsCount) {
        if (validBlueprintsCount < Constants.MIN_PLAYERS) {
            goToMapSelectionButton.setTouchable(Touchable.disabled);
            goToMapSelectionButton.setStyle(
                resources.getSkin().get("inactive", TextButton.TextButtonStyle.class
                ));
        } else {
            goToMapSelectionButton.setTouchable(Touchable.enabled);
            goToMapSelectionButton.setStyle(
                resources.getSkin().get("default", TextButton.TextButtonStyle.class
                ));
        }
    }
}
