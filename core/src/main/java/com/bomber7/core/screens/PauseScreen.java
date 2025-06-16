package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;

/**
 * Pause screen.
 */
public class PauseScreen extends BomberScreen {

    /**
     * Resume button that resumes the game.
     */
    private TextButton resumeButton;
    /**
     * Button used to go to the settings {@link PlayerSelectionScreen}.
     */
    private TextButton settingsButton;
    /**
     * Button used to end the game and go to the main menu.
     */
    private TextButton mainMenuButton;

    /**
     * Constructs a new PauseScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public PauseScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    @Override
    public void initView() {
        final int cols = 1;
        BomberTable table = new BomberTable();
        table.setFillParent(true);

        Image backgroundImage = new Image(new Texture("images/background.png"));
        backgroundImage.setFillParent(true);

        Image logoImage = new Image(new Texture("images/logo.png"));
        logoImage.setScaling(Scaling.fit);
        resumeButton = new TextButton(resources.getString("resume"), resources.getSkin());
        settingsButton = new TextButton(resources.getString("options"), resources.getSkin());
        mainMenuButton = new TextButton(resources.getString("go_back_to_main_menu"), resources.getSkin());

        table.setTitle(new Label(resources.getString("pause"), resources.getSkin(), "large"), cols);

        table.add(resumeButton)
            .spaceTop(Dimensions.COMPONENT_SPACING)
            .width(Dimensions.BUTTON_WIDTH)
            .fillX()
            .height(Dimensions.BUTTON_HEIGHT)
            .spaceBottom(Dimensions.COMPONENT_SPACING)
            .row();
        table.add(settingsButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .spaceBottom(Dimensions.COMPONENT_SPACING)
            .row();
        table.add(mainMenuButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .row();

        super.addActor(table);
    }

    @Override
    public void initController() {
        resumeButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resume();
            }
        }, resources));

        settingsButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.SETTINGS, true, true);
            }
        }, resources));

        mainMenuButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.stop();
            }
        }, resources));
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.PAUSE;
    }
}

