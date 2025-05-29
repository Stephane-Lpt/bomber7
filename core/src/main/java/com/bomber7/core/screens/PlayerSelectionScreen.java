package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.PlayerSelector;
import com.bomber7.utils.*;

/**
 * Screen where the user adds or removes players to the game he is creating.
 * Here he can define the number of players, their skins, as well as their type (human or AI)
 * as well as the difficulty of the AI.
 */
public class PlayerSelectionScreen extends BomberScreen {
    // TODO : unsubscribe every playerSelector from playerBlueprint on dispose

    /**
     * PlayerBlueprint list to store all the playerBlueprints
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
        Table table = new Table();
//        table.setDebug(true);
        table.setFillParent(true);

        Label playerSelectionLabel = new Label(resources.getString("player_selection"), resources.getSkin(), "large");
        PlayerSelector[] playerSelectors = new PlayerSelector[game.MAX_PLAYERS];
        playerBlueprintObservables = new PlayerBlueprintObservable[game.MAX_PLAYERS];
        goToMapSelectionButton = new TextButton(resources.getString("continue"), resources.getSkin());
        goToMainMenuButton = new TextButton(resources.getString("go_back"), resources.getSkin());

        table.add(playerSelectionLabel)
            .colspan(4)
            .spaceBottom(Dimensions.COMPONENT_SPACING / 2f)
            .row();

        for(int i = 0; i < playerSelectors.length; i++) {
            playerBlueprintObservables[i] = new PlayerBlueprintObservable();
            playerSelectors[i] = new PlayerSelector(resources, playerBlueprintObservables[i], i);
            table.add(playerSelectors[i])
                .padLeft(Dimensions.COMPONENT_SPACING / 4f)
                .padRight(Dimensions.COMPONENT_SPACING / 4f);
        }

        table.row();

        table.add(goToMainMenuButton)
            .colspan(2)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING)
            .padRight(Dimensions.COMPONENT_SPACING / 2f)
            .right();
        table.add(goToMapSelectionButton)
            .colspan(2)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING)
            .padLeft(Dimensions.COMPONENT_SPACING / 2f)
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
}
