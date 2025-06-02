package com.bomber7.core.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * MapActor is an Actor that draws the map using drawing of each square.
 * draw() is called by libGDX for each frame.
 */
public class MapActor extends Actor {

    /** The ViewMap instance : map grid + resources */
    private ViewMap viewMap;

    /**
     * Constructs a new MapActor with the specified ViewMap.
     * @param viewMap the ViewMap 
     */
    public MapActor(ViewMap viewMap) {
        this.viewMap = viewMap;
    }

    /**
     * Method called by libGDX to draw the map.
     * It updates the map textures by iterating through each square in the grid.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        viewMap.updateMapTextures();
    }
}
