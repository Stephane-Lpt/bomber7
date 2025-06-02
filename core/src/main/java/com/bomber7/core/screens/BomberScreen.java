package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bomber7.core.BomberGame;
import com.bomber7.utils.MVCComponent;
import com.bomber7.core.ResourceManager;

/**
 * An abstract Screen class that all bomber screens should inherit from.
 */
public abstract class BomberScreen extends Stage implements Screen, MVCComponent {
    /**
     * Background color of the app.
     */
    private final Color bgColor;
    /**
     * Game object.
     */
    protected final BomberGame game;
    /**
     * Game resources.
     */
    protected final ResourceManager resources;

    /**
     * Initializes the screen.
     * @param game the Game instance this screen belongs to
     */
    public BomberScreen(Game game) {
        super(new ScreenViewport());

        this.game = (BomberGame) game;
        this.resources = this.game.getBomberResources();
        this.bgColor = resources.getSkin().getColor("darkBlue");

        initView();
        initController();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        super.act(delta);
        super.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        super.dispose();
    }
}
