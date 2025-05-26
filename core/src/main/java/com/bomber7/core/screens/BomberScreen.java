package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomber7.core.BomberGame;
import com.bomber7.core.BomberResources;

public abstract class BomberScreen implements Screen {
    protected final Float COMPONENT_SPACING = 50f;
    protected final Float BUTTON_HEIGHT = 80f;
    protected final Float BUTTON_WIDTH = 400f;

    protected Color backgroundColor;
    protected Game game;
    protected Stage stage;
    protected BomberResources resources;

    public BomberScreen(Game game) {
        this.game = game;
        this.resources = ((BomberGame) game).resources;
        this.backgroundColor = resources.skin.getColor("darkBlue");
        initializeStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
