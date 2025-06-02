package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Constants;
import com.bomber7.utils.Dimensions;

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
    private TextButton[] configPlayerButtons;

    /**
     * Button to confirm and save changes.
     */
    private TextButton confirmChangesButton;

    /**
     * Button to return to the previous screen.
     * If not saved, changes will not be applied.
     */
    private TextButton goBackButton;

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
        final int cols = 2;
        BomberTable table = new BomberTable();
        table.setDebug(true);
        table.setFillParent(true);

        Label optionsLabels = new Label(resources.getString("options"), resources.getSkin(), "large");
        Label globalVolumeLabel = new Label(resources.getString("global_volume"), resources.getSkin(), "medium");
        Label musicLabel = new Label(resources.getString("music"), resources.getSkin(), "medium");
        Label keyBindingLabel = new Label(resources.getString("key_binding"), resources.getSkin(), "medium");
        Label[] playerLabels = new Label[Constants.MAX_PLAYERS];

        configPlayerButtons = new TextButton[Constants.MAX_PLAYERS];

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            playerLabels[i] = new Label(resources.getString("player") + " " + (i + 1), resources.getSkin());
            configPlayerButtons[i] = new TextButton(resources.getString("configure"), resources.getSkin());
        }

        globalVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());
        musicVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());

        confirmChangesButton = new TextButton(resources.getString("validate"), resources.getSkin());
        goBackButton = new TextButton(resources.getString("go_back"), resources.getSkin());

        table.setTitle(new Label(resources.getString("options"), resources.getSkin(), "large"), cols);
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
            .colspan(cols)
            .left()
            .spaceTop(Dimensions.COMPONENT_SPACING_LG)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .row();

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            table.add(playerLabels[i])
                .right()
                .padRight(Dimensions.COMPONENT_SPACING_LG)
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG);
            table.add(configPlayerButtons[i])
                .width(Dimensions.BUTTON_WIDTH_SM)
                .height(Dimensions.BUTTON_HEIGHT_SM)
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padLeft(Dimensions.COMPONENT_SPACING_LG)
                .left()
                .row();
        }

        table.setupDoubleButtons(goBackButton, confirmChangesButton, 2);

        super.addActor(table);
    }

    @Override
    public void initController() {
        goBackButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showPreviousScreen();
            }
        }, resources));

        confirmChangesButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO
            }
        }, resources));
    }
}
