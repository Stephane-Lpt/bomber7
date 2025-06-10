package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ConfigManager;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.core.components.InputDialog;
import com.bomber7.core.controller.PlayerConfig;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Controls;
import com.bomber7.utils.DefaultConfig;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;

/**
 * A screen where a player can bind its input controls.
 */
public class KeyBindingScreen extends BomberScreen implements InputProcessor {

    /**
     * The dialog that will be shown when a user clicks on any inputButton to rebind a control to a new key.
     */
    private InputDialog inputDialog;
    /**
     * The index of the player this binding screen is associated to.
     */
    private int playerIndex;
    /**
     * A button to go back to the settings screen.
     */
    private TextButton goBackButton;
    /**
     * A button to reset current's player controls to defaults.
     */
    private TextButton resetBindingButton;
    /**
     * Buttons representing input controls, on which the user can click and set his own inputs.
     */
    private TextButton[] inputButtons;

    /**
     * Constructs a new KeyBindingScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     * @param playerIndex the index of the player this screen is supposed to modify
     */
    public KeyBindingScreen(Game game, int playerIndex) {
        super(game);

        this.playerIndex = playerIndex;
        inputDialog = new InputDialog(resources, playerIndex) {

            @Override
            public void hide() {
                Gdx.input.setInputProcessor(getStage());
                updateInputButtons();
                getColor().a = 0f;
                clearActions();
                super.hide();
            }
        };

        initView();
        initController();
    }

    @Override
    public void initView() {
        BomberTable table = new BomberTable();
        table.setFillParent(true);

        Label[] inputLabels = new Label[Controls.values().length];
        inputButtons = new TextButton[Controls.values().length];

        goBackButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        resetBindingButton = new TextButton(resources.getString("reset"), resources.getSkin(), "red");

        table.setTitle(new Label(
            resources.getString("key_binding") + " " + resources.getString("player").toLowerCase() + " " + (playerIndex + 1),
            resources.getSkin(),
            "large"
        ), 2);

        for (int i = 0; i < Controls.values().length; i++) {
            Controls control = Controls.values()[i];

            inputLabels[i] = new Label(resources.getString(control.toString().toLowerCase()), resources.getSkin());
            inputButtons[i] = new TextButton(
                "",
                resources.getSkin()
            );

            table.add(inputLabels[i])
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padRight(Dimensions.COMPONENT_SPACING_LG);
            table.add(inputButtons[i])
                .width(Dimensions.BUTTON_WIDTH_SM)
                .height(Dimensions.BUTTON_HEIGHT_SM)
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padLeft(Dimensions.COMPONENT_SPACING_LG);
            table.row();
        }

        table.setupDoubleButtons(resetBindingButton, goBackButton, 2);

        this.addActor(table);
        updateInputButtons();
    }

    @Override
    public void initController() {
        resetBindingButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigManager.getInstance().getConfig().setPlayerConfig(
                    new PlayerConfig(DefaultConfig.getControlsForPlayer(playerIndex)),
                    playerIndex
                );
                updateInputButtons();
            }
        }, resources));

        goBackButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showPreviousScreen(false, false);
            }
        }, resources));

        for (int i = 0; i < Controls.values().length; i++) {
            int controlIndex = i;
            inputButtons[i].addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    inputDialog.show(KeyBindingScreen.this, (Controls.values()[controlIndex]));
                }
            }, resources));
        }
    }

    private void updateInputButtons() {
        PlayerConfig playerConfig = ConfigManager.getInstance().getConfig().getPlayerConfig(playerIndex);

        for (int i = 0; i < Controls.values().length; i++) {
            Controls control = Controls.values()[i];
            inputButtons[i].setText(Input.Keys.toString((playerConfig.getKeyBinding(control))));
        }
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.KEY_BINDING;
    }

    @Override
    public void show() {
        updateInputButtons();
        super.show();
    }
}
