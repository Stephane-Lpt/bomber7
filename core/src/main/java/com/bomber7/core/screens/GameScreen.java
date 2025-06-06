package com.bomber7.core.screens;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;

import java.nio.file.Path;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.core.components.BomberTextButton;
import com.bomber7.core.views.ViewMap;
import com.bomber7.utils.Dimensions;

import com.bomber7.utils.ProjectPaths;

/**
 * Represents the game screen in the Bomber7 game.
 * This screen displays the game map
 */
public class GameScreen extends BomberScreen {

    /**
     * Constructs a new GameScreen associated with the given game.
     * @param game the Game instance this screen belongs to
     */
    public GameScreen(Game game) {
        super(game);
    }

    /**
     * Game screen initialization.
     */
    @Override
    public void initView() {

        /* =======[GENERAL PURPOSES]=============================================== */

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        /* =======[SCOREBOARD]=============================================== */

        // Ajout d'une grille tout en haut de l'écran
        Table scoreboardTable = new Table();
        scoreboardTable.setFillParent(true);
        scoreboardTable.setDebug(false);

        // Créer la grille
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 15; col++) {
                Label cell = new Label("", new Label.LabelStyle());
                scoreboardTable.add(cell).width(Gdx.graphics.getWidth() / 15f).height(Gdx.graphics.getHeight() / 10f);
                for (Character character : game.getListPlayer()) {
                    // Afficher le score dans les 5 dernières colonnes
                    if (col >= 10) {
                        cell.setText(String.valueOf(character.getScore()));
                        col++;
                    }
                }
            }
            scoreboardTable.row();


        /* =======[MAP VIEW]=============================================== */

        /* Path to the tileset JSON file. */
        Path tilesetJsonPath = ProjectPaths.getTileset();
        /* Map name for the current game. */
        String mapName = "foy";
        /* Create a LevelMapFactory to load the map from the tileset JSON file. */
        LevelMapFactory levelMapFactory = new LevelMapFactory(tilesetJsonPath);
        LevelMap levelMap = levelMapFactory.createLevelMap(mapName);
        /* Map view of the game. */
        ViewMap viewMap = new ViewMap(levelMap, resources);

        /* =======[FULL FRAME]=============================================== */

        mainTable.add(viewMap);
        stage.addActor(mainTable);
    }

    /**
     * Game controller initialization.
     */
    @Override
    public void initController() {

    }

    /**
     * Update the game screen for each frame.
     * @param delta time since the last frame
     */
    @Override
    public void render(float delta) {
        super.render(delta);
    }

}
