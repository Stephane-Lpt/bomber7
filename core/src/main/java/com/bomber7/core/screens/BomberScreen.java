package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomber7.core.BomberGame;
import com.bomber7.utils.ResourceManager;

/**
 * An abstract Screen class that all bomber screens should inherit from.
 */
public abstract class BomberScreen implements Screen {
    /**
     * Background color of the app.
     */
    private final Color bgColor;
    /**
     * Game object.
     */
    protected final BomberGame game;
    /**
     * 2D Stage object.
     */
    protected Stage stage;
    /**
     * Game resources.
     */
    protected final ResourceManager resources;

    /**
     * Initializes the screen.
     * @param game the Game instance this screen belongs to
     */
    public BomberScreen(Game game) {
        this.game = (BomberGame) game;
        this.resources = this.game.getBomberResources();
        this.bgColor = resources.getSkin().getColor("darkBlue");
        initializeStage();
        initView();
        initController();
    }

    /**
     * A method where all view components should be initialized and positioned.
     */
    public abstract void initView();

    /**
     * A method where all controls should be initialized (button clicks, etc..).
     */
    public abstract void initController();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
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

    /**
     * Initializes the stage.
     */
    private void initializeStage() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
}
