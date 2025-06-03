package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.core.components.DisableableTextButton;
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
     * Button to return to the previous screen (main menu).
     */
    private TextButton goToMainMenuButton;

    /**
     * Button to continue and go to the map selection screen.
     */
    private DisableableTextButton goToMapSelectionButton;

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
        BomberTable table = new BomberTable();
//        table.setDebug(true);
        table.setFillParent(true);

        PlayerSelector[] playerSelectors = new PlayerSelector[Constants.MAX_PLAYERS];
        playerBlueprintObservables = new PlayerBlueprintObservable[Constants.MAX_PLAYERS];
        goToMainMenuButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        goToMapSelectionButton = new DisableableTextButton(resources.getString("continue"), resources.getSkin(), "inactive");
        goToMapSelectionButton.setTouchable(Touchable.disabled);

        table.setTitle(new Label(resources.getString("player_selection"), resources.getSkin(), "large"), cols);

        for (int i = 0; i < playerSelectors.length; i++) {
            playerBlueprintObservables[i] = new PlayerBlueprintObservable();
            playerSelectors[i] = new PlayerSelector(resources, playerBlueprintObservables[i], i);
            playerBlueprintObservables[i].addObserver(this);
            table.add(playerSelectors[i])
                .padLeft(Dimensions.COMPONENT_SPACING_SM)
                .padRight(Dimensions.COMPONENT_SPACING_SM);
        }

        table.row();

        table.setupDoubleButtons(goToMainMenuButton, goToMapSelectionButton, cols);

        super.addActor(table);
    }

    @Override
    public void initController() {
        goToMainMenuButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU);
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

        goToMapSelectionButton.toggle(validBlueprintsCount >= Constants.MIN_PLAYERS);
    }
}
