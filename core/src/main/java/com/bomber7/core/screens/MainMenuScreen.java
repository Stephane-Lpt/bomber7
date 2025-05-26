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
import com.bomber7.core.components.BomberTextButton;

public class MainMenuScreen extends BomberScreen {
    private final float LOGO_WIDTH = 500f * 0.6f;
    private final float LOGO_HEIGHT = LOGO_WIDTH * 0.6f;
    
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton quitButton;

    public MainMenuScreen(Game game) {
        super(game);
        initView();
        initController();
    }

    public void initView() {
        Table table = new Table();
        table.setDebug(true);
        table.setFillParent(true);

        Image backgroundImage = new Image(new Texture("images/background.png"));
        backgroundImage.setFillParent(true);

        Image logoImage = new Image(new Texture("images/logo.png"));
        logoImage.setScaling(Scaling.fit);
        playButton = new BomberTextButton(resources.bundle.get("play"), resources);
        optionsButton = new BomberTextButton(resources.bundle.get("options"), resources);
        quitButton = new BomberTextButton(resources.bundle.get("quit"), resources);

        table.add(logoImage)
            .width(this.LOGO_WIDTH)
            .height(this.LOGO_HEIGHT)
            .spaceBottom(this.COMPONENT_SPACING * 2f)
            .fillX()
            .row();
        table.add(playButton)
            .width(this.BUTTON_WIDTH)
            .fillX()
            .height(this.BUTTON_HEIGHT)
            .spaceBottom(this.COMPONENT_SPACING)
            .row();
        table.add(optionsButton)
            .width(this.BUTTON_WIDTH)
            .height(this.BUTTON_HEIGHT)
            .spaceBottom(this.COMPONENT_SPACING)
            .row();
        table.add(quitButton)
            .width(this.BUTTON_WIDTH)
            .height(this.BUTTON_HEIGHT)
            .row();

        stage.addActor(table);
    }

    public void initController() {
        playButton.addListener(e -> {
            return false;
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
