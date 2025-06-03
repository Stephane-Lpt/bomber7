package com.bomber7.core.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.components.BomberTextButton;
import com.bomber7.utils.Dimensions;
import com.bomber7.core.views.*;

public class GameScreen extends BomberScreen {


    /**
     * Constructs a new GameScreen associated with the given game.
     * @param game the Game instance this screen belongs to
     */
    public GameScreen(Game game) {
        super(game);
    }

    /**
     * Game screen initialization
     */
    @Override
    public void initView() {

        /** =======[GENERAL PURPOSES]=============================================== */

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        /** =======[BUTTON]=============================================== */

        /** Buttons to go to key bindings menu. */
        BomberTextButton settingsButton = new BomberTextButton(resources.getString("options"), resources);
        /** Buttons to go to key bindings menu. */
        BomberTextButton goBackButton = new BomberTextButton(resources.getString("go_back"), resources);

        mainTable.add(settingsButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .left();
        mainTable.row();
        mainTable.add(goBackButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT);
        mainTable.row();

        /** =======[MAP VIEW]=============================================== */

        /** Path to the tileset JSON file. */
        Path tilesetJsonPath = Paths.get("assets/textures/tileset.tsj");
        /** Map name for the current game. */
        String mapName = "foy";
        /** Create a LevelMapFactory to load the map from the tileset JSON file. */
        LevelMapFactory levelMapFactory = new LevelMapFactory(tilesetJsonPath);
        LevelMap levelMap = levelMapFactory.createLevelMap(mapName);
        /** Map view of the game. */
        ViewMap viewMap = new ViewMap(levelMap, resources);
        viewMap.setWidth(500);

        /** =======[FULL FRAME]=============================================== */

        mainTable.add(viewMap)
            .width(1920)
            .height(1080);
        stage.addActor(mainTable);
    }

    /**
     * Game controller initialization
     */
    @Override
    public void initController() {}

    /**
     * Update the game screen for each frame
     */
    @Override
    public void render(float delta) {
        super.render(delta);
    }

}
