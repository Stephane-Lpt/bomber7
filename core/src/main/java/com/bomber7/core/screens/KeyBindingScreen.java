package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Controls;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;

/**
 * A screen where a player can bind its input controls.
 */
public class KeyBindingScreen extends BomberScreen {

    private TextButton confirmButton;
    private TextButton resetBindingButton;
    private TextButton[] inputButtons;

    /**
     * The index of the key this binding screen is associated to.
     */
    private int playerIndex;

    /**
     * Constructs a new KeyBindingScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public KeyBindingScreen(Game game) {
        super(game);
        this.playerIndex = 2;
    }

    @Override
    public void initView() {
        BomberTable table = new BomberTable();
        table.setFillParent(true);
//        table.setDebug(true);

        Label[] inputLabels = new Label[Controls.values().length];
        inputButtons = new TextButton[Controls.values().length];

        confirmButton = new TextButton(resources.getString("confirm"), resources.getSkin());
        resetBindingButton = new TextButton(resources.getString("reset"), resources.getSkin(), "red");

        table.setTitle(new Label(
            resources.getString("key_binding") + " " + resources.getString("player").toLowerCase() + " " + (playerIndex + 1),
            resources.getSkin(),
            "large"
        ), 2);

        for (int i = 0; i < Controls.values().length; i++) {
            Controls control = Controls.values()[i];
            inputLabels[i] = new Label(resources.getString(control.toString().toLowerCase()), resources.getSkin());
            inputButtons[i] = new TextButton(control.getKeyForPlayer(playerIndex), resources.getSkin());

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

        table.setupDoubleButtons(resetBindingButton, confirmButton,2);

        this.addActor(table);
    }

    @Override
    public void initController() {
        resetBindingButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO
            }
        }, resources));

        confirmButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO
                ScreenManager.getInstance().showPreviousScreen(false, false);
            }
        }, resources));

        for (int i = 0; i < Controls.values().length; i++) {
            inputButtons[i].addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // TODO
                }
            }, resources));
        }
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.KEY_BINDING;
    }
}
