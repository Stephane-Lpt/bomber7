package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.bomber7.core.components.BomberTextButton;

public class OptionsScreen extends BomberScreen {
    public OptionsScreen(Game game) {
        super(game);
        initView();
        initController();
    }

    private Slider globalVolumeSlider;
    private Slider musicVolumeSlider;
    private BomberTextButton[] configPlayerButtons = new BomberTextButton[game.MAX_PLAYERS];
    private BomberTextButton confirmChangesButton;
    private BomberTextButton goBackButton;

    public void initView() {
        Table table = new Table();
        table.setDebug(true);
        table.setFillParent(true);

        Label optionsLabels = new Label(resources.bundle.get("options"), resources.skin, "large");
        Label globalVolumeLabel = new Label(resources.bundle.get("global_volume"), resources.skin, "medium");
        Label musicLabel = new Label(resources.bundle.get("music"), resources.skin, "medium");
        Label keyBindingLabel = new Label(resources.bundle.get("key_binding"), resources.skin, "medium");
        Label playerLabels[] = new Label[game.MAX_PLAYERS];

        for (int i = 0; i < game.MAX_PLAYERS; i++) {
            playerLabels[i] = new Label(resources.bundle.get("player") + " " + (i + 1), resources.skin);
            configPlayerButtons[i] = new BomberTextButton(resources.bundle.get("configure"), resources);
        }

        globalVolumeSlider = new Slider(0f, 100f, 1f, false, resources.skin);
        musicVolumeSlider = new Slider(0f, 100f, 1f, false, resources.skin);

        confirmChangesButton = new BomberTextButton(resources.bundle.get("validate"), resources);
        goBackButton = new BomberTextButton(resources.bundle.get("go_back"), resources);

        table.add(optionsLabels)
            .colspan(2)
            .spaceBottom(this.COMPONENT_SPACING / 2f)
            .row();
        table.add(globalVolumeLabel)
            .spaceBottom(this.LABEL_PADDING)
            .padRight(this.LABEL_PADDING)
            .left();
        table.add(musicLabel)
            .spaceBottom(this.LABEL_PADDING)
            .padLeft(this.LABEL_PADDING)
            .left()
            .row();
        table.add(globalVolumeSlider)
            .fillX()
            .padRight(this.LABEL_PADDING);
        table.add(musicVolumeSlider)
            .fillX()
            .padLeft(this.LABEL_PADDING)
            .row();

        table.add(keyBindingLabel)
            .colspan(2)
            .left()
            .spaceTop(this.COMPONENT_SPACING / 2f)
            .spaceBottom(this.LABEL_PADDING)
            .row();

        for (int i = 0; i < game.MAX_PLAYERS; i++) {
            table.add(playerLabels[i])
                .right()
                .padRight(this.COMPONENT_SPACING / 2f)
                .spaceBottom(this.COMPONENT_SPACING / 2f);
            table.add(configPlayerButtons[i])
                .width(this.BUTTON_WIDTH_SM)
                .height(this.BUTTON_HEIGHT_SM)
                .spaceBottom(this.COMPONENT_SPACING / 2f)
                .padLeft(this.COMPONENT_SPACING / 2f)
                .left()
                .row();
        }

        table.add(goBackButton)
            .width(this.BUTTON_WIDTH)
            .height(this.BUTTON_HEIGHT)
            .padTop(this.COMPONENT_SPACING / 2f)
            .padRight(this.COMPONENT_SPACING / 2f);
        table.add(confirmChangesButton)
            .width(this.BUTTON_WIDTH)
            .height(this.BUTTON_HEIGHT)
            .padTop(this.COMPONENT_SPACING / 2f)
            .padLeft(this.COMPONENT_SPACING / 2f);

        stage.addActor(table);
    }

    public void initController() {

    }
}
