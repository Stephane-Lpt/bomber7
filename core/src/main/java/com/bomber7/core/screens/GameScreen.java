package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.bomber7.core.components.BomberTextButton;
import com.bomber7.utils.Dimensions;
import com.bomber7.core.views.*;

public class GameScreen extends BomberScreen {

    /** Buttons to go to key bindings menu */
    private BomberTextButton settingsButton = new BomberTextButton(resources.getString("options"), resources);

    /** Buttons to go to key bindings menu */
    private BomberTextButton goBackButton = new BomberTextButton(resources.getString("go_back"), resources);

    /** Map view of the game */
    private ViewMap viewMap;

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

        viewMap = new ViewMap(LevelMapFactory.createLevelMap(), resources);
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
