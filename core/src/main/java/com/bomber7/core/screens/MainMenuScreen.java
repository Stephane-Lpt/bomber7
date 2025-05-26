package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bomber7.core.components.BomberTextButton;

public class MainMenuScreen extends BomberScreen {
    private Image backgroundImage;
    private Image logoImage;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton quitButton;

    public MainMenuScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    public void initView() {
        backgroundImage = new Image(new Texture("images/background.png"));
        backgroundImage.setFillParent(true);

        logoImage = new Image(new Texture("images/bomber7.png"));
        logoImage.setPosition(
            Gdx.graphics.getWidth() / 2f - logoImage.getWidth() / 2,
            Gdx.graphics.getHeight() * 0.6f
        );

        playButton = new BomberTextButton(resources.bundle.get("play"), resources);
        playButton.pack();
        playButton.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f
        );

        optionsButton = new BomberTextButton(resources.bundle.get("options"), resources);
        optionsButton.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f - 70
        );

        quitButton = new BomberTextButton(resources.bundle.get("quit"), resources);
        quitButton.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f - 140
        );

        stage.addActor(logoImage);
        stage.addActor(backgroundImage);
        stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(quitButton);
    }

    public void initController() {
        playButton.addListener(e -> {
            return false;
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
