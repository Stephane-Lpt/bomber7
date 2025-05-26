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
    protected final Float LABEL_PADDING = 10f;
    protected final Float BUTTON_HEIGHT = 80f;
    protected final Float BUTTON_HEIGHT_SM = 60f;
    protected final Float BUTTON_WIDTH = 400f;
    protected final Float BUTTON_WIDTH_SM = 300f;

    protected Color backgroundColor;
    protected BomberGame game;
    protected Stage stage;
    protected BomberResources resources;

    public BomberScreen(Game game) {
        this.game = (BomberGame) game;
        this.resources = this.game.resources;
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
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
