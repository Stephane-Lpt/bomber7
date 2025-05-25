package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomber7.core.BomberGame;

public abstract class BomberScreen implements Screen {
    protected Skin skin;
    protected Game game;
    protected Stage stage;
    protected I18NBundle bundle;

    public BomberScreen(Game game) {
        this.game = game;
        this.skin = ((BomberGame) game).sharedSkin;
        this.bundle = ((BomberGame) game).bundle;
        initializeStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int with, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void initializeStage() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
}
