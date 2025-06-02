package com.bomber7.core.screens;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.components.BomberTextButton;
import com.bomber7.utils.Dimensions;
import com.bomber7.core.views.*;

public class GameScreen extends BomberScreen {

    /** Buttons to go to key bindings menu */
    private BomberTextButton settingsButton = new BomberTextButton(resources.getString("options"), resources);
    /** Buttons to go to key bindings menu */
    private BomberTextButton goBackButton = new BomberTextButton(resources.getString("go_back"), resources);
    /** Tileset JSON file path. */
    //private final Path tilesetJsonPath = Paths.get("/home/t70n/Documents/bomber7/assets/textures/tileset.tsj");
    /** Map name. */
    private final String mapName;
     /** Map view of the game */
    private ViewMap viewMap;
    /** New factory for a level map. */
    //private final 

    /**
     * Constructs a new GameScreen associated with the given game.
     * @param game the Game instance this screen belongs to
     */
    public GameScreen(Game game, String mapName, String mapFileName) {
        super(game);
        this.mapName = mapName;
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

        mainTable.add(settingsButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING / 2f)
            .padRight(Dimensions.COMPONENT_SPACING / 2f);
        mainTable.add(goBackButton)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padTop(Dimensions.COMPONENT_SPACING / 2f)
            .padRight(Dimensions.COMPONENT_SPACING / 2f);
        mainTable.row();

        /** =======[MAP VIEW]=============================================== */

        Path tilesetJsonPath = Paths.get("/home/t70n/Documents/bomber7/assets/textures/tileset.tsj");
        LevelMapFactory levelMapFactory = new LevelMapFactory(tilesetJsonPath);
        LevelMap levelMap = levelMapFactory.createLevelMap("foy");
        viewMap = new ViewMap(levelMap, resources);

        MapActor mapActor = new MapActor(viewMap);

        Table viewMapTable = new Table();
        viewMapTable.setSize(Dimensions.VIEW_MAP_WIDTH, Dimensions.VIEW_MAP_HEIGHT);
        viewMapTable.add(mapActor).expand().fill();

        /** =======[FULL FRAME]=============================================== */

        mainTable.add(viewMapTable).expand().center();
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
