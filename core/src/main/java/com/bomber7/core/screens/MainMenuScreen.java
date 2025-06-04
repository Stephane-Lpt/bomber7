package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.bomber7.core.ScreenManager;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;

/**
 * The main menu of the game.
 */
public class MainMenuScreen extends BomberScreen {
    /**
     * Main menu logo width.
     */
    private static final float LOGO_WIDTH = 500f * 0.6f;
    /**
     * Main menu logo height.
     */
    private static final float LOGO_HEIGHT = LOGO_WIDTH * 0.6f;
    /**
     * Play button that switches screen to {@link PlayerSelectionScreen}.
     */
    private TextButton playButton;
    /**
     * Button used to go to the settings {@link PlayerSelectionScreen}.
     */
    private TextButton settingsButton;
    /**
     * Button used to quit the game.
     */
    private TextButton quitButton;

    /**
     * Constructs a new MainMenuScreen associated with the given game.
     *
     * @param game the Game instance this screen belongs to
     */
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void initView() {
        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);

        Image backgroundImage = new Image(new Texture("images/background.png"));
        backgroundImage.setFillParent(true);

        Image logoImage = new Image(new Texture("images/logo.png"));
        logoImage.setScaling(Scaling.fit);
        playButton = new TextButton(resources.getString("play"), resources.getSkin());
        settingsButton = new TextButton(resources.getString("options"), resources.getSkin());
        quitButton = new TextButton(resources.getString("quit"), resources.getSkin());

        table.add(logoImage)
            .width(LOGO_WIDTH)
            .height(LOGO_HEIGHT)
            .spaceBottom(Dimensions.COMPONENT_SPACING * 2f)
            .fillX()
            .row();
        table.add(playButton)
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
        table.add(quitButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .row();

        super.addActor(table);
    }

    @Override
    public void initController() {
        playButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.PLAYER_SETUP, true, false);
            }
        }, resources));

        settingsButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.SETTINGS, true, false);
            }
        }, resources));

        quitButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        }, resources));
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.MAIN_MENU;
    }
}
