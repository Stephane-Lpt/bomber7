package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ConfigManager;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberDialog;
import com.bomber7.core.components.BomberTable;
import com.bomber7.core.components.DisableableTextButton;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Constants;
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
    private TextButton[] configPlayerButtons;

    /**
     * Button to confirm and save changes.
     */
    private DisableableTextButton saveChangesButton;

    /**
     * Button to return to the previous screen.
     * If not saved, changes will not be applied.
     */
    private TextButton goBackButton;

    /**
     * A dialog that pops up when there is some unsaved changes.
     */
    private BomberDialog unsavedChangesDialog;

    /**
     * Button to switch to the previous language.
     */
    private TextButton changeLanguageLeftButton;

    /**
     * Button to switch to the next language.
     */
    private TextButton changeLanguageRightButton;
    /**
     * Label displaying the current language.
     */
    private Label languageValue;

    /**
     * Constructs a new SettingsScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public SettingsScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    @Override
    public void initView() {
        unsavedChangesDialog = new BomberDialog(
            resources.getString("go_back"),
            resources.getString("unsaved_changes_text"),
            resources.getString("save_settings"),
            resources.getString("discard"),
            resources.getSkin()
        );

        final int cols = 2;
        BomberTable table = new BomberTable();
        table.setFillParent(true);

        Label globalVolumeLabel = new Label(resources.getString("global_volume"), resources.getSkin(), "medium");
        Label musicLabel = new Label(resources.getString("music"), resources.getSkin(), "medium");
        Label languageLabel = new Label(resources.getString("language"), resources.getSkin(), "medium");
        languageValue = new Label(
            resources.getString(ConfigManager.getInstance().getConfig().getLanguage().toString().toLowerCase()),
            resources.getSkin(),
            "medium"
        );
        Label keyBindingLabel = new Label(resources.getString("key_binding"), resources.getSkin(), "medium");
        Label[] playerLabels = new Label[Constants.MAX_PLAYERS];

        configPlayerButtons = new TextButton[Constants.MAX_PLAYERS];

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            playerLabels[i] = new Label(resources.getString("player") + " " + (i + 1), resources.getSkin());
            configPlayerButtons[i] = new TextButton(resources.getString("configure"), resources.getSkin());
        }

        globalVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());
        globalVolumeSlider.setValue((float) ConfigManager.getInstance().getConfig().getGlobalVolume());
        musicVolumeSlider = new Slider(VOLUME_SLIDER_MIN, VOLUME_SLIDER_MAX, VOLUME_SLIDER_STEP, false, resources.getSkin());
        musicVolumeSlider.setValue((float) ConfigManager.getInstance().getConfig().getMusicVolume());

        goBackButton = new TextButton(resources.getString("go_back"), resources.getSkin());
        saveChangesButton = new DisableableTextButton(resources.getString("save_settings"), resources.getSkin(), "green");
        updateSaveButtonState();

        changeLanguageLeftButton = new TextButton("<", resources.getSkin(), "transparent-sm");
        changeLanguageRightButton = new TextButton(">", resources.getSkin(), "transparent-sm");

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
            .spaceBottom(Dimensions.LABEL_PADDING)
            .padRight(Dimensions.LABEL_PADDING);
        table.add(musicVolumeSlider)
            .fillX()
            .padLeft(Dimensions.LABEL_PADDING)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .row();

        table.add(languageLabel)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .left();

        HorizontalGroup group = new HorizontalGroup();
        group.space(Dimensions.COMPONENT_SPACING_SM);
        group.addActor(changeLanguageLeftButton);
        group.addActor(languageValue);
        group.addActor(changeLanguageRightButton);

        table.add(group)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .right()
            .row();

        table.add(keyBindingLabel)
            .colspan(cols)
            .left()
            .spaceTop(Dimensions.COMPONENT_SPACING_LG)
            .spaceBottom(Dimensions.LABEL_PADDING)
            .row();

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            table.add(playerLabels[i])
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padRight(Dimensions.COMPONENT_SPACING_LG);
            table.add(configPlayerButtons[i])
                .width(Dimensions.BUTTON_WIDTH_SM)
                .height(Dimensions.BUTTON_HEIGHT_SM)
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padLeft(Dimensions.COMPONENT_SPACING_LG);
            table.row();
        }

        table.setupDoubleButtons(goBackButton, saveChangesButton, 2);

        super.addActor(table);
    }

    @Override
    public void initController() {
        changeLanguageLeftButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigManager.getInstance().getConfig().setLanguage(
                    ConfigManager.getInstance().getConfig().getLanguage().previous()
                );
                languageValue.setText(
                    resources.getString(ConfigManager.getInstance().getConfig().getLanguage().toString().toLowerCase())
                );
                updateSaveButtonState();
                resources.updateLanguage();
                ScreenManager.getInstance().showScreen(ScreenType.SETTINGS, false, false);
            }
        }, resources));

        changeLanguageRightButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigManager.getInstance().getConfig().setLanguage(
                    ConfigManager.getInstance().getConfig().getLanguage().next()
                );
                languageValue.setText(
                    resources.getString(ConfigManager.getInstance().getConfig().getLanguage().toString().toLowerCase())
                );
                updateSaveButtonState();
                resources.updateLanguage();
                ScreenManager.getInstance().showScreen(ScreenType.SETTINGS, false, false);
            }
        }, resources));

        unsavedChangesDialog.setOnResult(save -> {
            if (save) {
                ConfigManager.getInstance().saveModifications();
                ScreenManager.getInstance().showPreviousScreen(false, false);
            } else {
                ConfigManager.getInstance().resetModifications();
                ScreenManager.getInstance().showPreviousScreen(false, false);
            }
        });

        goBackButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ConfigManager.getInstance().isUnsaved()) {
                    unsavedChangesDialog.show(SettingsScreen.this);
                } else {
                    ScreenManager.getInstance().showPreviousScreen(false, false);
                }
            }
        }, resources));

        saveChangesButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigManager.getInstance().saveModifications();
                updateSaveButtonState();
            }
        }, resources));

        for (int i = 0; i < Constants.MAX_PLAYERS; i++) {
            int playerIndex = i;
            configPlayerButtons[i].addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ScreenManager.getInstance().showScreen(ScreenType.KEY_BINDING, true, false, playerIndex);
                }
            }, resources));
        }

        globalVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ConfigManager.getInstance().getConfig().setGlobalVolume(
                    (int) globalVolumeSlider.getValue()
                );
                updateSaveButtonState();
            }
        });

        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ConfigManager.getInstance().getConfig().setMusicVolume(
                    (int) musicVolumeSlider.getValue()
                );
                updateSaveButtonState();
            }
        });

        globalVolumeSlider.addListener(ComponentsUtils.addSoundEffect(new ClickListener() { }, resources));
        musicVolumeSlider.addListener(ComponentsUtils.addSoundEffect(new ClickListener() { }, resources));
    }

    /**
     * Updates the state of the "save" button.
     */
    public void updateSaveButtonState() {
        if (ConfigManager.getInstance().isUnsaved()) {
            saveChangesButton.enable();
        } else {
            saveChangesButton.disable();
        }
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.SETTINGS;
    }
}
