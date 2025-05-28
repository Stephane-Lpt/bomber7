package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTextButton;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;

/**
 * Settings screen where the user can configure game options such as volume levels,
 * key bindings, and player controls.
 * <p>
 * This screen provides sliders for global and music volume adjustment,
 * buttons for configuring player controls, and buttons to confirm changes or go back.
 * </p>
 */
public class SettingsScreen extends BomberScreen {
    /**
     * Minimum value for the volume sliders.
     */
    private static final float VOLUME_SLIDER_MIN = 0f;

    /**
     * Maximum value for the volume sliders.
     */
    private static final float VOLUME_SLIDER_MAX = 100f;

    /**
     * Step size for the volume sliders.
     */
    private static final float VOLUME_SLIDER_STEP = 1f;

    /**
     * Slider to adjust global volume.
     */
    private Slider globalVolumeSlider;

    /**
     * Slider to adjust music volume.
     */
    private Slider musicVolumeSlider;

    /**
     * Buttons to configure each player's key bindings.
     */
    private BomberTextButton[] configPlayerButtons;

    /**
     * Button to confirm and save changes.
     */
    private BomberTextButton confirmChangesButton;

    /**
     * Button to return to the previous screen.
     * If not saved, changes will not be applied.
     */
    private BomberTextButton goBackButton;

    /**
     * Constructs a new SettingsScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public SettingsScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() {
        Table table = new Table();
        table.setDebug(true);
        table.setFillParent(true);

        Label optionsLabels = new Label(resources.getString("options"), resources.getSkin(), "large");
        Label globalVolumeLabel = new Label(resources.getString("global_volume"), resources.getSkin(), "medium");
        Label musicLabel = new Label(resources.getString("music"), resources.getSkin(), "medium");
        Label keyBindingLabel = new Label(resources.getString("key_binding"), resources.getSkin(), "medium");
        Label[] playerLabels = new Label[game.MAX_PLAYERS];

        configPlayerButtons = new BomberTextButton[game.MAX_PLAYERS];

        for (int i = 0; i < game.MAX_PLAYERS; i++) {
            playerLabels[i] = new Label(resources.getString("player") + " " + (i + 1), resources.getSkin());
            configPlayerButtons[i] = new BomberTextButton(resources.getString("configure"), resources);
        }

        globalVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());
        musicVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());

        confirmChangesButton = new BomberTextButton(resources.getString("validate"), resources);
        goBackButton = new BomberTextButton(resources.getString("go_back"), resources);

        table.add(optionsLabels)
            .colspan(2)
            .spaceBottom(Dimensions.COMPONENT_SPACING / 2f)
            .row();
        table.add(globalVolumeLabel)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .padRight(Dimensions.LABEL_PADDING)
            .left();
        table.add(musicLabel)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .padLeft(Dimensions.LABEL_PADDING)
            .left()
            .row();
        table.add(globalVolumeSlider)
            .fillX()
            .padRight(Dimensions.LABEL_PADDING);
        table.add(musicVolumeSlider)
            .fillX()
            .padLeft(Dimensions.LABEL_PADDING)
            .row();

        table.add(keyBindingLabel)
            .colspan(2)
            .left()
            .spaceTop(Dimensions.COMPONENT_SPACING / 2f)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .row();

        for (int i = 0; i < game.MAX_PLAYERS; i++) {
            table.add(playerLabels[i])
                .right()
                .padRight(Dimensions.COMPONENT_SPACING / 2f)
                .spaceBottom(Dimensions.COMPONENT_SPACING / 2f);
            table.add(configPlayerButtons[i])
                .width(Dimensions.BUTTON_WIDTH_SM)
                .height(Dimensions.BUTTON_HEIGHT_SM)
                .spaceBottom(Dimensions.COMPONENT_SPACING / 2f)
                .padLeft(Dimensions.COMPONENT_SPACING / 2f)
                .left()
                .row();
        }

        table.add(goBackButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING / 2f)
            .padRight(Dimensions.COMPONENT_SPACING / 2f);
        table.add(confirmChangesButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING / 2f)
            .padLeft(Dimensions.COMPONENT_SPACING / 2f);

        super.addActor(table);
    }

    @Override
    public void initController() {
        goBackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showPreviousScreen();
            }
        });
    }
}
